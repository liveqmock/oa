/*
 * �������� 2007-6-22
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.multivote.handler;

import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;



import com.icss.oa.fo.admin.DbTrans;
import com.icss.oa.fo.dao.FoArticalDAO;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.multivote.dao.VoteArticleDAO;
import com.icss.oa.multivote.vo.VoteArticleVO;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.multivote.dao.VotePlanDAO;
import com.icss.oa.multivote.dao.VoteTableDAO;
import com.icss.oa.multivote.dao.VotePersonDAO;
import com.icss.oa.multivote.vo.*;
import com.icss.oa.multivote.dao.VoteArticleDAO;
import com.icss.oa.multivote.dao.VoteItemDAO;
import com.icss.j2ee.util.Globals;



/**
 * @author liupei
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class VoteLiuHandler {
	
	DbTrans myDbTrans;
	
	Connection conn = null;
	public Sheet sheetimp = null;
	public String datafile = ""; //�����ļ�����
	public Workbook rwb = null;
	public int intRowAccount = 0; //���������������
	public String erromessage = "";
	public boolean booInsertFlag = true;
	public boolean booEndFlag = false;
	
	/**
	 * 
	 * ���캯��
	 * @param _conn
	 */
	public VoteLiuHandler(Connection _conn) {
		this.conn = _conn;
	}
	
	
/**
 * �ڹ���ҳ����ȡ�üƻ��б�
 * @return
 */
	public List getPlanManagerList() {
		VotePlanDAO dao = new VotePlanDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("planPlanid",true);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new VotePlanVO());
			System.out.println("handler�Ѿ�ȡ��list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * ���ݼƻ�ID�ж��Ƿ�ӵ��ͶƱ������Ϣ
	 * @param planPlanid
	 * @return
	 */
	public boolean hasVoteTable(Integer planPlanid) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT count(TABLE_ID) AS NUM FROM  VOTE_TABLE WHERE PLAN_PLANID = "
				+ planPlanid.toString();
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		boolean hasVotetable=false;
		int num=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					num = rs.getInt("num");//�õ������ĸ���
					if(num>0){
						hasVotetable=true;
					}
//					System.out.println("eeee3333333     "+blob.length()); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return hasVotetable;

	}

	/**
	 * ���ݼƻ�ID�ж��Ƿ�ӵ��ͶƱ��Ա��Ϣ
	 * @param planPlanid
	 * @return
	 */
	public boolean hasVotePerson(Integer planPlanid) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT count(PERSON_ID) AS NUM FROM  VOTE_PERSON WHERE PLAN_PLANID = "
				+ planPlanid.toString();
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		boolean hasVoteperson=false;
		int num=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					num = rs.getInt("num");//�õ������ĸ���
					if(num>0){
						hasVoteperson=true;
					}
//					System.out.println("eeee3333333     "+blob.length()); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return hasVoteperson;

	}	
	
	
	
	/**
	 * ����tableID�ж��Ƿ�ӵ��������Ϣ
	 * @param tableId
	 * @return
	 */
	public int hasVoteArticle(Integer tableId) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT count(ART_ID) AS NUM FROM  VOTE_ARTICLE WHERE TABLE_ID = "
				+ tableId.toString();
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		boolean hasVotearticle=false;
		int num=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					num = rs.getInt("num");//�õ������ĸ���
					if(num>0){
						hasVotearticle=true;
					}
//					System.out.println("eeee3333333     "+blob.length()); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return num;

	}

	/**
	 * ����artID�ж��Ƿ�ӵ��ͶƱֵ��Ϣ
	 * @param tableId
	 * @return
	 */
	public int hasVoteValueByArtid(Integer artId) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT count(VOTE_ID) AS NUM FROM  VOTE_VALUE WHERE ART_ID = "
				+ artId.toString();
		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		boolean hasVotevalue=false;
		int num=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					num = rs.getInt("num");//�õ������ĸ���
					if(num>0){
						hasVotevalue=true;
					}
//					System.out.println("eeee3333333     "+blob.length()); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return num;

	}
	

	/**
	 * ����personID�ж��Ƿ�ӵ��ͶƱֵ��Ϣ
	 * @param personId
	 * @return
	 */
	public boolean hasVoteValueByPersonid(Integer personId) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT count(VOTE_ID) AS NUM FROM  VOTE_VALUE WHERE PERSON_ID = "
				+ personId.toString();
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		boolean hasVotevalue=false;
		int num=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					num = rs.getInt("num");//�õ������ĸ���
					if(num>0){
						hasVotevalue=true;
					}
