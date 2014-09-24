/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.fo.handler;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;
import jxl.Workbook;

import com.icss.oa.fo.dao.FoArticalDAO;
import com.icss.oa.fo.dao.FoInputvalueDAO;
import com.icss.oa.fo.dao.FoInputvoteDAO;
import com.icss.oa.fo.dao.FoPlanDAO;
import com.icss.oa.fo.dao.FoVoteDAO;
import com.icss.oa.fo.dao.FoVotepersonDAO;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoInputvalueArtVO;
import com.icss.oa.fo.vo.FoInputvalueVO;
import com.icss.oa.fo.vo.FoInputvoteVO;
import com.icss.oa.fo.vo.FoInputvotevalueVO;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.fo.vo.FoVoteVO;
import com.icss.oa.fo.vo.FoVotepersonVO;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.Organization;


import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;


/**
 * @author huangshilong
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FoHandler {
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
	public FoHandler(Connection conn) {
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

		sql.append("select distinct org_code,org_name from fo_voteperson where org_parent_name='" + name + "'");
		//System.out.println("+++++++++++++findOrgByParentname sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					orgname = rs.getString("ORG_NAME");
					orgcode = rs.getString("ORG_CODE");
					FoVotepersonVO vo = new FoVotepersonVO();
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
		FoVotepersonDAO dao = new FoVotepersonDAO();
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
			result = factory.find(new FoVotepersonVO());
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
	public FoVotepersonVO findByPersonid(String personid) {
		DAOFactory factory = new DAOFactory(conn);
		FoVotepersonDAO dao = new FoVotepersonDAO();
		dao.setPersonId(new Integer(personid));
		factory.setDAO(dao);
		FoVotepersonVO vo = new FoVotepersonVO();
		List list = new ArrayList();
		try {
				list = factory.find(new FoVotepersonVO());
		} catch (DAOException e) {

				e.printStackTrace();
		}
		vo = (FoVotepersonVO) list.get(0);
		return vo;
		
	}
	/**
	 * 根据计划id取得计划的vo信息
	 * @param planid
	 * @return
	 */
	public FoPlanVO getPlanVo(String planid) {
		DAOFactory factory = new DAOFactory(conn);
		FoPlanDAO dao = new FoPlanDAO();
		dao.setPlanPlanid(new Integer(planid));
		factory.setDAO(dao);
		FoPlanVO vo = new FoPlanVO();
		List list = new ArrayList();
		try {
				list = factory.find(new FoPlanVO());
		} catch (DAOException e) {

				e.printStackTrace();
		}
		vo = (FoPlanVO) list.get(0);
		return vo;
	}
	/**
	 * 根据计划ID取得投票文章列表（不包括文章的内容）list，
	 * @param planid
	 * @return
	 */
	public List getVoteArticlelist(String planid) {
		//		System.out.println("执行函数findOrgByParentname："+name);
		List result = new ArrayList();

		StringBuffer sql = new StringBuffer();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select art_id,plan_planid,art_title,art_writer,art_uploadusr,art_uploadtime,art_votenum," +
				"art_votescore,art_place,art_totalnum,art_typeid,art_typename,art_wordnum,art_pubtime,art_istoolong,art_usenum,art_statnum,art_memo ,art_isconsult " +
				"from fo_artical where plan_planid=" + planid +" order by art_id" );
