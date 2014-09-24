/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.multivote.handler;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import jxl.Sheet;
import jxl.Workbook;



import com.icss.oa.multivote.dao.VoteItemDAO;
import com.icss.oa.multivote.dao.VotePersonDAO;
import com.icss.oa.multivote.dao.VotePlanDAO;
import com.icss.oa.multivote.dao.VoteTableDAO;
import com.icss.oa.multivote.dao.VoteValueDAO;
import com.icss.oa.multivote.vo.VoteArticleVO;
import com.icss.oa.multivote.vo.VoteItemVO;
import com.icss.oa.multivote.vo.VotePersonVO;
import com.icss.oa.multivote.vo.VotePlanVO;
import com.icss.oa.multivote.vo.VoteTableVO;
import com.icss.oa.multivote.vo.VoteValueVO;



import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;


/**
 * @author huangshilong
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VoteHandler {
	private Connection conn;
	public Workbook rwb = null;
	public Sheet sheetimp = null;

	public boolean booInsertFlag = true;
	public boolean booEndFlag = false;
	public String erromessage = "";
	public int intRowAccount = 0; //待导入的数据条数
	public int intRowIn = 0; //成功导入的条数
	public int intRowFalse = 0; //错误条数
	public String datafile = ""; //数据文件名称
	public String filepath = null;
	public VoteHandler(Connection conn) {
		this.conn = conn;
	}
	/**
	 * 
	 * @param name
	 * @return 根据部门上级地区名称取得部门代码及部门名称
	 */
	public List findOrgByParentname(String name) {
		//		System.out.println("执行函数findOrgByParentname："+name);
		List result = new ArrayList();

		StringBuffer sql = new StringBuffer();
		String orgname = "";
		String orgcode = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select distinct org_code,org_name from vote_person where org_parent_name='" + name + "'");
		//System.out.println("+++++++++++++findOrgByParentname sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					orgname = rs.getString("ORG_NAME");
					orgcode = rs.getString("ORG_CODE");
					VotePersonVO vo = new VotePersonVO();
					vo.setOrgCode(orgcode);
					vo.setOrgName(orgname);
					result.add(vo);
					//                        System.err.println("取出的Orgcode："+vo.getOrgCode());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		return result;
	}
	/**
	 * 根据机构代码及计划ID取得投票人员列表
	 * @param orgCode
	 * @param planid
	 * @return
	 */
	public List findPersonByOrgCode(String orgCode, String planid) {
		VotePersonDAO dao = new VotePersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
			.append(" org_code= '")
			.append(orgCode)
			.append("' and plan_planid =")
			.append(Integer.valueOf(planid))
			.append(" and flag=0");

		dao.setWhereClause(sql.toString());
		dao.addOrderBy("name", true);
		factory.setDAO(dao);
		try {
			result = factory.find(new VotePersonVO());
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据机构代码及计划ID取得投票人员列表
	 * @param orgCode
	 * @param planid
	 * @return
	 */
	public List findPersonByOrgName(String orgName, String planid) {
		VotePersonDAO dao = new VotePersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
			.append(" org_name= '")
			.append(orgName)
			.append("' and plan_planid =")
			.append(Integer.valueOf(planid))
			.append(" and flag=0");

		dao.setWhereClause(sql.toString());
		dao.addOrderBy("name", true);
		factory.setDAO(dao);
		try {
			result = factory.find(new VotePersonVO());
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据人员ID取得人员的VO信息
	 * @param personid
	 * @return
	 */
	public VotePersonVO findByPersonid(String personid) {
		DAOFactory factory = new DAOFactory(conn);
		VotePersonDAO dao = new VotePersonDAO();
		dao.setPersonId(new Integer(personid));
		factory.setDAO(dao);
		VotePersonVO vo = new VotePersonVO();
		List list = new ArrayList();
		try {
				list = factory.find(new VotePersonVO());
		} catch (DAOException e) {

				e.printStackTrace();
		}
		vo = (VotePersonVO) list.get(0);
		return vo;
		
	}
	/**
	 * 根据计划id取得计划的vo信息
	 * @param planid
	 * @return
	 */
	public VotePlanVO getPlanVo(String planid) {
		DAOFactory factory = new DAOFactory(conn);
		VotePlanDAO dao = new VotePlanDAO();
		dao.setPlanPlanid(new Integer(planid));
		factory.setDAO(dao);
		VotePlanVO vo = new VotePlanVO();
		List list = new ArrayList();
		try {
				list = factory.find(new VotePlanVO());
		} catch (DAOException e) {

				e.printStackTrace();
		}
		vo = (VotePlanVO) list.get(0);
		return vo;
	}
	/**
	 * 根据计划ID取得投票文章列表（不包括文章的内容）list，
	 * @param planid
	 * @return
	 */
	public List getVoteArticlelist(Integer planid) {
		//		System.out.println("执行函数findOrgByParentname："+name);
		List result = new ArrayList();

		StringBuffer sql = new StringBuffer();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select art_id,table_id,art_title,art_writer,art_uploadusr,art_uploadtime,art_votenum," +
				"art_votescore,art_place,art_totalnum,art_typeid,art_typename,art_wordnum,art_pubtime,art_istoolong,art_usenum,art_statnum,art_memo ,art_isconsult " +
				"from vote_article where table_id=" + planid +" order by art_id" );
//		System.out.println("+++++++++++++getVoteArticlelist sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					VoteArticleVO vo = new VoteArticleVO();
					vo.setArtId(new Integer(rs.getString("art_id")));
					vo.setArtTitle(rs.getString("art_title"));
					vo.setTableId(new Integer(rs.getString("table_id")==null?"-1":rs.getString("table_id")));
					vo.setArtWriter(rs.getString("art_writer"));
					vo.setArtUploadusr(rs.getString("art_uploadusr"));
					vo.setArtUploadtime(rs.getString("art_uploadtime"));
					vo.setArtVotenum(new Integer(rs.getString("art_votenum")==null?"0":rs.getString("art_votenum")));
					vo.setArtVotescore(rs.getString("art_votescore"));
					vo.setArtTotalnum(rs.getString("art_totalnum"));
					vo.setArtTypeid(rs.getString("art_typeid"));
					vo.setArtTypename(rs.getString("art_typename"));
					vo.setArtWordnum(rs.getString("art_wordnum"));
					vo.setArtPubtime(rs.getString("art_pubtime"));
					vo.setArtIstoolong(rs.getString("art_istoolong"));
					vo.setArtUsenum(rs.getString("art_usenum"));
					vo.setArtStatnum(rs.getString("art_statnum"));
					vo.setArtMemo(rs.getString("art_memo"));
					vo.setArtIsconsult(rs.getString("art_isconsult"));
					result.add(vo);
					//                        System.err.println("取出的Orgcode："+vo.getOrgCode());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		return result;
	}
	public void updatePersonPassword(String personid, String password, String firstflag) {
		StringBuffer sql = new StringBuffer();
		
		PreparedStatement pstmt = null;
		
		sql.append("update vote_person set firstflag='" +firstflag+"' , password='"+password+"' "
				
				+"  where person_id=" + personid) ;
		System.out.println("+++++++++++++updatePersonPassword sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.executeUpdate(sql.toString());
			
			
		} catch (SQLException e) {
			System.out.println("+++++++++++++updatePersonPassword err sql+++++++++++++++"+sql);
			e.printStackTrace();
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 根据计划ID取得投票样式表信息列表
	 * @param planid
	 * @return
	 */
	public List getVoteTableList(String planid) {
		DAOFactory factory = new DAOFactory(conn);
		VoteTableDAO dao = new VoteTableDAO();
		dao.setPlanPlanid(new Integer(planid));
		factory.setDAO(dao);
		VoteTableVO vo = new VoteTableVO();
		List list = new ArrayList();
		try {
				list = factory.find(new VoteTableVO());
		} catch (DAOException e) {

				e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据计划中投票表样ID取得投票指标列表
	 * @param tableId
	 * @return
	 */
	public List getVoteItemList(Integer tableId) {
		DAOFactory factory = new DAOFactory(conn);
		VoteItemDAO dao = new VoteItemDAO();
		dao.setTableId(tableId);
		factory.setDAO(dao);
		List list = new ArrayList();
		try {
				list = factory.find(new VoteItemVO());
		} catch (DAOException e) {

				e.printStackTrace();
		}
		
		return list;
	}
	/**
	 *向投票记录表中增加投票记录
	 * @param votevo
	 */
	public void addVoteValue(VoteValueVO votevo) {
//		System.out.println("+++++++++++==addSys1");
		VoteValueDAO dao = new VoteValueDAO();
		dao.setValueObject(votevo);
		dao.setConnection(conn);
//		System.out.println("+++++++++++==addSys2");
		try {
//			System.out.println("+++++++++++==addSys3");
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
//			System.out.println("+++++++++++==err");
		}
		
	}
	/**
	 * 修改计划信息
	 * @param articalvo
	 */
	public void updatePlan(VotePlanVO foplanvo) {
		VotePlanDAO dao = new VotePlanDAO();
		dao.setValueObject(foplanvo);
		dao.setPlanPlanid(foplanvo.getPlanPlanid());
		dao.setConnection(conn);
		try {
			dao.update();
			//System.out.println("修改计划信息成功");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 根据用户ID更改投票标识
	 * @param personid
	 * @param integer
	 */
	public void updatePersonFlag(String personid, Integer flag) {
		StringBuffer sql = new StringBuffer();
		
		PreparedStatement pstmt = null;
		
		sql.append("update vote_person set flag=" +flag
				
				+"  where person_id=" + personid) ;
		System.out.println("+++++++++++++updatePersonFlag sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.executeUpdate(sql.toString());
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 根据计划ID及季度信息进行票数合计
	 * @param planid
	 * @param planSeason
	 * @return
	 */
	public Map statVotenum(String planid, String planSeason) {
		StringBuffer sql = new StringBuffer();
		
		Map statnummap=new HashMap();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append("select art_id,item_id,sum(vote_value) as statnum  from oabase.vote_value where vote_season ='"+planSeason+"'  group by art_id,item_id ");
		System.out.println("+++++++++++++statVotenum sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs=pstmt.executeQuery(sql.toString());
			if (rs != null) {
				while (rs.next()) {
				String artitem=rs.getString("art_id").toString()+":"+rs.getString("item_id").toString();
				
				statnummap.put(artitem,rs.getString("statnum"));
//				System.out.println("+++++++++++++++artitem="+artitem+"value1="+statnummap.get(artitem));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return statnummap;
	}
	/**
	 * 取得此投票计划下的部门投票人数量
	 * @param planPlanid
	 * @return
	 */
	public Map getDeptVotePersonNum(Integer planid) {
		Map map = new HashMap();

		StringBuffer sql = new StringBuffer();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select org_name ,count(hrid) as personnum from vote_person where plan_planid="+planid+" group by org_name  order by org_name " );
//		System.out.println("+++++++++++++getVoteArticlelist sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String	voteperson="0";
					if(rs.getString("personnum")!=null){
						voteperson=rs.getString("personnum");
					}
					if(rs.getString("org_name")!=null){
						map.put(rs.getString("org_name"),voteperson);
						System.out.println("单位名称="+rs.getString("org_name")+"投票人数="+voteperson);
					}
					
					//                        System.err.println("取出的Orgcode："+vo.getOrgCode());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		return map;
	}
	/**
	 * 
	 * @param planid
	 * @return
	 */
	public Map getDeptHasVotePersonNum(Integer planid) {
		Map map = new HashMap();

		StringBuffer sql = new StringBuffer();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select org_name ,count(hrid) as personnum from vote_person where plan_planid="+planid+" and flag='1' group by org_name  order by org_name ");
//		System.out.println("+++++++++++++getVoteArticlelist sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String	voteperson="0";
					if(rs.getString("personnum")!=null){
						voteperson=rs.getString("personnum");
					}
					if(rs.getString("org_name")!=null){
						map.put(rs.getString("org_name"),voteperson);
					}
					
					//                        System.err.println("取出的Orgcode："+vo.getOrgCode());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		return map;
	}
	/**
	 * 取得用户列表
	 * @return
	 */
		public List getUserManagerList(String sql) {
			VotePersonDAO dao = new VotePersonDAO();
			DAOFactory factory = new DAOFactory(conn);
			
			dao.setWhereClause(sql.toString());
			dao.addOrderBy("orgName",true);
			dao.addOrderBy("name", true);
			//dao.addOrderBy("logTime",false);
			factory.setDAO(dao);
			
			List list = null;
			try {
				list = factory.find(new VotePersonVO());
			//	System.out.println("handler已经取得personlist");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
//		设置文件
		private void setDataFile(String filename) {
			datafile = filename ;
			System.out.println("datafile" + datafile);
		}
//		创建workbook对象
		public String initDataFile() {
			try {
				//构建Workbook对象, 只读Workbook对象
				//直接从本地文件创建Workbook
				//从输入流创建Workbook
				InputStream is = new FileInputStream(datafile);
				System.out.println("读取文件");
				rwb = Workbook.getWorkbook(is);
				sheetimp = rwb.getSheet(0);
				this.intRowAccount = sheetimp.getRows();
				System.out.println("数据文件 intRowAccount=" + intRowAccount);
			} catch (Exception e) {
				erromessage += "初始化ｅｘｃｌｅ文件错:" + e.getMessage();
				e.printStackTrace();
			}
			return this.erromessage;
		}

		/**
		 * 取得excel表中的用户信息,并生成列表
		 * @param filepath2
		 * @param planid
		 * @return
		 */
		public void impVoteUserReadExcel(String file_name, Integer planid) {
			setDataFile(file_name);

			initDataFile();
			int intBegin = 2;
			this.booInsertFlag = false;
			//List list=new ArrayList();
			
			try {
				for (int i = intBegin; i < intRowAccount; i++) {
					if (booEndFlag) {
						break;
					} else {
						VotePersonVO vo=new VotePersonVO();

						System.out.println("开始装载导入文件的数据");
						vo.setUuid("".equals(sheetimp.getCell(1, i).getContents())?"":sheetimp.getCell(1, i).getContents());
						vo.setHrid("".equals(sheetimp.getCell(2, i).getContents())?"-1":sheetimp.getCell(2, i).getContents());
						vo.setName("".equals(sheetimp.getCell(3, i).getContents())?"":sheetimp.getCell(3, i).getContents());
						vo.setOrgCode("".equals(sheetimp.getCell(4, i).getContents())?"-1":sheetimp.getCell(4, i).getContents());
						vo.setOrgName("".equals(sheetimp.getCell(5, i).getContents())?"":sheetimp.getCell(5, i).getContents());
						vo.setDepCode("".equals(sheetimp.getCell(6, i).getContents())?"-1":sheetimp.getCell(6,i).getContents());
						vo.setDepName("".equals(sheetimp.getCell(7, i).getContents())?"":sheetimp.getCell(7, i).getContents());
						vo.setOrgParentName("".equals(sheetimp.getCell(8, i).getContents())?"":sheetimp.getCell(8, i).getContents());
						vo.setHeadshipLevelCode("".equals(sheetimp.getCell(9, i).getContents())?"":sheetimp.getCell(9, i).getContents());
						vo.setPlanPlanid(planid);
						vo.setFirstflag("1");
						vo.setFlag("0");
						vo.setPassword("1");
						
						//list.add(vo);
						System.out.println("完成装载导入文件的数据！");
						this.addVotePerson(vo);
					}
				}

				//rs.close();
				//this.close();
				//return rslist;
			} catch (Exception ex) {
				ex.printStackTrace();
				
			}
			//return list;
		}	
		/**
		 * 增加投票人员记录
		 * @param invaluevo
		 */
		public void addVotePerson(VotePersonVO vo) {
			VotePersonDAO dao = new VotePersonDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			try {
				dao.create();
			} catch (DAOException e) {
				e.printStackTrace();
				 System.out.println(" 增加投票人员记录 err");
			}
			
			
		}
		/**
		 * 取得用户列表
		 * @return
		 */
			public List getUserManagerList() {
				VotePersonDAO dao = new VotePersonDAO();
				DAOFactory factory = new DAOFactory(conn);
				//dao.addOrderBy("logTime",false);
				factory.setDAO(dao);
				
				List list = null;
				try {
					list = factory.find(new VotePersonVO());
					System.out.println("handler已经取得personlist");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			public String creatRandomString() {
				String[] randomchars =
					new String[] {
						"a",
						"b",
						"c",
						"d",
						"e",
						"f",
						"g",
						"h",
						"i",
						"j",
						"k",
						//"l",
						"m",
						"n",
						//"o",
						"p",
						"q",
						"r",
						"s",
						"t",
						"u",
						"v",
						"w",
						"x",
						"y",
						"z",
						//"0",
						//"1",
						"2",
						"3",
						"4",
						"5",
						"6",
						"7",
						"8",
						"9" };
				String password = MakePassword(randomchars, 6);
				return password;
			}
			public static String MakePassword(String[] pwdchars, int pwdlen) {
				String tmpstr = "";
				int iRandNum;
				Random rnd = new Random();
				for (int i = 0; i < pwdlen; i++) {
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					iRandNum = rnd.nextInt(pwdchars.length);
					tmpstr += pwdchars[iRandNum];
				}
				return tmpstr;
			}
			/**
			 * 根据用户ID修改用户信息
			 * @param personId
			 */
				public void updatePersonVO(VotePersonVO vo) {
					VotePersonDAO dao = new VotePersonDAO();
					dao.setPersonId(vo.getPersonId());
					dao.setValueObject(vo);
					dao.setConnection(conn);
					try {
						dao.update();
					} catch (DAOException e) {
						e.printStackTrace();
					}
					
					
				}
			/**
			 * 根据计划ID、季度及投票人ID删除投票值
			 * @param planid
			 * @param personId
			 * @param planSeason
			 */
			public void deleteUserVoteValue(Integer planid, Integer personId, String planSeason) {
				StringBuffer sql = new StringBuffer();
				
				PreparedStatement pstmt = null;
				
				sql.append("delete  from  vote_value where vote_value.vote_id in (select vote_id from vote_value left join vote_article on vote_value.art_id =vote_article.art_id "+
                           " left join vote_table on vote_article.table_id=vote_table.table_id "+
                          " where plan_planid="+planid + " and person_id="+personId+")") ;
				System.out.println("+++++++++++++deleteUserVoteValue sql+++++++++++++++"+sql);
				try {
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.executeUpdate(sql.toString());
					
					
				} catch (SQLException e) {

					e.printStackTrace();
				} finally {
					
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				
			}
			/**
			 * 根据计划ID及人员ID删除投票人员信息
			 * @param planid
			 * @param personId
			 */
			public void deleteUser(Integer planid, Integer personId) {
				StringBuffer sql = new StringBuffer();
				
				PreparedStatement pstmt = null;
				
				sql.append("delete from vote_person   where person_id=" + personId+" and plan_planid="+planid ) ;
				System.out.println("+++++++++++++deleteUser sql+++++++++++++++"+sql);
				try {
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.executeUpdate(sql.toString());
					
					
				} catch (SQLException e) {

					e.printStackTrace();
				} finally {
					
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				
			}
			/**
			 * 根据用户ID和计划ID更新用户是否投过票
			 * @param personId,planId
			 */
			public void updateUserVote(Integer personId,Integer planId,String flag) {
				String sql=null;
				
				if("1".equals(flag))//如果该用户以前已投票，则更改为未投票
				{ sql = " update vote_person set flag='0' where plan_planid="
						+ planId.toString()+" and person_id="+personId.toString();
				}else{//如果该用户以前未投票，则更改为已投票
					sql = " update vote_person set flag='1' where plan_planid="
						+ planId.toString()+" and person_id="+personId.toString();
				}
				
				System.out.println("updatePerson: "+sql); 

				Statement stmt = null;

				
				try {
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					//System.out.println("完成更新");
				} catch (SQLException e) {
					e.printStackTrace();
					
				} finally {
					if(stmt!=null){
						try {
							stmt.close();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
					
				}
				
			}
			/**
			 * 创建目录
			 * @param filepath
			 */
			public void CreatDir(String filepath) {

				// this.filepath = toFilePathManager.getString("toFile_path");
				this.filepath = filepath;
				System.out.println("创建目录toFile_path is : " + filepath);
				File file = new File(filepath);
				if (!file.exists()) {
					file.mkdirs();
				}
			}
}