//					System.out.println("eeee3333333     "+blob.length()); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return hasVotevalue;

	}	
	
	
	/**
	 * ����tableID�ж��Ƿ�ӵ��ѡ����Ϣ
	 * @param tableId
	 * @return
	 */
	public boolean hasVoteItem(Integer tableId) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT count(ITEM_ID) AS NUM FROM  VOTE_ITEM WHERE TABLE_ID = "
				+ tableId.toString();
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		boolean hasVoteitem=false;
		int num=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					num = rs.getInt("num");//�õ������ĸ���
					if(num>0){
						hasVoteitem=true;
					}
//					System.out.println("eeee3333333     "+blob.length()); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return hasVoteitem;

	}	
	
	
/**
 * ����planid�õ�votetableid
 */
	public Integer getVoteTableId(Integer planPlanid) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT TABLE_ID FROM  VOTE_TABLE WHERE PLAN_PLANID = "
				+ planPlanid.toString();
		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		Integer voteTableId=null;
		
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					String s_voteTableId=rs.getString(1);
					voteTableId = Integer.valueOf(s_voteTableId);
										
					System.out.println("voteTableId     "+voteTableId); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return voteTableId;

	}

	/**
	 * ����tableID�õ�artid
	 */
		public List getVoteArtIdlist(Integer tableId,int count) {
//			System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
			Connection conn = null;
			try {
				conn = DBConnectionLocator.getInstance().getConnection(
						com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			} catch (DBConnectionLocatorException e1) {
				e1.printStackTrace();
			}
			
			String sql = " SELECT ART_ID FROM  VOTE_ARTICLE WHERE TABLE_ID = "
					+ tableId.toString();
			System.out.println("eeee22222222222222222222222222     "+sql); 

			Statement stmt = null;
			ResultSet rs = null;
			List voteArtIdList=new ArrayList();
						
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);		
				System.out.println("count="+count);
				int i=0;						
			while(i<count){
				rs.next();
				System.out.println("��"+(i+1)+"����¼��"+rs.getString(1));
				voteArtIdList.add(rs.getString(1));				
				i++;
				
//				System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
				//if (rs != null) {System.out.println("��������м�¼");
				
					/*while (rs.next()) {					
						System.out.println("�������¼��"+rs.getString(1));
						voteArtIdList.add(rs.getString(1));																	 
					}
					System.out.println("voteArtIdListsize=     "+voteArtIdList.size());
				//}*/
				}
			System.out.println("voteArtIdListsize=  "+voteArtIdList.size());
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
				}
				if(stmt!=null){
					try {
						stmt.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
				}
			}
			
			return voteArtIdList;

		}	
		
		
		/**
		 * ����tableID�õ�artid
		 */
			public List getVoteValueIdlist(Integer artId,int count) {
//				System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
				Connection conn = null;
				try {
					conn = DBConnectionLocator.getInstance().getConnection(
							com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				} catch (DBConnectionLocatorException e1) {
					e1.printStackTrace();
				}
				
				String sql = " SELECT VALUE_ID FROM  VOTE_VALUE WHERE ART_ID = "
						+ artId.toString();
				System.out.println("eeee22222222222222222222222222     "+sql); 

				Statement stmt = null;
				ResultSet rs = null;
				List voteValueIdList=new ArrayList();
							
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);		
					System.out.println("count="+count);
					int i=0;						
				while(i<count){
					rs.next();
					System.out.println("��"+(i+1)+"����¼��"+rs.getString(1));
					voteValueIdList.add(rs.getString(1));				
					i++;
					
//					System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
					//if (rs != null) {System.out.println("��������м�¼");
					
						/*while (rs.next()) {					
							System.out.println("�������¼��"+rs.getString(1));
							voteArtIdList.add(rs.getString(1));																	 
						}
						System.out.println("voteArtIdListsize=     "+voteArtIdList.size());
					//}*/
					}
				System.out.println("voteValueIdListsize=  "+voteValueIdList.size());
				} catch (SQLException e) {
					e.printStackTrace();
					
				} finally {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
							
						}
					}
					if(stmt!=null){
						try {
							stmt.close();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
							
						}
					}
				}
				
				return voteValueIdList;

			}	
		


		/**
		 * ����planPlanid�õ�personidlist
		 */
			public List getVotePersonIdlist(Integer planPlanid) {
//				System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
				Connection conn = null;
				try {
					conn = DBConnectionLocator.getInstance().getConnection(
							com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				} catch (DBConnectionLocatorException e1) {
					e1.printStackTrace();
				}
				
				String sql = " SELECT PERSON_ID FROM  VOTE_PERSON WHERE PLAN_PLANID = "
						+ planPlanid.toString();
				System.out.println("eeee22222222222222222222222222     "+sql); 

				Statement stmt = null;
				ResultSet rs = null;
				List votePersonIdList=null;
				
				
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
//					System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
					if (rs != null) {
						while (rs.next()) {
							votePersonIdList.add(rs.getString(1));											
							System.out.println("votePersonIdListsize=     "+votePersonIdList.size()); 
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					
				} finally {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
							
						}
					}
					if(stmt!=null){
						try {
							stmt.close();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
							
						}
					}
				}
				
				return votePersonIdList;

			}			
		
	