//		System.out.println("+++++++++++++getVoteArticlelist sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					FoArticalVO vo = new FoArticalVO();
					vo.setArtId(new Integer(rs.getString("art_id")));
					vo.setArtTitle(rs.getString("art_title"));
					vo.setPlanPlanid(new Integer(rs.getString("plan_planid")==null?"-1":rs.getString("plan_planid")));
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
	/**
	 *向投票记录表中增加投票记录
	 * @param votevo
	 */
	public void addVoteValue(FoVoteVO votevo) {
//		System.out.println("+++++++++++==addSys1");
		FoVoteDAO dao = new FoVoteDAO();
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
	 * 根据文章ID取得此文章的文章信息，不包括文章内容
	 * @return
	 */
	public FoArticalVO getSimpleArtVO(String art_id) {
//		System.out.println("执行函数findOrgByParentname："+name);
		
		FoArticalVO vo = new FoArticalVO();
		StringBuffer sql = new StringBuffer();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select art_id,plan_planid,art_title,art_writer,art_uploadusr,art_uploadtime,art_votenum," +
				"art_votescore,art_place,art_totalnum,art_typeid,art_typename,art_wordnum,art_pubtime,art_istoolong,art_usenum,art_statnum,art_memo ,art_isconsult " +
				"from fo_artical where art_id=" + art_id );
		System.out.println("+++++++++++++getSimpleArtVO sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					
					vo.setArtId(new Integer(rs.getString("art_id")));
					vo.setArtTitle(rs.getString("art_title"));
					vo.setPlanPlanid(new Integer(rs.getString("plan_planid")==null?"-1":rs.getString("plan_planid")));
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
					//result.add(vo);
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

		return vo;
	}
	/**
	 * 根据文章ID，更改此文章中的投票值
	 * @param string
	 * @param artVotenum
	 */
	public void updateArticalVotenum(String art_id, Integer artVotenum) {
		
		StringBuffer sql = new StringBuffer();
		
		PreparedStatement pstmt = null;
		
		sql.append("update fo_artical set art_votenum=" +artVotenum
				
				+"  where art_id=" + art_id) ;
		//System.out.println("+++++++++++++updateArticalVotenum sql+++++++++++++++"+sql);
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
	 * 根据用户ID更改投票标识
	 * @param personid
	 * @param integer
	 */
	public void updatePersonFlag(String personid, Integer flag) {
		StringBuffer sql = new StringBuffer();
		
		PreparedStatement pstmt = null;
		
		sql.append("update fo_voteperson set flag=" +flag
				
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
	public void updatePersonPassword(String personid, String password, String firstflag) {
		StringBuffer sql = new StringBuffer();
		
		PreparedStatement pstmt = null;
		
		sql.append("update fo_voteperson set firstflag='" +firstflag+"' , password='"+password+"' "
				
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
	 * 根据文章ＩＤ取得文章信息
	 * @param artid
	 * @return
	 */
	public FoArticalVO getArticleDetailvo(String artid) {
		FoArticalVO vo=new FoArticalVO();
		FoArticalDAO dao = new FoArticalDAO();
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
			.append(" art_id=")
			.append(artid)
			;

		dao.setWhereClause(sql.toString());
		dao.addOrderBy("name", true);
		factory.setDAO(dao);
		try {
			result = factory.find(new FoArticalVO());
			vo=(FoArticalVO) result.get(0);
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return vo;
	}
	/**
	 * 根据计划ID取得投票人数
	 * @param planid
	 * @return
	 */
	public String getVotePersonNum(String planid) {
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String	votepersonnum="0";
		sql.append("select count(person_Id) as votepersonnum from FO_VOTEPERSON" 
				
				+"  where plan_Planid=" + planid +" and flag='1'") ;
		System.out.println("+++++++++++++getVotePersonNum sql+++++++++++++++"+sql);
		try {
				pstmt = conn.prepareStatement(sql.toString());
				
				rs=pstmt.executeQuery(sql.toString());
				if(rs.next()){
					votepersonnum=rs.getString("votepersonnum");
				}
				return votepersonnum;
			
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
		return votepersonnum;
	}
	/**
	 * 根据计划ID与文章ID取得票数合计
	 * @param planid
	 * @param string
	 * @return
	 */
	public int getArtVoteNum(Integer planid, String artid) {
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int	votepersonnum=0;
		sql.append("select sum(vote_value) as votenum from FO_VOTE" 
				
				+"  where art_id=" + artid ) ;
//		System.out.println("+++++++++++++getArtVoteNum sql+++++++++++++++"+sql);
		try {
				pstmt = conn.prepareStatement(sql.toString());
				
				rs=pstmt.executeQuery(sql.toString());
				if(rs.next()){
					votepersonnum=rs.getInt("votenum");
				}
				return votepersonnum;
			
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
		return votepersonnum;
	}
	public void outHtml(
			PrintWriter out,
			String msg,
			HttpServletRequest request,
			String type) {
			out.println(
				"<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("  <TR height=24 class=title-04 bgcolor=\"#F2F9FF\">");
			out.println(
				"    <td width=\"1\" background=\""
					+ request.getContextPath()
					+ "/images/bg-21.gif\"><img src=\""
					+ request.getContextPath()
					+ "/images/bg-21.gif\" width=\"1\" height=\"100%\"></td>");

			out.println(
				"    <td width=\"100%\" class=\"dot\" align=left>&nbsp;&nbsp;");
			if ("error".equals(type)) {
				out.println("<font color='red'>");
			}
			out.println(msg);
			if ("error".equals(type)) {
				out.println("</font>");
			}
			out.println("</td>");
			out.println("  </TR>");
			out.println("</TABLE>");
			out.flush();
		}
	/**
	 * 根据计划ID取得输入的投票列表
	 * @param planid
	 * @return
	 */
	public List getInputVoteList(String planid) {
		FoInputvoteDAO dao = new FoInputvoteDAO();
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql	.append("  plan_planid =")
			.append(Integer.valueOf(planid));

		dao.setWhereClause(sql.toString());
		dao.addOrderBy("invoteDate", false);
		factory.setDAO(dao);
		try {
			result = factory.find(new FoInputvoteVO());
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据输入ID取得文章具体投票值
	 * @param invoteid
	 * @return
	 */
	public List getInputValueList(String invoteid) {
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		FoInputvalueArtVO vo=new FoInputvalueArtVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select invalue_id,invote_id,invalue_value,fo_inputvalue.art_id,plan_planid,art_title,art_writer,art_uploadusr,art_uploadtime,art_votenum," +
				"art_votescore,art_place,art_totalnum,art_typeid,art_typename,art_wordnum,art_pubtime,art_istoolong,art_usenum,art_statnum,art_memo ,art_isconsult " +
				"from fo_artical left join fo_inputvalue on fo_artical.art_id=fo_inputvalue.art_id where fo_inputvalue.invote_id="+invoteid);
		System.out.println("+++++++++++++getInputValueList sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					vo.setInvalueId(new Integer(rs.getString("invalue_id")==null?"-1":rs.getString("invalue_id")));
					vo.setInvoteId(new Integer(rs.getString("invote_id")==null?"-1":rs.getString("invote_id")));
					vo.setInvalueValue(new Integer(rs.getString("invalue_value")==null?"-1":rs.getString("invalue_value")));
					vo.setArtId(new Integer(rs.getString("art_id")));
					vo.setArtTitle(rs.getString("art_title"));
					vo.setPlanPlanid(new Integer(rs.getString("plan_planid")==null?"-1":rs.getString("plan_planid")));
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
					list.add(vo);
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

		return list;
	}
	/**
	 * 取得输入投票值的简单列表信息，不含文章信息
	 * @param invoteid
	 * @return
	 */
	public List getSimpleInputValueList(String invoteid) {
		FoInputvalueDAO dao = new FoInputvalueDAO();
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql	.append("  invote_id =")
			.append(Integer.valueOf(invoteid));

		dao.setWhereClause(sql.toString());
		dao.addOrderBy(" artId", true);
		factory.setDAO(dao);
		try {
			result = factory.find(new FoInputvalueVO());
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据计划ID取得（不包含文章内容）文章信息的Map
	 * @param planid
	 * @return
	 */
	public Map getSimpleArticleMap(String planid) {
//		System.out.println("执行函数findOrgByParentname："+name);
		Map map = new HashMap();

		StringBuffer sql = new StringBuffer();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select art_id,plan_planid,art_title,art_writer,art_uploadusr,art_uploadtime,art_votenum," +
				"art_votescore,art_place,art_totalnum,art_typeid,art_typename,art_wordnum,art_pubtime,art_istoolong,art_usenum,art_statnum,art_memo ,art_isconsult " +
				"from fo_artical where plan_planid=" + planid );
//		System.out.println("+++++++++++++getVoteArticlelist sql+++++++++++++++"+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					FoArticalVO vo = new FoArticalVO();
					vo.setArtId(new Integer(rs.getString("art_id")));
					vo.setArtTitle(rs.getString("art_title"));
					vo.setPlanPlanid(new Integer(rs.getString("plan_planid")==null?"-1":rs.getString("plan_planid")));
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
					map.put(new Integer(rs.getString("art_id")),vo);
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
	 * 增加输入投票记录
	 * @param vo
	 */
	public Integer addInputvote(FoInputvoteVO vo) {
		FoInputvoteDAO dao = new FoInputvoteDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
			 System.out.println(" 增加一条输入投票记录表 err");
		}
		return dao.getInvoteId();
	}
	/**
	 * 取得invoteid由artid 生成的Map 
	 * @param invoteid
	 * @return
	 */
	public Map getInputValueMap(Integer invoteid) {
		List list=new ArrayList();
		list=this.getSimpleInputValueList(invoteid.toString());
		Map map=new HashMap();
		Iterator itr=list.iterator();
		while(itr.hasNext()){
			FoInputvalueVO vo=(FoInputvalueVO) itr.next();
			
			map.put(vo.getArtId(),vo);
		}
		
		
		
		return map;
	}
	/**
	 * 根据invoteid删除此投票数据值表中的数据
	 * @param invoteid
	 */
	public void deleteInputValue(Integer invoteid) {
		StringBuffer sql = new StringBuffer();
		
		PreparedStatement pstmt = null;
		
		sql.append("delete from fo_inputvalue where invote_id="+invoteid) ;
		System.out.println("+++++++++++++deleteInputValue sql+++++++++++++++"+sql);
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
	 * 根据invoteid删除输入投票记录表中信息 
	 * @param invoteid
	 */
	public void deleteInputVote(Integer invoteid) {
		FoInputvoteDAO dao = new FoInputvoteDAO();
		dao.setInvoteId(invoteid);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 增加输入投票数据表记录
	 * @param invaluevo
	 */
	public void addInputVoteValue(FoInputvalueVO invaluevo) {
		FoInputvalueDAO dao = new FoInputvalueDAO();
		dao.setValueObject(invaluevo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
			 System.out.println(" 增加输入投票数据表记录 err");
		}
		
		
	}
	/**
	 * 修改投票数据表记录
	 * @param vo
	 */
	public void updateInputValue(FoInputvalueVO vo) {
		FoInputvalueDAO dao = new FoInputvalueDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.update();
		} catch (DAOException e) {
			e.printStackTrace();
			 System.out.println(" 修改投票数据表记录 err");
		}
		
		
	}
	/**
	 * 修改计划信息
	 * @param articalvo
	 */
	public void updatePlan(FoPlanVO foplanvo) {
		FoPlanDAO dao = new FoPlanDAO();
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
	 * 取得输入投票信息表信息
	 * @param invoteid
	 * @return
	 */
	public FoInputvoteVO getInputVote(Integer invoteid) {
		FoInputvoteDAO dao = new FoInputvoteDAO();
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		dao.setInvoteId(invoteid);
		factory.setDAO(dao);
		try {
			result = factory.find(new FoInputvoteVO());
		} catch (DAOException e) {

			e.printStackTrace();
		}
		FoInputvoteVO vo=(FoInputvoteVO)result.get(0);
		return vo;
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

		sql.append("select org_name ,count(hrid) as personnum from fo_voteperson where plan_planid="+planid+" group by org_name  order by org_name " );
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

		sql.append("select org_name ,count(hrid) as personnum from fo_voteperson where plan_planid="+planid+" and flag='1' group by org_name  order by org_name ");
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
	 * 根据计划ID及文章ID取得输入投票的投票值合计
	 * @param planid
	 * @param string
	 * @return
	 */
	public int getInputvalueStat(Integer planid, String artid) {
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int	votepersonnum=0;
		sql.append("select sum(invalue_value) as votenum from FO_inputvalue,fo_inputvote  " 
				
				+"  where fo_inputvote.invote_id=fo_inputvalue.invote_id and fo_inputvalue.art_id=" + artid +" and fo_inputvote.plan_planid="+planid ) ;
//		System.out.println("+++++++++++++getArtVoteNum sql+++++++++++++++"+sql);
		try {
				pstmt = conn.prepareStatement(sql.toString());
				
				rs=pstmt.executeQuery(sql.toString());
				if(rs.next()){
					votepersonnum=rs.getInt("votenum");
				}
				return votepersonnum;
			
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
		return votepersonnum;
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
//设置文件
private void setDataFile(String filename) {
	datafile = filename ;
	System.out.println("datafile" + datafile);
}
//创建workbook对象
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
 * 取得artid的文章Map
 * @return
 */
public List getartIdList(Integer planid) {
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	List list=new ArrayList();
	sql.append("select art_id  from fo_artical  " 
			
			+"  where plan_planid="+planid);
//	System.out.println("+++++++++++++getArtVoteNum sql+++++++++++++++"+sql);
	try {
			pstmt = conn.prepareStatement(sql.toString());
			
			rs=pstmt.executeQuery(sql.toString());
			while(rs.next()){
				list.add(rs.getString("art_id"));
			}
			return list;
		
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
	return list;
}
/**
 * 进行输入投票的导入
 * @param filepath2
 * @param planid
 */
//导入数据

public List inputVoteReadExcel(String file_name, Integer voteplanid){
	setDataFile(file_name);

	initDataFile();
	int intBegin = 3;
	this.booInsertFlag = false;
	List list=new ArrayList();
	
	try {
		for (int i = intBegin; i < intRowAccount-1; i++) {
			if (booEndFlag) {
				break;
			} else {
				FoInputvotevalueVO vo=new FoInputvotevalueVO();

				System.out.println("开始装载导入文件的数据");
				vo.setInvotePerson(sheetimp.getCell(2, 0).getContents()==null?"-1":
					("".equals(sheetimp.getCell(2, 0).getContents())?"-1":sheetimp.getCell(2, 0).getContents()));
				vo.setInvotePersonnum(new Integer(sheetimp.getCell(4, 0).getContents()==null?"-1":
					("".equals(sheetimp.getCell(4, 0).getContents())?"-1":sheetimp.getCell(4, 0).getContents())));
				System.out.println("开始装载导入文件的数据2 人数="+sheetimp.getCell(4, 0).getContents()
						+"部门="+sheetimp.getCell(2, 0).getContents()
						+"文章ID="+sheetimp.getCell(1, i).getContents()
						+"票数="+sheetimp.getCell(4, i).getContents()
						
				
				
				);
				
				vo.setArtId(new Integer(sheetimp.getCell(0, i).getContents()==null?"-1":
					("".equals(sheetimp.getCell(0, i).getContents())?"-1":sheetimp.getCell(0, i).getContents())));
				vo.setInvalueValue(new Integer(sheetimp.getCell(4, i).getContents()==null?"0":
					("".equals(sheetimp.getCell(4, i).getContents())?"0":sheetimp.getCell(4, i).getContents())));
				list.add(vo);
				System.out.println("完成装载导入文件的数据！");
			}
		}

		//rs.close();
		//this.close();
		//return rslist;
	} catch (Exception ex) {
		ex.printStackTrace();
		
	}
	return list;
	//   return rslist;
}
/**
 * 根据计划ID及文章ID取得此文章各个部门的投票数量
 * @param planid
 * @param artid
 * @return
 */
public Map getArtDeptVotenum(String planid, String artid) {
	Map map = new HashMap();

	StringBuffer sql = new StringBuffer();

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	sql.append("select org_name ,sum(vote_value) as votenum from fo_vote,fo_voteperson where fo_vote.person_id=fo_voteperson.person_id and plan_planid="+planid+" and art_id="+artid+" group by org_name  order by org_name ");
//	System.out.println("+++++++++++++getVoteArticlelist sql+++++++++++++++"+sql);
	try {
		pstmt = conn.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				String	votenum="0";
				if(rs.getString("votenum")!=null){
					votenum=rs.getString("votenum");
				}
				if(rs.getString("org_name")!=null){
					map.put(rs.getString("org_name"),votenum);
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
 * 根据计划ID取得输入投票的人数合计
 * @param planid
 * @return
 */
public int getinPutVotePersonnum(Integer planid) {
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	int	votenum=0;
	sql.append("select SUM(INVOTE_PERSONNUM) as VOTEPERSON from FO_INPUTVOTE" 
			
			+"  where plan_Planid=" + planid ) ;
	System.out.println("+++++++++++++getinPutVotePersonnum sql+++++++++++++++"+sql);
	try {
			pstmt = conn.prepareStatement(sql.toString());
			
			rs=pstmt.executeQuery(sql.toString());
			if(rs.next()){
				votenum=rs.getInt("VOTEPERSON");
			}
			return votenum;
		
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
	return votenum;
}
/**
 * 取得用户列表
 * @return
 */
	public List getUserManagerList() {
		FoVotepersonDAO dao = new FoVotepersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		//dao.addOrderBy("logTime",false);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new FoVotepersonVO());
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
				Thread.sleep(1);
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
	public void updatePersonVO(FoVotepersonVO vo) {
		FoVotepersonDAO dao = new FoVotepersonDAO();
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
	 * 根据计划ID与用户名取得输入的投票列表
	 * @param planid
	 * @return
	 */
	public List getInputVoteList(String planid,String username) {
		FoInputvoteDAO dao = new FoInputvoteDAO();
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql	.append("  plan_planid =")
			.append(Integer.valueOf(planid));
		sql.append(" and INVOTE_RECORDER='")
			.append(username)
			.append("'");
		dao.setWhereClause(sql.toString());
		dao.addOrderBy(" invoteDate", false);
		factory.setDAO(dao);
		try {
			result = factory.find(new FoInputvoteVO());
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取当前登录用户所在的部门ORGID（即被考核对象组织机构ID）
	 * @return
	 */
	public String getUserOrgUuid() throws EntityException {
		//获取当前人所在第三级部门ORGUUID
		Organization curOrg = null;
		com.icss.resourceone.sdk.framework.Context ctx = null;
		ctx = com.icss.resourceone.sdk.framework.Context.getInstance();
		//UserInfo userInfo = ctx.getCurrentLoginInfo();		   
		List list_orgs = ctx.getCurrentOrganization();
		if (list_orgs.isEmpty() || list_orgs == null) {

			throw new RuntimeException("请设置当前用户所在部门");
		}
		Iterator it_orgs = list_orgs.iterator();

		while (it_orgs.hasNext()) {
			Organization org = (Organization) it_orgs.next();
			curOrg = getParentOrganization(org, 3);
			if (curOrg != null) {
				break;
			}

		}
		if (curOrg == null) {
			throw new RuntimeException("无法获取当前用户的第三层次级别组织");
		}

		System.out.println("当前用户的所在组织:" + curOrg.getName());
		//curOrg.getUuid();
		return curOrg.getName();

	}

	/**
	 * 获取当前组织的指定层次的父组织对象
	 * @param org
	 * @param orgLevel
	 * @return
	 * @throws EntityException
	 */
	public Organization getParentOrganization(Organization org, int orgLevel) throws EntityException {
		Organization parentOrg = null;

		if (org.getOrglevel().intValue() == orgLevel) {
			parentOrg = org;

		} else {

			if (org.getOrglevel().intValue() > orgLevel) {
				parentOrg = getParentOrganization(org.getParentOrg(), orgLevel);

			}
		}

		return parentOrg;
	}
	/**
	 * 更新输入投票记录信息
	 * @param inputvo
	 */
	public void updateInputVote(FoInputvoteVO inputvo) {
		FoInputvoteDAO dao = new FoInputvoteDAO();
		dao.setValueObject(inputvo);
		dao.setInvoteId(inputvo.getInvoteId());
		dao.setConnection(conn);
		try {
			dao.update();
		} catch (DAOException e) {

			e.printStackTrace();
		}
		
		
		
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
					FoVotepersonVO vo=new FoVotepersonVO();

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
	public void addVotePerson(FoVotepersonVO vo) {
		FoVotepersonDAO dao = new FoVotepersonDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
			 System.out.println(" 增加投票人员记录 err");
		}
		
		
	}
}
