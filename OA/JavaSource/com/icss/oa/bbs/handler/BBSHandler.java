/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.handler;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import com.icss.oa.bbs.dao.*;
import com.icss.oa.bbs.vo.*;
import com.icss.oa.hr.dao.HRSysPersonDAO;
import com.icss.oa.hr.vo.HRSysPersonVO;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BBSHandler {
	private Connection conn;

	public BBSHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 得到所有的待审批的板块
	 * 
	 * @author Administrator
	 * 
	 */
	public List getApplyBoardlist() {
		List list = new ArrayList();
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setApplyflag("0");
		dao.addOrderBy("creattime", false);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new BbsBoardVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 审批一个申请的板块
	 * 
	 * @author Administrator
	 * 
	 */
	public void auditApplyBoard(Integer boardId) {
		List list = new ArrayList();
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setBoardid(boardId);
		dao.setConnection(conn);
		try {
			dao.setApplyflag("1");
			dao.update(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断一个申请的板块是否已经被审批
	 * 
	 * @author Administrator
	 * 
	 */
	public boolean isAuditBoard(Integer boardId) {
		boolean isAudit = true;
		List list = new ArrayList();
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setBoardid(boardId);
		dao.setApplyflag("0");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new BbsBoardVO());
			if (list != null && list.size() > 0) {
				isAudit = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAudit;
	}

	/**
	 * 判断登录用户是不是公用账号
	 * 
	 * @author Administrator
	 * 
	 */
	public boolean isPublicUserid(String userid) {
		boolean ispub = true;
		SimpleApplyBaseDAO dao = new SimpleApplyBaseDAO();
		dao.setUserid(userid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = new ArrayList();
		try {
			list = factory.find(new SimpleApplyBaseVO());
			if (list.size() > 0) {
				ispub = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ispub;
	}

	/**
	 * get board list
	 * 
	 * @author Administrator
	 * 
	 */
	public List getBoardList() {
		// BbsBoardDAO dao = new BbsBoardDAO();
		// DAOFactory factory = new DAOFactory(conn);
		// dao.setApplyflag("1");
		// factory.setDAO(dao);
		List list = null;
		// try {
		// list = (List)factory.find(new BbsBoardVO());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//		

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BOARDID,AREAID,BOARDNAME,CREATTIME,ARTICLENUM,TOPICNUM,BOARDDES,LASTTIME,LASTARTICLEID,LASTID,ISLIMITED,"
						+ "ISAUDIT,ISWORK,ISVIEW,PERMIT,LASTARTICLENAME,LASTUSERNAME,NOTICEID,APPLYFLAG,APPLYPERSONUUID ");
		sql.append("FROM ");
		sql.append("BBS_BOARD ");
		sql.append(" where APPLYFLAG ='1' order by boardid");
		// System.out.println("------------ "+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			int i = 0;
			list = new ArrayList();
			while (rs.next()) {
				BbsBoardVO boardvo = new BbsBoardVO();
				boardvo.setBoardid(rs.getString("BOARDID") == null ? null
						: Integer.valueOf(rs.getString("BOARDID")));
				boardvo.setAreaid(rs.getString("AREAID") == null ? null
						: Integer.valueOf(rs.getString("AREAID")));
				boardvo.setBoardname(rs.getString("BOARDNAME"));
				boardvo.setCreattime(rs.getString("AREAID") == null ? null
						: Long.valueOf(rs.getString("CREATTIME")));
				boardvo.setArticlenum(rs.getString("ARTICLENUM") == null ? null
						: Integer.valueOf(rs.getString("ARTICLENUM")));
				boardvo.setTopicnum(rs.getString("TOPICNUM") == null ? null
						: Integer.valueOf(rs.getString("TOPICNUM")));
				boardvo.setBoarddes(rs.getString("BOARDDES"));
				boardvo.setLasttime(rs.getString("LASTTIME") == null ? null
						: Long.valueOf(rs.getString("LASTTIME")));
				boardvo
						.setLastarticleid(rs.getString("LASTARTICLEID") == null ? null
								: Integer
										.valueOf(rs.getString("LASTARTICLEID")));
				boardvo.setLastid(rs.getString("LASTID"));
				boardvo.setIslimited(rs.getString("ISLIMITED"));
				boardvo.setIsaudit(rs.getString("ISAUDIT"));
				boardvo.setIswork(rs.getString("ISWORK"));
				boardvo.setIsview(rs.getString("ISVIEW"));
				boardvo.setPermit(rs.getString("PERMIT"));
				boardvo.setLastarticlename(rs.getString("LASTARTICLENAME"));
				boardvo.setLastusername(rs.getString("LASTUSERNAME"));
				boardvo.setNoticeid(rs.getString("NOTICEID") == null ? null
						: Integer.valueOf(rs.getString("NOTICEID")));
				boardvo.setApplyflag(rs.getString("APPLYFLAG"));
				boardvo.setApplypersonuuid(rs.getString("APPLYPERSONUUID"));
				i++;
				// System.out.println("hander.managelist=sql方式:" + i);
				list.add(boardvo);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 根据人员信息和分区信息获取该人员在指定分区下有权进入的版块
	 * 
	 * @param String
	 *            userid
	 * @param String
	 *            areaids 分区id,如果有多个分区以逗号分隔,并且字符串末尾无逗号
	 * @author zhanggk
	 * 
	 */
	public List getBoardByPersonList(String userid, String areaids) {
		List list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2=null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("distinct A.BOARDID,A.AREAID,A.BOARDNAME,A.CREATTIME,A.ARTICLENUM,A.TOPICNUM,A.BOARDDES,A.LASTTIME,A.LASTARTICLEID,A.LASTID,A.ISLIMITED,"
						+ "A.ISAUDIT,A.ISWORK,A.ISVIEW,A.PERMIT,A.LASTARTICLENAME,A.LASTUSERNAME,A.NOTICEID,A.APPLYFLAG,A.APPLYPERSONUUID ");
		sql.append("FROM ");
		// sql.append("BBS_BOARD A, BBS_BOARDACC B ");
		sql.append("BBS_BOARD A ");
		sql.append(" where A.APPLYFLAG ='1' AND (A.ISLIMITED='1' ");
		sql.append(" and A.BOARDID NOT IN ( ");
		sql
				.append(" SELECT DISTINCT A.BOARDID FROM BBS_BOARD A,BBS_BOARDACC C ");
		sql.append(" WHERE (( A.BOARDID=C.BOARDID AND (A.PERMIT='0' ");
		sql.append(" AND C.USERID='" + userid + "')))) OR A.ISLIMITED='0') ");
		// sql.append(" OR (A.PERMIT='1' AND C.USERID='"+userid+"')))) ");

		// sql.append(" and (A.ISLIMITED = '0' OR (ISLIMITED='1' AND
		// A.BOARDID=B.BOARDID AND B.USERID='").append(userid).append("'))");
		sql.append(" and A.AREAID IN (").append(areaids).append(
				")  order by boardid ");
		System.out.println("------------ " + sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			int i = 0;
			list = new ArrayList();
			while (rs.next()) {
				BbsBoardVO boardvo = new BbsBoardVO();
				boardvo.setBoardid(rs.getString("BOARDID") == null ? null
						: Integer.valueOf(rs.getString("BOARDID")));
				boardvo.setAreaid(rs.getString("AREAID") == null ? null
						: Integer.valueOf(rs.getString("AREAID")));
				boardvo.setBoardname(rs.getString("BOARDNAME"));
				boardvo.setCreattime(rs.getString("AREAID") == null ? null
						: Long.valueOf(rs.getString("CREATTIME")));
				boardvo.setArticlenum(rs.getString("ARTICLENUM") == null ? null
						: Integer.valueOf(rs.getString("ARTICLENUM")));
				boardvo.setTopicnum(rs.getString("TOPICNUM") == null ? null
						: Integer.valueOf(rs.getString("TOPICNUM")));
				boardvo.setBoarddes(rs.getString("BOARDDES"));
				boardvo.setLasttime(rs.getString("LASTTIME") == null ? null
						: Long.valueOf(rs.getString("LASTTIME")));
				boardvo
						.setLastarticleid(rs.getString("LASTARTICLEID") == null ? null
								: Integer
										.valueOf(rs.getString("LASTARTICLEID")));
				boardvo.setLastid(rs.getString("LASTID"));
				boardvo.setIslimited(rs.getString("ISLIMITED"));
				boardvo.setIsaudit(rs.getString("ISAUDIT"));
				boardvo.setIswork(rs.getString("ISWORK"));
				boardvo.setIsview(rs.getString("ISVIEW"));
				boardvo.setPermit(rs.getString("PERMIT"));
				boardvo.setLastarticlename(rs.getString("LASTARTICLENAME"));
				boardvo.setLastusername(rs.getString("LASTUSERNAME"));
				boardvo.setNoticeid(rs.getString("NOTICEID") == null ? null
						: Integer.valueOf(rs.getString("NOTICEID")));
				boardvo.setApplyflag(rs.getString("APPLYFLAG"));
				boardvo.setApplypersonuuid(rs.getString("APPLYPERSONUUID"));
				i++;
				System.out.println("hander.managelist=sql方式:" + i);
				list.add(boardvo);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	/**
	 * get board list by areaid
	 * 
	 * @author Administrator
	 * 
	 */
	public List getBoardByAreaidList(Integer areaid, String personuuid) {
		List relist = new ArrayList();
		BbsBoardDAO dao = new BbsBoardDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setAreaid(areaid);
		dao.setApplyflag("1");
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new BbsBoardVO());
			if (list != null) {
				Iterator Itr = list.iterator();
				while (Itr.hasNext()) {
					boolean hasright;
					BbsBoardVO bVO = (BbsBoardVO) Itr.next();
					Integer boardid = bVO.getBoardid();
					hasright = this.getRight(personuuid, boardid);
					if (hasright)
						relist.add(bVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relist;
	}

	/**
	 * get board list by areaid
	 * 
	 * @author Administrator
	 * 
	 */
	public List getBoardByAreaidList(Integer areaid) {
		List relist = new ArrayList();
		BbsBoardDAO dao = new BbsBoardDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setAreaid(areaid);
		dao.setApplyflag("1");
		factory.setDAO(dao);
		try {
			relist = factory.find(new BbsBoardVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relist;
	}

	/**
	 * get user right list
	 * 
	 * @author firecoral
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public boolean getRight(String userId, Integer boardId) {
		BbsBoardaccDAO dao = new BbsBoardaccDAO();
		dao.setUserid(userId);
		dao.setBoardid(boardId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsBoardaccVO());
			if (list != null && list.size() > 0)
				return false;
			else
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * get board userinfo article search list
	 * 
	 * @return
	 */
	/*
	 * public List getMulitBoardList() { ArticleBoardUserSearchDAO dao = new
	 * ArticleBoardUserSearchDAO(); DAOFactory factory = new DAOFactory(conn);
	 * factory.setDAO(dao); List list = null; try { list = factory.find(new
	 * ArticleBoardUserVO()); } catch (Exception e) { e.printStackTrace(); }
	 * return list; }
	 */

	/**
	 * get subarea list
	 * 
	 * @author firecoral
	 * 
	 */
	public List getSubareaList() {
		// BbsAreaDAO dao = new BbsAreaDAO();
		// DAOFactory factory = new DAOFactory(conn);
		// factory.setDAO(dao);
		List list = null;
		// try {
		// list = factory.find(new BbsAreaVO());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" AREAID,AREADES,AREANAME,AREARIGHT ");
		sql.append("FROM ");
		sql.append("BBS_AREA ");
		// System.out.println("------------ "+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			int i = 0;
			list = new ArrayList();
			while (rs.next()) {
				BbsAreaVO areavo = new BbsAreaVO();
				areavo.setAreaid(rs.getString("AREAID") == null ? null
						: Integer.valueOf(rs.getString("AREAID")));
				areavo.setAreades(rs.getString("AREADES"));
				areavo.setAreaname(rs.getString("AREANAME"));
				areavo.setArearight(rs.getString("AREARIGHT"));
				// i++;
				// System.out.println("hander.managelist=sql方式:" + i);
				list.add(areavo);
			}

			// pstmt.close();
			// rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 获取有权进入的分区
	 * 
	 * @author firecoral
	 * 
	 */
	public List getSubareaList(String orguuid) {
		// BbsAreaDAO dao = new BbsAreaDAO();
		// DAOFactory factory = new DAOFactory(conn);
		// factory.setDAO(dao);
		List list = null;
		// try {
		// list = factory.find(new BbsAreaVO());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" AREAID,AREADES,AREANAME,AREARIGHT ");
		sql.append("FROM ");
		sql.append("BBS_AREA WHERE AREARIGHT='" + orguuid
				+ "' OR AREARIGHT='0' OR AREARIGHT IS NULL OR AREARIGHT=''");
		System.out.println("------------ " + sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			int i = 0;
			list = new ArrayList();
			while (rs.next()) {
				BbsAreaVO areavo = new BbsAreaVO();
				areavo.setAreaid(rs.getString("AREAID") == null ? null
						: Integer.valueOf(rs.getString("AREAID")));
				areavo.setAreades(rs.getString("AREADES"));
				areavo.setAreaname(rs.getString("AREANAME"));
				areavo.setArearight(rs.getString("AREARIGHT"));
				// i++;
				// System.out.println("hander.managelist=sql方式:" + i);
				list.add(areavo);
			}

			// pstmt.close();
			// rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 获取所有公共分区
	 * 
	 * @author zhanggk
	 * 
	 */
	public List getPublicSubareaList() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();

		StringBuffer sql = new StringBuffer();
		// 查询所有公共分区,arearight==0或者为空的记录
		sql.append("SELECT ");
		sql.append(" AREAID,AREADES,AREANAME,AREARIGHT ");
		sql.append("FROM ");
		sql
				.append("BBS_AREA WHERE AREARIGHT='0' OR AREARIGHT IS NULL OR AREARIGHT=''");
		System.out.println("------------ " + sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			int i = 0;
			list = new ArrayList();
			while (rs.next()) {
				BbsAreaVO areavo = new BbsAreaVO();
				areavo.setAreaid(rs.getString("AREAID") == null ? null
						: Integer.valueOf(rs.getString("AREAID")));
				areavo.setAreades(rs.getString("AREADES"));
				areavo.setAreaname(rs.getString("AREANAME"));
				areavo.setArearight(rs.getString("AREARIGHT"));
				list.add(areavo);
			}

			// pstmt.close();
			// rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * get subarea list
	 * 
	 * @author firecoral
	 * 
	 */
	public List getAreaByPersonList(String userid) {
		// BbsAreaDAO dao = new BbsAreaDAO();
		// DAOFactory factory = new DAOFactory(conn);
		// factory.setDAO(dao);
		List list = null;
		// try {
		// list = factory.find(new BbsAreaVO());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" distinct A.AREAID,A.AREADES,A.AREANAME,A.AREARIGHT ");
		sql.append("FROM ");
		sql.append("BBS_AREA A,BBS_AREACC C ");
		sql
				.append(
						"WHERE A.AREAID=C.AREAID AND A.AREARIGHT='1' AND C.USERID='")
				.append(userid).append("' ");
		sql.append(" ORDER BY A.AREAID ");
		// System.out.println("------------ "+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			// int i=0;
			list = new ArrayList();
			while (rs.next()) {
				BbsAreaVO areavo = new BbsAreaVO();
				areavo.setAreaid(rs.getString("AREAID") == null ? null
						: Integer.valueOf(rs.getString("AREAID")));
				areavo.setAreades(rs.getString("AREADES"));
				areavo.setAreaname(rs.getString("AREANAME"));
				areavo.setArearight(rs.getString("AREARIGHT"));
				// i++;
				// System.out.println("hander.managelist=sql方式:" + i);
				list.add(areavo);
			}

			// pstmt.close();
			// rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * get subarea list
	 * 
	 * @author firecoral
	 * 
	 */
	public List getAreaByOrgList(String orgid) {
		// BbsAreaDAO dao = new BbsAreaDAO();
		// DAOFactory factory = new DAOFactory(conn);
		// factory.setDAO(dao);
		List list = null;
		// try {
		// list = factory.find(new BbsAreaVO());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" distinct A.AREAID,A.AREADES,A.AREANAME,A.AREARIGHT ");
		sql.append("FROM ");
		sql.append("BBS_AREA A ");
		sql.append(" WHERE A.AREARIGHT IS NOT NULL AND A.AREARIGHT='").append(
				orgid).append("' ");
		sql.append(" ORDER BY A.AREAID ");
		System.out.println("------------ " + sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			// int i=0;
			list = new ArrayList();
			while (rs.next()) {
				BbsAreaVO areavo = new BbsAreaVO();
				areavo.setAreaid(rs.getString("AREAID") == null ? null
						: Integer.valueOf(rs.getString("AREAID")));
				areavo.setAreades(rs.getString("AREADES"));
				areavo.setAreaname(rs.getString("AREANAME"));
				areavo.setArearight(rs.getString("AREARIGHT"));
				// i++;
				// System.out.println("hander.managelist=sql方式:" + i);
				list.add(areavo);
			}

			// pstmt.close();
			// rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * get subarea list
	 * 
	 * @author firecoral
	 * 
	 */
	public List getAllareaList(String personuuid) {
		BbsAreaDAO dao = new BbsAreaDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List TreeAreaList = new ArrayList();
		List list = null;
		try {
			list = factory.find(new BbsAreaVO());
			if (list != null && list.size() > 0) {
				Iterator Itr = list.iterator();
				while (Itr.hasNext()) {
					BBSLeftTreeVO vo = new BBSLeftTreeVO();
					BbsAreaVO vo1 = (BbsAreaVO) Itr.next();
					vo.setVO(vo1);
					vo.setHasChild(this.hasBoard(vo1.getAreaid(), personuuid));
					TreeAreaList.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TreeAreaList;
	}

	/**
	 * 判断专区下是否有板块
	 * 
	 * @author firecoral
	 * 
	 */
	public boolean hasBoard(Integer areaId, String personuuid) {
		try {
			List list = this.getBoardByAreaidList(areaId, personuuid);
			if (list != null && list.size() > 0) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 得到一个板块下的所有贴子
	 * 
	 * @author firecoral
	 * 
	 */
	public List getArticleByBoardId(Integer boardId) {
		List list = new ArrayList();
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setBoardid(boardId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new BbsArticleVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get article list by id
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void updateArticleWhenMergeBoard(Integer articleId,
			Integer newBoardId) {
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao.setBoardid(newBoardId);
			dao.update(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get article list
	 * 
	 * @author Administrator
	 * 
	 */
	public List getArticleList(Integer topicId, String isview) {
		ArticleUserinfoSearchDAO dao = new ArticleUserinfoSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_ARTICLE,BBS_USERINFO ");
		sql.append(" where BBS_ARTICLE.USERID = BBS_USERINFO.USERID "
				+ " and (reid = " + topicId + " or articleid = " + topicId
				+ ") ");
		if (isview == null) // 查询可见贴
			sql.append("and isview = '1' ");
		else if (isview.equals("0")) // 查询不可见贴
			sql.append("and isview = '0' ");
		else if (isview.equals("1")) { // 查询所有贴
		}
		sql.append("order by topic desc ,emittime ");
		dao.setSearchSQL(sql.toString());

		// System.out.println("..............get articl list sql "+sql);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ArticleUserinfoVO());
			System.out.println("..............list size is " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据人员版块,获取公共分区中的帖子
	 * 
	 * @author zhanggk
	 * 
	 */
	public List getTopicListByBoardids(String boardids) {
		List relist = new ArrayList();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT");
		searchsql
				.append(" BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.USERID AS PERSONUUID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_ARTICLE.TOINTEND");
		searchsql.append(" FROM");
		// 是主题并且可读
		searchsql
				.append(" BBS_ARTICLE WHERE BBS_ARTICLE.TOPIC='1' AND BBS_ARTICLE.ISVIEW='1' ");
		// 当前人员有权读此版块
		searchsql.append(" AND ");
		searchsql.append(" BBS_ARTICLE.BOARDID IN (" + boardids + ")");

		BbsArticleBbsBoardaccSearchDAO dao = new BbsArticleBbsBoardaccSearchDAO();
		// System.out.println("the sql is:..." + searchsql.toString());
		dao.setSearchSQL(searchsql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			relist = factory.find(new BbsArticleBbsBoardaccVO());
		} catch (DAOException e) {
			// System.out.println("search failed!..............");
			e.printStackTrace();
		}
		return relist;
	}

	/**
	 * get newtopic list,用户登录的首页
	 * 
	 * @author firecoral
	 * @param boardids
	 * 
	 */
	public List getNewTopicList(String personuuid, String orguuid,
			String boardids) {
		List relist = new ArrayList();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT");
		searchsql
				.append(" BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.USERID AS PERSONUUID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_ARTICLE.TOINTEND");
		searchsql.append(" FROM");
		// 是主题并且可读
		searchsql
				.append(" BBS_ARTICLE WHERE BBS_ARTICLE.TOPIC='1' AND BBS_ARTICLE.ISVIEW='1' ");
		// 当前人员有权读此版块
		searchsql.append(" AND ");
		searchsql
				.append(" BBS_ARTICLE.BOARDID IN (SELECT B.BOARDID FROM BBS_BOARD B WHERE ");
		// 分区限制
		searchsql
				.append(" B.AREAID IN (SELECT AREAID FROM BBS_AREA A WHERE A.AREARIGHT IS NULL ");
		searchsql.append(" OR A.AREARIGHT='0' OR A.AREARIGHT='" + orguuid
				+ "') ");
		// 黑名单限制
		searchsql
				.append(" AND ((B.PERMIT='0' AND B.BOARDID NOT IN((SELECT BBS_BOARDACC.BOARDID ");
		searchsql.append(" FROM BBS_BOARDACC ");
		searchsql.append(" WHERE BBS_BOARDACC.USERID = ");
		searchsql.append(" '" + personuuid + "')))");
		// 白名单限制
		searchsql.append(" OR (B.PERMIT='1' ");
		searchsql
				.append(" AND B.BOARDID IN ((SELECT BBS_BOARDACC.BOARDID FROM BBS_BOARDACC ");
		searchsql.append(" WHERE BBS_BOARDACC.USERID = '" + personuuid
				+ "')))))");
		searchsql
				.append("  ORDER BY BBS_ARTICLE.TOP desc,BBS_ARTICLE.RETIME DESC");

		// "(BBS_ARTICLE.BOARDID NOT IN");
		// searchsql.append(" (SELECT BBS_BOARDACC.BOARDID FROM BBS_BOARDACC
		// WHERE BBS_BOARDACC.USERID='" + personuuid + "'))");
		// searchsql.append(
		// " AND BBS_ARTICLE.TOPIC='1'AND BBS_ARTICLE.ISVIEW='1' AND
		// ((BBS_ARTICLE.BOARDID IN (SELECT BBS_BOARD.BOARDID FROM BBS_BOARD
		// WHERE BBS_BOARD.AREAID IN (SELECT BBS_AREA.AREAID FROM BBS_AREA WHERE
		// BBS_AREA.AREARIGHT is null)) )OR (BBS_ARTICLE.BOARDID IN ");
		// searchsql.append(
		// " (SELECT BBS_BOARD.BOARDID FROM BBS_BOARD WHERE BBS_BOARD.AREAID IN
		// (SELECT BBS_AREACC.AREAID FROM BBS_AREACC WHERE BBS_AREACC.USERID='"
		// + personuuid
		// + "'))))");
		// searchsql.append(" ORDER BY BBS_ARTICLE.RETIME DESC");
		BbsArticleBbsBoardaccSearchDAO dao = new BbsArticleBbsBoardaccSearchDAO();
		System.out.println("the sql is:..." + searchsql.toString());
		dao.setSearchSQL(searchsql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			relist = factory.find(new BbsArticleBbsBoardaccVO());
		} catch (DAOException e) {
			// System.out.println("search failed!..............");
			e.printStackTrace();
		}
		return relist;
	}

	public List getNewTopicListName(String personuuid, String orguuid,
			String boardids,String name) {
		List relist = new ArrayList();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT");
		searchsql
				.append(" BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.USERID AS PERSONUUID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_ARTICLE.TOINTEND");
		searchsql.append(" FROM");
		// 是主题并且可读
		searchsql
				.append(" BBS_ARTICLE WHERE BBS_ARTICLE.TOPIC='1' AND BBS_ARTICLE.ISVIEW='1' ");
		// 当前人员有权读此版块
		searchsql.append(" AND ");
		searchsql
				.append(" BBS_ARTICLE.BOARDID IN (SELECT B.BOARDID FROM BBS_BOARD B WHERE ");
		// 分区限制
		searchsql
				.append(" B.AREAID IN (SELECT AREAID FROM BBS_AREA A WHERE A.AREARIGHT IS NULL ");
		searchsql.append(" OR A.AREARIGHT='0' OR A.AREARIGHT='" + orguuid
				+ "') ");
		// 黑名单限制
		searchsql
				.append(" AND ((B.PERMIT='0' AND B.BOARDID NOT IN((SELECT BBS_BOARDACC.BOARDID ");
		searchsql.append(" FROM BBS_BOARDACC ");
		searchsql.append(" WHERE BBS_BOARDACC.USERID = ");
		searchsql.append(" '" + personuuid + "')))");
		// 白名单限制
		searchsql.append(" OR (B.PERMIT='1' ");
		searchsql
				.append(" AND B.BOARDID IN ((SELECT BBS_BOARDACC.BOARDID FROM BBS_BOARDACC ");
		searchsql.append(" WHERE BBS_BOARDACC.USERID = '" + personuuid
				+ "')))))");
		searchsql
				.append(" AND BBS_ARTICLE.ARTICLENAME like '%"+ name +"%' ORDER BY BBS_ARTICLE.TOP desc,BBS_ARTICLE.RETIME DESC");

		// "(BBS_ARTICLE.BOARDID NOT IN");
		// searchsql.append(" (SELECT BBS_BOARDACC.BOARDID FROM BBS_BOARDACC
		// WHERE BBS_BOARDACC.USERID='" + personuuid + "'))");
		// searchsql.append(
		// " AND BBS_ARTICLE.TOPIC='1'AND BBS_ARTICLE.ISVIEW='1' AND
		// ((BBS_ARTICLE.BOARDID IN (SELECT BBS_BOARD.BOARDID FROM BBS_BOARD
		// WHERE BBS_BOARD.AREAID IN (SELECT BBS_AREA.AREAID FROM BBS_AREA WHERE
		// BBS_AREA.AREARIGHT is null)) )OR (BBS_ARTICLE.BOARDID IN ");
		// searchsql.append(
		// " (SELECT BBS_BOARD.BOARDID FROM BBS_BOARD WHERE BBS_BOARD.AREAID IN
		// (SELECT BBS_AREACC.AREAID FROM BBS_AREACC WHERE BBS_AREACC.USERID='"
		// + personuuid
		// + "'))))");
		// searchsql.append(" ORDER BY BBS_ARTICLE.RETIME DESC");
		BbsArticleBbsBoardaccSearchDAO dao = new BbsArticleBbsBoardaccSearchDAO();
		System.out.println("the sql is:..." + searchsql.toString());
		dao.setSearchSQL(searchsql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			relist = factory.find(new BbsArticleBbsBoardaccVO());
		} catch (DAOException e) {
			// System.out.println("search failed!..............");
			e.printStackTrace();
		}
		return relist;
	}
	/**
	 * get article list
	 * 
	 * @author Administrator
	 * 
	 */
	public List getTopicList(Integer boardId, String primeFlag) {
		ArticleUserinfoSearchDAO dao = new ArticleUserinfoSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_ARTICLE,BBS_USERINFO ");
		sql
				.append(" where BBS_ARTICLE.USERID = BBS_USERINFO.USERID and BBS_ARTICLE.TOPIC = '"
						+ 1
						+ "' and BBS_ARTICLE.ISVIEW = '"
						+ 1
						+ "' and BBS_ARTICLE.BOARDID = " + boardId + " ");
		if (primeFlag.equals("1"))
			sql.append(" and BBS_ARTICLE.PRIME = '" + 1 + "'");
		sql.append(" order by BBS_ARTICLE.TOP desc,BBS_ARTICLE.RETIME desc ");
		// // System.out.println("sql===========" + sql.toString());
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ArticleUserinfoVO());
			System.out.println("==================list size is " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定版块中主题的信息
	 * 
	 * @param String
	 *            boardids 版块ID,多个版块以逗号隔开,字符串最后一个字符不为逗号
	 * @author Administrator
	 * 
	 */
	public List getTopicList(String boardids) {
		ArticleUserinfoSearchDAO dao = new ArticleUserinfoSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_ARTICLE,BBS_USERINFO ");
		sql
				.append(
						" where BBS_ARTICLE.USERID = BBS_USERINFO.USERID and BBS_ARTICLE.TOPIC = '1' and BBS_ARTICLE.ISVIEW = '1' and BBS_ARTICLE.BOARDID in( ")
				.append(boardids).append(") ");
		sql.append(" order by BBS_ARTICLE.TOP desc,BBS_ARTICLE.RETIME desc ");

		System.out.println("sql===========" + sql.toString());
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ArticleUserinfoVO());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get unaudit list
	 * 
	 * @param vo
	 * @return
	 */
	public List getUnauditList(Integer boardId) {
		ArticleUserinfoSearchDAO dao = new ArticleUserinfoSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_ARTICLE,BBS_USERINFO ");
		sql
				.append(" where BBS_ARTICLE.USERID = BBS_USERINFO.USERID and BBS_ARTICLE.ISVIEW = '"
						+ 0 + "' and BBS_ARTICLE.BOARDID = '" + boardId + "' ");
		sql.append(" order by BBS_ARTICLE.TOP desc,BBS_ARTICLE.EMITTIME desc ");

		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ArticleUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get topic num for batch delete
	 * 
	 * @param vo
	 * @return
	 */
	public int getArticleNum(String sql) {
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setWhereClause(sql);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsArticleVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null) {
			return list.size();
		} else {
			return 0;
		}
	}

	/**
	 * reArticle
	 * 
	 */
	public Integer newArticl(BbsArticleVO vo) {
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getArticleid();
	}

	/**
	 * new Area
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public Integer newArea(BbsAreaVO vo) {
		BbsAreaDAO dao = new BbsAreaDAO();
		dao.setValueObject(vo);

		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getAreaid();
	}

	/**
	 * new Notice
	 * 
	 * @param areaId
	 * @throws Exception
	 */
	public void newNotice(BbsNoticeVO vo) {
		BbsNoticeDAO dao = new BbsNoticeDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * update notice
	 * 
	 * @param areaId
	 * @throws Exception
	 */
	public void updateNotice(BbsNoticeVO vo, BbsNoticeVO tvo) {
		DAOFactory factory = new DAOFactory(conn);
		BbsNoticeDAO dao = new BbsNoticeDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setNoticeid(tvo.getNoticeid());
		try {
			dao = (BbsNoticeDAO) factory.findByPrimaryKey();
			dao.setTitle(vo.getTitle());
			dao.setContent(vo.getContent());
			dao.setNoticedate(vo.getNoticedate());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * del board by areaid
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	/*
	 * public void delBoardByAreaId(Integer areaId) throws Exception {
	 * BbsBoardDAO dao = new BbsBoardDAO(); DAOFactory factory = new
	 * DAOFactory(conn); dao.setAreaid(areaId); factory.setDAO(dao); try {
	 * factory.batchDelete(); } catch (DAOException e) { throw e; } }
	 */
	/**
	 * del noticeby boardId
	 * 
	 * @param areaId
	 */
	/*
	 * public void delNoticeByBoardId(Integer boardId) { BbsNoticeDAO dao = new
	 * BbsNoticeDAO(); dao.setBoardid(boardId); dao.setConnection(conn);
	 * DAOFactory factory = new DAOFactory(conn); factory.setDAO(dao); try {
	 * factory.batchDelete(); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * del area
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void delArea(Integer areaId) {
		BbsAreaDAO dao = new BbsAreaDAO();
		dao.setAreaid(areaId);
		dao.setConnection(conn);
		try {
			// delBoardByAreaId(areaId);
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * del board
	 * 
	 * @param vo
	 */
	public void delBoard(Integer boardId) {
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setBoardid(boardId);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * new Board
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public Integer newBoard(BbsBoardVO vo) {
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getBoardid();
	}

	/**
	 * new manager
	 * 
	 * @param boardId
	 * @return
	 */
	public void newManager(Integer boardId, String userId) {
		BbsManagerDAO dao = new BbsManagerDAO();
		dao.setBoardid(boardId);
		dao.setUserid(userId);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		// return dao.getBoardid();
	}

	/**
	 * new right
	 * 
	 * @param boardId
	 * @return
	 */
	public void newRight(Integer boardId, String userId) {
		BbsBoardaccDAO dao = new BbsBoardaccDAO();
		dao.setBoardid(boardId);
		dao.setUserid(userId);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		// return dao.getBoardid();
	}

	/**
	 * get boardList by boardId
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getBoardListById(Integer boardId) {
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setBoardid(boardId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsBoardVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get areaList by areaId
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getAreaListById(Integer areaId) {
		BbsAreaDAO dao = new BbsAreaDAO();
		dao.setAreaid(areaId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsAreaVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get areaName by areaId
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getAreaNameById(Integer areaId) {
		String name = "";
		BbsAreaDAO dao = new BbsAreaDAO();
		dao.setAreaid(areaId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsAreaVO());
			BbsAreaVO vo = (BbsAreaVO) list.get(0);
			name = vo.getAreaname();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * get user List
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getUserMsgList() {
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new BbsUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get UserName List
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getUserName(String userid) {
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		dao.setUserid(userid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		String username = "";
		List list = null;
		try {
			list = factory.find(new BbsUserinfoVO());
			Iterator uIter = list.iterator();
			while (uIter.hasNext()) {
				BbsUserinfoVO uVO = (BbsUserinfoVO) uIter.next();
				username = uVO.getTruename();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

	/**
	 * get BoardAcc List
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getBoardAccList(Integer boardid) {
		BbsBoardaccDAO accdao = new BbsBoardaccDAO();
		accdao.setBoardid(boardid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(accdao);
		List uList = null;
		try {
			uList = factory.find(new BbsBoardaccVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return uList;
	}

	/**
	 * get article vo
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public BbsArticleVO getArticleVO(Integer articleId) {
		BbsArticleDAO dao = new BbsArticleDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setArticleid(articleId);
		factory.setDAO(dao);
		BbsArticleVO vo = null;
		try {
			vo = (BbsArticleVO) factory.findByPrimaryKey(new BbsArticleVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * get user vo
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getNotice(Integer boardId) {
		BbsNoticeDAO dao = new BbsNoticeDAO();
		dao.setBoardid(boardId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new BbsNoticeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get article list of one user
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getUserArticleList(String userId, Integer boardId,
			String quickSearchFlag, String articleName) {
		ArticleUserinfoSearchDAO dao = new ArticleUserinfoSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_ARTICLE,BBS_USERINFO ");
		sql.append(" where BBS_ARTICLE.USERID = BBS_USERINFO.USERID "
				+ " and BBS_ARTICLE.boardid = " + boardId + " and isview='1' ");
		if (quickSearchFlag == null)
			sql.append(" and BBS_ARTICLE.userid = '" + userId + "' ");
		if (quickSearchFlag != null)
			sql.append(" and BBS_ARTICLE.ARTICLENAME like '%" + articleName
					+ "%' ");
		sql.append(" order by emittime ");
		// System.out.println(sql.toString());
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ArticleUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get article list by id
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getArticleListById(Integer articleId) {
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setArticleid(articleId);
		dao.setIsview("1");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsArticleVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * del article
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void delArticle(Integer articleId) {
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setArticleid(articleId);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * set prime
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void setArticlePrime(Integer articleId, String prime) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			if (prime.equals("0"))
				dao.setPrime("1");
			else if (prime.equals("1"))
				dao.setPrime("0");
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * set lock
	 * 
	 * @param articleId
	 * @param top
	 */
	public void setArticleLock(Integer articleId, String lock) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			if (lock.equals("0"))
				dao.setArticlelock("1");
			else if (lock.equals("1"))
				dao.setArticlelock("0");
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * set top
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void setArticleTop(Integer articleId, String top) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			if (top.equals("0"))
				dao.setTop("1");
			else if (top.equals("1"))
				dao.setTop("0");
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * set article view
	 * 
	 */
	public BbsArticleDAO setArticleView(Integer articleId, String view) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			if (view.equals("0"))
				dao.setIsview("1");
			else if (view.equals("1"))
				dao.setIsview("0");
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao;
	}

	/**
	 * edit article
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void editArticle(Integer articleId, String articleName,
			String content, String tointend, String accType, String face) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			dao.setArticlecontent(content);
			dao.setArticlename(articleName);
			dao.setAcctype(accType);
			dao.setFace(face);
			dao.setEdittime(new Long(System.currentTimeMillis()));
			dao.setTointend(tointend);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * edit area
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void editArea(Integer areaId, String areaName, String areaDes,
			String areaRight) {
		DAOFactory factory = new DAOFactory(conn);
		BbsAreaDAO dao = new BbsAreaDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setAreaid(areaId);
		try {
			dao = (BbsAreaDAO) factory.findByPrimaryKey();
			dao.setAreades(areaDes);
			dao.setAreaname(areaName);
			System.out.println("**************** area right "
					+ dao.getArearight());
			dao.setArearight(areaRight);
			System.out.println("**************** area right2 "
					+ dao.getArearight());
			System.out.println("&&&&&&&&&&&&&&&&&&&arearight is " + areaRight
					+ " areaId is " + areaId);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * edit board
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */

	public void editBoard(Integer boardId, String boardName, String boardDes,
			Integer areaId, String isaudit) {
		DAOFactory factory = new DAOFactory(conn);
		BbsBoardDAO dao = new BbsBoardDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setBoardid(boardId);
		try {
			dao = (BbsBoardDAO) factory.findByPrimaryKey();
			dao.setBoarddes(boardDes);
			dao.setBoardname(boardName);
			dao.setAreaid(areaId);
			dao.setIsaudit(isaudit);
			// dao.setManagerid(managerId);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * add article accessory
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void addAccessory(BbsAccVO vo) {
		BbsAccDAO dao = new BbsAccDAO();
		dao.setAcclns(vo.getAcclns());
		dao.setAccname(vo.getAccname());
		dao.setArticleid(vo.getArticleid());
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * add accessory
	 * 
	 * @param id
	 * @param name
	 * @param in
	 */
	public void addAccessory(Integer id, String name, InputStream in) {
		BbsAccDAO dao = new BbsAccDAO(conn);
		dao.setAcclns(in);
		dao.setAccname(name);
		dao.setArticleid(id);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get file list
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getAccList(Integer articleId) {
		BbsAccDAO dao = new BbsAccDAO();
		dao.setArticleid(articleId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsAccVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * batch del article
	 * 
	 * @param topicId
	 */
	public void delArticleByTopicId(Integer topicId) throws Exception {
		BbsArticleDAO dao = new BbsArticleDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setReid(topicId);
		factory.setDAO(dao);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw e;
		}
	}

	/**
	 * del manager by boardId
	 * 
	 * @param topicId
	 */
	public void delManager(Integer boardId) throws Exception {
		BbsManagerDAO dao = new BbsManagerDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setBoardid(boardId);
		factory.setDAO(dao);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw e;
		}
	}

	/**
	 * del right user by board id
	 * 
	 * @param topicId
	 */
	public void delUserRight(Integer boardId) throws Exception {
		BbsBoardaccDAO dao = new BbsBoardaccDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setBoardid(boardId);
		factory.setDAO(dao);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw e;
		}
	}

	/**
	 * del manager by boardId
	 * 
	 * @param topicId
	 */
	public void delManager(Integer boardId, String mId) throws Exception {
		BbsManagerDAO dao = new BbsManagerDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setBoardid(boardId);
		dao.setUserid(mId);
		factory.setDAO(dao);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw e;
		}
	}

	/**
	 * del right user by board id
	 * 
	 * @param topicId
	 */
	public void delUserRight(Integer boardId, String uId) throws Exception {
		BbsBoardaccDAO dao = new BbsBoardaccDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setBoardid(boardId);
		dao.setUserid(uId);
		factory.setDAO(dao);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw e;
		}
	}

	/**
	 * 
	 * del acc
	 * 
	 * @param topicId
	 */
	/*
	 * public void delAcc(Integer articleId) { BbsAccDAO dao = new BbsAccDAO();
	 * DAOFactory factory = new DAOFactory(conn); dao.setArticleid(articleId);
	 * factory.setDAO(dao); try { factory.batchDelete(); } catch (DAOException
	 * e) { e.printStackTrace(); } }
	 */

	/**
	 * del topic
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void delTopic(Integer topicId) {
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setArticleid(topicId);
		dao.setConnection(conn);
		try {
			delArticleByTopicId(topicId);
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get board vo
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public BbsBoardVO getBoardVO(Integer boardId) {
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setBoardid(boardId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		BbsBoardVO vo = null;
		try {
			vo = (BbsBoardVO) factory.findByPrimaryKey(new BbsBoardVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * get user right list
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getRightList(String userId) {
		// BbsBoardaccDAO dao = new BbsBoardaccDAO();
		// dao.setUserid(userId);
		// DAOFactory factory = new DAOFactory(conn);
		// factory.setDAO(dao);
		List list = new ArrayList();
		// try {
		// list = (List) factory.find(new BbsBoardaccVO());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" BBA_ID,BOARDID,USERID ");
		sql.append("FROM ");
		sql.append("BBS_BOARDACC WHERE USERID='" + userId + "' ");
		// System.out.println("------------ "+sql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			int i = 0;
			list = new ArrayList();
			while (rs.next()) {
				BbsBoardaccVO areavo = new BbsBoardaccVO();
				areavo.setBbaId(rs.getString("BBA_ID") == null ? null : Integer
						.valueOf(rs.getString("BBA_ID")));
				areavo.setBoardid(rs.getString("BOARDID") == null ? null
						: Integer.valueOf(rs.getString("BOARDID")));
				areavo.setUserid(rs.getString("USERID"));
				// i++;
				// System.out.println("hander.managelist=sql方式:" + i);
				list.add(areavo);
			}

			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

		return list;
	}

	/**
	 * get manager list
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getManagerList2() {
		ManagerUserinfoSearchDAO dao = new ManagerUserinfoSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_MANAGER.BOARDID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_MANAGER,BBS_USERINFO ");
		sql.append(" where BBS_MANAGER.USERID = BBS_USERINFO.USERID ");
		dao.setSearchSQL(sql.toString());
		System.out.println("这里的sql是:" + sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		factory.setConnection(conn);
		List list = new ArrayList();
		try {
			list = (List) factory.find(new ManagerUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("hander.managelist=" + list.size());
		return list;
	}

	public List getManagerList() {
		// ManagerUserinfoSearchDAO dao = new ManagerUserinfoSearchDAO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql
					.append("BBS_MANAGER.BOARDID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
			sql.append("FROM ");
			sql.append("BBS_MANAGER,BBS_USERINFO ");
			sql.append(" where BBS_MANAGER.USERID = BBS_USERINFO.USERID ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			int i = 0;
			List newlist = new ArrayList();
			while (rs.next()) {
				i++;
				ManagerUserinfoVO vo = new ManagerUserinfoVO();
				vo.setBoardid(Integer.valueOf(rs.getInt(1)));
				vo.setUserid((String) rs.getString(2));
				vo.setOnlineid((String) rs.getString(3));
				vo.setBmId(Integer.valueOf(rs.getInt(4)));
				vo.setBbaId(Integer.valueOf(rs.getInt(5)));
				vo.setOicq((String) rs.getString(6));
				vo.setUserpic((String) rs.getString(7));
				vo.setUnderwrite((String) rs.getString(8));
				vo.setPubnum(Integer.valueOf(rs.getInt(9)));
				vo.setAccessnum(Integer.valueOf(rs.getInt(10)));
				vo.setUserlevel((String) rs.getString(11));
				vo.setRegdate(Long.valueOf(rs.getLong(12)));
				vo.setOnLine((String) rs.getString(13));
				vo.setUsername((String) rs.getString(14));
				vo.setHomepage((String) rs.getString(15));
				vo.setMail((String) rs.getString(16));
				vo.setExp(Integer.valueOf(rs.getInt(17)));
				vo.setIsleader((String) rs.getString(18));
				vo.setTruename((String) rs.getString(19));
				newlist.add(vo);
			}
			System.out.println("hander.managelist=sql方式:" + i);
			// pstmt.close();
			// rs.close();

			// dao.setSearchSQL(sql.toString());
			// System.out.println("这里的sql是:" + sql.toString());
			// DAOFactory factory = new DAOFactory(conn);
			// factory.setDAO(dao);
			// factory.setConnection(conn);
			// List list = new ArrayList();
			//
			// list = (List) factory.find(new ManagerUserinfoVO());

			// System.out.println("hander.managelist=" + list.size());
			return newlist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public List getManagerList(Integer boardId) {
		// ManagerUserinfoSearchDAO dao = new ManagerUserinfoSearchDAO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql
					.append("BBS_MANAGER.BOARDID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
			sql.append("FROM ");
			sql.append("BBS_MANAGER,BBS_USERINFO ");
			sql
					.append(" where BBS_MANAGER.USERID = BBS_USERINFO.USERID and BBS_MANAGER.boardId=?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardId.intValue());
			rs = pstmt.executeQuery();
			int i = 0;
			List newlist = new ArrayList();
			while (rs.next()) {
				i++;
				ManagerUserinfoVO vo = new ManagerUserinfoVO();
				vo.setBoardid(Integer.valueOf(rs.getInt(1)));
				vo.setUserid((String) rs.getString(2));
				vo.setOnlineid((String) rs.getString(3));
				vo.setBmId(Integer.valueOf(rs.getInt(4)));
				vo.setBbaId(Integer.valueOf(rs.getInt(5)));
				vo.setOicq((String) rs.getString(6));
				vo.setUserpic((String) rs.getString(7));
				vo.setUnderwrite((String) rs.getString(8));
				vo.setPubnum(Integer.valueOf(rs.getInt(9)));
				vo.setAccessnum(Integer.valueOf(rs.getInt(10)));
				vo.setUserlevel((String) rs.getString(11));
				vo.setRegdate(new Long(rs.getLong(12)));
				vo.setOnLine((String) rs.getString(13));
				vo.setUsername((String) rs.getString(14));
				vo.setHomepage((String) rs.getString(15));
				vo.setMail((String) rs.getString(16));
				vo.setExp(Integer.valueOf(rs.getInt(17)));
				vo.setIsleader((String) rs.getString(18));
				vo.setTruename((String) rs.getString(19));
				newlist.add(vo);
			}
			System.out.println("hander.managelist=sql方式:" + i);
			pstmt.close();
			rs.close();

			// dao.setSearchSQL(sql.toString());
			// System.out.println("这里的sql是:" + sql.toString());
			// DAOFactory factory = new DAOFactory(conn);
			// factory.setDAO(dao);
			// factory.setConnection(conn);
			// List list = new ArrayList();
			//
			// list = (List) factory.find(new ManagerUserinfoVO());

			// System.out.println("hander.managelist=" + list.size());
			return newlist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// by lintl
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * get manager list by boardid
	 * 
	 * @param boardId
	 * @return
	 */
	public List getManagerList2(Integer boardId) {
		ManagerUserinfoSearchDAO dao = new ManagerUserinfoSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_MANAGER.BOARDID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_MANAGER,BBS_USERINFO ");
		sql
				.append(" where BBS_MANAGER.USERID = BBS_USERINFO.USERID and BBS_MANAGER.boardId="
						+ boardId + " ");
		dao.setSearchSQL(sql.toString());
		System.out.println("dddddddddddddddddddddddddd " + sql.toString());
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new ManagerUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get manager vo
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getManagerVO(Integer boardId) {
		ManagerUserinfoSearchDAO dao = new ManagerUserinfoSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_MANAGER.BOARDID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_MANAGER,BBS_USERINFO ");
		sql
				.append("where BBS_MANAGER.USERID = BBS_USERINFO.USERID and BBS_MANAGER.boardId="
						+ boardId + " ");
		// System.out.println("sql===" + sql.toString());
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new ManagerUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * batch delete article
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void batchDelArticle(String sql) {
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setWhereClause(sql);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setConnection(conn);
		try {
			factory.batchDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMainTopic(Integer topicId) {
		String maintopic = null;
		List list = null;
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setArticleid(topicId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		// BbsArticleVO bvo = new BbsArticleVO();
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		maintopic = dao.getArticlename();
		return maintopic;
	}

	/**
	 * 选取被禁用的时间
	 * 
	 * @param uuid
	 * @return int 0 表示没有被禁用
	 */
	public int getOutDay(String uuid) {
		int b = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn
					.prepareStatement("select time from bbs_out where time>sysdate and personuuid =? order by time ");
			pst.setString(1, uuid);
			rs = pst.executeQuery();
			while (rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				b = (int) ((time.getTime() - (new Date()).getTime()) / (1000 * 60 * 60 * 24)) + 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return b;
	}

	/**
	 * 插入删除的记录
	 * 
	 * @param uuid
	 * @param articleid
	 */
	public void insertDel(String uuid, Integer articleid) {
		PreparedStatement pst = null;

		try {
			pst = conn
					.prepareStatement("insert into bbs_article_del (delpersonuuid,deltime,articleid,boardid,userid,articlename,emittime,articlesize,ip,prime,top,topic,articlecontent,articlelock,reid,reuserid,retime,renum,hitnum,edittime,acctype,face,isview,reusername,accid,tointend) select ?,sysdate,articleid,boardid,userid,articlename,emittime,articlesize,ip,prime,top,topic,articlecontent,articlelock,reid,reuserid,retime,renum,hitnum,edittime,acctype,face,isview,reusername,accid,tointend from bbs_article where articleid=? or reid=? ");
			pst.setString(1, uuid);
			pst.setInt(2, articleid);
			pst.setInt(3, articleid);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List getDelArticle() throws DAOException {
		BbsArticleDelDAO dao = new BbsArticleDelDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		return (factory.find(new BbsArticleDelVO()));
	}

	/**
	 * 取得有权限查看的所有删除帖子
	 * @param uuid
	 * @return
	 * @throws DAOException 
	 */
	public List getDelArticle(String uuid) throws DAOException {
		// TODO Auto-generated method stub
		BbsArticleDelDAO dao = new BbsArticleDelDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setWhereClause(" boardid in(select boardid from bbs_manager where userid='"+uuid+"')");
		dao.addOrderBy("deltime", false);
		factory.setDAO(dao);
		return factory.find(new BbsArticleDelVO());
	}
	
	/**
	 * 判断是否为普通帐户(非公共帐户)
	 * @param personuuid
	 * @return true 是  false 不是
	 * 			
	 */
	public boolean isCommonUser(String personuuid) {
		boolean flag = false;
		try {
			HRSysPersonDAO dao = new HRSysPersonDAO();
			dao.setPersonuuid(personuuid);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			List list = factory.find(new HRSysPersonVO());
			if (!list.isEmpty()) {
				HRSysPersonVO vo = (HRSysPersonVO) list.get(0);
				String hrid =null;
				hrid = vo.getHrid();
				if (hrid != null && !"".equals(hrid.trim())) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