/**
 * ���Ӽƻ�
 * @param VotePlanVo
 */
public void addPlan(VotePlanVO vo) {
	VotePlanDAO dao = new VotePlanDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * ���ӱ���
 * @param VoteTableVO
 */
public void addTable(VoteTableVO vo) {
	VoteTableDAO dao = new VoteTableDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
		System.out.println("��ӱ������");
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * ����article
 * @param VoteArticleVO
 */
public void addArticle(VoteArticleVO vo) {
	VoteArticleDAO dao = new VoteArticleDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
		System.out.println("��ӿ����");
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * ����item
 * @param VoteItemVO
 */
public void addItem(VoteItemVO vo) {
	VoteItemDAO dao = new VoteItemDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
		System.out.println("���ѡ�����");
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * ��������IDɾ������
 * @param artId
 */
public void deleteArticleByArtid(Integer artId) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_ARTICLE WHERE ART_ID = "
			+ artId.toString();
	System.out.println("deleteArticle     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	
}

/**
 * ����ѡ��IDɾ��ѡ��
 * @param itemId
 */
public void deleteItemByItemid(Integer itemId) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_ITEM WHERE ITEM_ID = "
			+ itemId.toString();
	System.out.println("deleteItem     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	
}



/**
 * ���ݼƻ�IDɾ�����ͶƱ����
 * @param planPlanid
 */
public void deleteVotetable(Integer planPlanid) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_TABLE WHERE PLAN_PLANID = "
			+ planPlanid.toString();
	System.out.println("deleteVotetable     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		System.out.println("��ɾ����¼");
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	


}

/**
 * ���ݼƻ�IDɾ�����ͶƱ��Ա
 * @param planPlanid
 */
public void deleteVoteperson(Integer planPlanid) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_PERSON WHERE PLAN_PLANID = "
			+ planPlanid.toString();
	System.out.println("deleteVoteperson     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		System.out.println("��ɾ����Ա��¼");
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	


}



/**
 * �ڹ���ҳ����ȡ�ñ������б�
 * @param planPlanid
 * @return
 */
public List getVoteTableList(Integer planPlanid) {
	VoteTableDAO dao = new VoteTableDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setPlanPlanid(planPlanid);
	dao.addOrderBy("tableId",true);//���ձ�����ID������
	factory.setDAO(dao);
	
	List list = null;
	try {
		list = factory.find(new VoteTableVO());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}


/**
 * �ڹ���ҳ����ȡ������ѡ����б�
 * @param tableId
 * @return
 */
public List getarticalOptionList(Integer tableId) {
	VoteArticleDAO dao = new VoteArticleDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setTableId(tableId);
	dao.addOrderBy("artId",true);//�������µ�ID������
	factory.setDAO(dao);
	
	List list = null;
	try {
		list = factory.find(new VoteArticleVO());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

/**
 * �ڹ���ҳ����ȡ��ͶƱ��Ŀ���б�
 * @param tableId
 * @return
 */
public List getvoteItemList(Integer tableId) {
	VoteItemDAO dao = new VoteItemDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setTableId(tableId);
	dao.addOrderBy("itemId",true);//�������µ�ID������
	factory.setDAO(dao);
	
	List list = null;
	try {
		list = factory.find(new VoteItemVO());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

/**
 * ����artIdȡ��VoteArtileVO
 * @param artId
 * @return
 */
public VoteArticleVO getArticleVo(Integer artId) {
	// TODO Auto-generated method stub
	VoteArticleVO vo=new VoteArticleVO();
	VoteArticleDAO dao = new VoteArticleDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setArtId(artId);
	factory.setDAO(dao);
	try {
		vo=(VoteArticleVO) factory.findByPrimaryKey(new VoteArticleVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}

/**
 * ����itemIdȡ��VoteItemVO
 * @param artId
 * @return
 */
public VoteItemVO getItemVo(Integer itemId) {
	// TODO Auto-generated method stub
	VoteItemVO vo=new VoteItemVO();
	VoteItemDAO dao = new VoteItemDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setItemId(itemId);
	factory.setDAO(dao);
	try {
		vo=(VoteItemVO) factory.findByPrimaryKey(new VoteItemVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}

/**
 * �޸����¼�¼��Ϣ
 * @param voteartvo
 */
public void updateArticle(VoteArticleVO voteartvo) {
	VoteArticleDAO dao = new VoteArticleDAO();
	dao.setValueObject(voteartvo);
	dao.setConnection(conn);
	try {
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * �޸ı�����¼��Ϣ
 * @param votetablevo
 */
public void updateTable(VoteTableVO votetablevo) {
	VoteTableDAO dao = new VoteTableDAO();
	dao.setValueObject(votetablevo);
	dao.setConnection(conn);
	try {
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}


/**
 * �޸�ѡ����Ϣ
 * @param voteitemvo
 */
public void updateItem(VoteItemVO voteitemvo) {
	VoteItemDAO dao = new VoteItemDAO();
	dao.setValueObject(voteitemvo);
	dao.setConnection(conn);
	try {
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * ����TableIDɾ���������ѡ���¼
 * @param voteTableId
 */
public void deleteArtical(Integer voteTableId) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_ARTICLE WHERE TABLE_ID = "
			+ voteTableId.toString();
	System.out.println("deleteArticle     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	


}

/**
 * ����ArtIDɾ���������ѡ���¼
 * @param artId
 */
public void deleteValueByArtid(Integer artId) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_VALUE WHERE ART_ID = "
			+ artId.toString();
	System.out.println("deleteValue     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	


}


/**
 * ����personIdɾ���������ѡ���¼
 * @param personId
 */
public void deleteValueByPersonid(Integer personId) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_VALUE WHERE PERSON_ID = "
			+ personId.toString();
	System.out.println("deleteValue     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	


}


/**
 * ����tableIDɾ���������ѡ���¼
 * @param planPlanid
 */
public void deleteItem(Integer voteTableId) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_ITEM WHERE TABLE_ID = "
			+ voteTableId.toString();
	System.out.println("deleteItem     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	


}


/**
 * ���ݼƻ�IDɾ���ƻ�
 * @param planPlanid
 */
public void deletePlan(Integer planPlanid) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  VOTE_PLAN WHERE PLAN_PLANID = "
			+ planPlanid.toString();
	System.out.println("deletePlan     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
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
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	
}

/**
 * �޸ļƻ���¼��Ϣ
 * @param foplanvo
 */
public void updatePlan(VotePlanVO voteplanvo) {
	VotePlanDAO dao = new VotePlanDAO();
	dao.setValueObject(voteplanvo);
	dao.setConnection(conn);
	try {
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * ����tableIdȡ��VoteTableVO
 * @param tableId
 * @return
 */
public VoteTableVO getTableVo(Integer tableId) {
	// TODO Auto-generated method stub
	VoteTableVO vo=new VoteTableVO();
	VoteTableDAO dao = new VoteTableDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setTableId(tableId);
	factory.setDAO(dao);
	try {
		vo=(VoteTableVO) factory.findByPrimaryKey(new VoteTableVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}

/**
 * ����planPlanidȡ��FoPlanVO
 * @param planPlanid
 * @return
 */
public VotePlanVO getPlanVo(Integer planPlanid) {
	// TODO Auto-generated method stub
	VotePlanVO vo=new VotePlanVO();
	VotePlanDAO dao = new VotePlanDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setPlanPlanid(planPlanid);
	factory.setDAO(dao);
	try {
		vo=(VotePlanVO) factory.findByPrimaryKey(new VotePlanVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}



}