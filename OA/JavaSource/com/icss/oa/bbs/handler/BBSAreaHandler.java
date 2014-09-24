package com.icss.oa.bbs.handler;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.bbs.dao.*;
import com.icss.oa.bbs.vo.*;
import java.sql.*;
import java.util.*;

public class BBSAreaHandler {

	public BBSAreaHandler(Connection conn) {
		this.conn = conn;
	}

	public List getAreAccList(Integer areaid) {
		BbsAreaccDAO accdao = new BbsAreaccDAO();
		accdao.setAreaid(areaid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(accdao);
		List uList = null;
		try {
			uList = factory.find(new BbsAreaccVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return uList;
	}

	public List getRightAreaList(String uuid) {
		BbsAreaccDAO accdao = new BbsAreaccDAO();
		accdao.setFlag("1");
		accdao.setUserid(uuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(accdao);
		List uList = null;
		try {
			uList = factory.find(new BbsAreaccVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return uList;
	}

	public void newAreaRight(Integer areaid, String userId) {
		BbsAreaccDAO dao = new BbsAreaccDAO();
		dao.setAreaid(areaid);
		dao.setUserid(userId);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public String getUserName(String userid) {
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		dao.setUserid(userid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		String username = "";
		List list = null;
		try {
			list = factory.find(new BbsUserinfoVO());
			for (Iterator uIter = list.iterator(); uIter.hasNext();) {
				BbsUserinfoVO uVO = (BbsUserinfoVO) uIter.next();
				username = uVO.getTruename();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

	public List getAreaListById(Integer areaId) {
		BbsAreaDAO dao = new BbsAreaDAO();
		dao.setAreaid(areaId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new BbsAreaVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List getAreaccList() {
		BbsAreaDAO dao = new BbsAreaDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new BbsAreaVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List acclist = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				BbsAreaVO vo = (BbsAreaVO) list.get(i);
				BbsAreaccVO accvo = new BbsAreaccVO();
				accvo.setAreaid(vo.getAreaid());
				acclist.add(accvo);
			}
		}
		return acclist;
	}

	public void delUserRight(Integer areaId, String uId) throws Exception {
		BbsAreaccDAO dao = new BbsAreaccDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setAreaid(areaId);
		dao.setUserid(uId);
		factory.setDAO(dao);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw e;
		}
	}

	public void updateAreaLimited(Integer areaId, String right) {
		DAOFactory factory = new DAOFactory(conn);
		BbsAreaDAO dao = new BbsAreaDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setAreaid(areaId);
		try {
			dao = (BbsAreaDAO) factory.findByPrimaryKey();
			dao.setArearight(right);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public List getRightList(String userId) {
		List list = new ArrayList();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select bbs_areacc.baa_id,bbs_areacc.areaid,bbs_areacc.userid from bbs_areacc where bbs_areacc.userid='"
				+ userId + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs != null && rs.next()) {
				BbsAreaccVO vo = new BbsAreaccVO();
				vo.setBaaId(new Integer(rs.getInt(1)));
				vo.setAreaid(new Integer(rs.getInt(2)));
				vo.setUserid(rs.getString(3));
				list.add(vo);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}

	public String getPersonTel(String uuid) {
		String tel = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select roeee.ro_person.officetel from roeee.ro_person where roeee.ro_person.personuuid='"
				+ uuid + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs != null && rs.next())
				tel = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return tel;
	}

	public String getPersonJob(String uuid) {
		String job = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select roeee.ro_person.job from roeee.ro_person where roeee.ro_person.personuuid='"
				+ uuid + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs != null && rs.next())
				job = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return job;
	}

	public List findAdministrator() {
		BbsAreaccDAO dao = new BbsAreaccDAO();
		dao.setFlag("0");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new BbsAreaccVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getAdminString() {
		List list = findAdministrator();
		String adminStrings = "";
		for (int i = 0; i < list.size(); i++) {
			BbsAreaccVO vo = (BbsAreaccVO) list.get(i);
			adminStrings = adminStrings + vo.getUserid() + "||";
		}

		return adminStrings;
	}

	public List findManagerListByAreaId(Integer integer) {
		BbsAreaccDAO dao = new BbsAreaccDAO();
		dao.setAreaid(integer);
		dao.setFlag("1");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new BbsAreaccVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void newAreaManager(Integer areaid, String userId) {
		BbsAreaccDAO dao = new BbsAreaccDAO();
		dao.setAreaid(areaid);
		dao.setUserid(userId);
		dao.setFlag("1");
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void delManagerRight(Integer areaId, String pid) {
		BbsAreaccDAO dao = new BbsAreaccDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setAreaid(areaId);
		dao.setUserid(pid);
		dao.setFlag("1");
		factory.setDAO(dao);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public String getAreaNameById(Integer areaId) {
		BbsAreaDAO dao = new BbsAreaDAO();
		dao.setAreaid(areaId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			dao = (BbsAreaDAO) factory.findByPrimaryKey();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getAreaname();
	}

	private Connection conn;
}