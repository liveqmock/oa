package com.icss.regulation.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.dao.SysOrgDAO;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.SysOrgVO;
import com.icss.oa.filetransfer.dao.SysOrgpersonDAO;
import com.icss.oa.filetransfer.vo.SysOrgpersonVO;
import com.icss.oa.tq.vo.TqOnlineVO;
import com.icss.regulation.dao.RegulationDAO;
import com.icss.regulation.dao.RegulationOrgDAO;
import com.icss.regulation.vo.RegulationCountVO;
import com.icss.regulation.vo.RegulationOrgVO;
import com.icss.regulation.vo.RegulationVO;

public class RegulationHandler {

	private Connection conn;

	public RegulationHandler() {
	}

	public RegulationHandler(Connection conn) {
		this.conn = conn;
	}

	public List getRListByClause(String string) {
		RegulationDAO dao = new RegulationDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("createTime", false);
		// dao.addOrderBy("id", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new RegulationVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 不取的内容
	 * @param string
	 * @return
	 */
	public List getRNListByClause(String string) {

		Statement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			st = conn.createStatement();
			String sql = "select id,title,org from regulation_record where "+string+ " order by id ";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				RegulationVO vo = new RegulationVO();
				vo.setId(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setOrg(rs.getString(3));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(st!=null){
				st.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	

	public RegulationVO getRegulation(Integer id) {
		RegulationVO vo = new RegulationVO();
		RegulationDAO dao = new RegulationDAO();
		dao.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new RegulationVO());
			if (!list.isEmpty()) {
				vo = (RegulationVO) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public Integer saveRegulation(RegulationVO vo) throws DAOException {
		RegulationDAO dao = new RegulationDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		dao.create();

		return dao.getId();

	}

	public void delRegulation(Integer id) throws DAOException {
		RegulationDAO dao = new RegulationDAO();
		dao.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		factory.batchDelete();
	}

	public List getAllRegulation() {
		RegulationDAO dao = new RegulationDAO();

		dao.addOrderBy("createTime", false);
		// dao.addOrderBy("id", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		System.out.println("is ok");
		List list = null;
		try {
			list = (List) factory.find(new RegulationVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public void updRegulation(RegulationVO vo) throws DAOException {
		RegulationDAO dao = new RegulationDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		dao.update(true);
	}

	public static List getOrg(int level) {
		Connection conn = null;
		List orglist = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			DAOFactory factory = new DAOFactory(conn);
			SysOrgDAO dao = new SysOrgDAO();
			dao
					.setWhereClause(" orglevel = "
							+ level
							+ " and SubStr(sys_org.orgcode,2,1)<>'3'  and deltag ='0' ");
			dao.addOrderBy("orgcode", true);
			factory.setDAO(dao);
			orglist = factory.find(new SysOrgVO());
		} catch (DBConnectionLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return orglist;
	}

	public static String getOrgName(String orguuid) {
		Connection conn = null;
		List orglist = null;
		String orgname = "";
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			DAOFactory factory = new DAOFactory(conn);
			SysOrgDAO dao = new SysOrgDAO();
			dao.setOrguuid(orguuid);
			factory.setDAO(dao);

			orglist = factory.find(new SysOrgVO());
			if (!orglist.isEmpty()) {
				SysOrgVO vo = (SysOrgVO) orglist.get(0);
				orgname = vo.getCnname();
			}
		} catch (DBConnectionLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return orgname;
	}

	/**
	 * 
	 * 取得所属的组织
	 * 
	 * @param personuuid
	 * @return
	 * @throws HandlerException
	 */
	public String getOrgUUID(String personuuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonDAO dao = new SysOrgpersonDAO();
		factory.setDAO(dao);
		dao.setPersonuuid(personuuid);
		String orguuid = null;

		try {
			List list = factory.find(new SysOrgpersonVO());
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SysOrgpersonVO vo = (SysOrgpersonVO) it.next();
					orguuid = vo.getOrguuid();
				}
			}

			return orguuid;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public String getParentUUID(String uuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(uuid);

		String orguuid = null;

		try {
			List list = factory.find(new SysOrgVO());
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SysOrgVO vo = (SysOrgVO) it.next();
					if (vo.getOrglevel() > 4) {
						orguuid = this.getParentUUID(vo.getParentorguuid());
					} else {
						orguuid = vo.getParentorguuid();
					}
				}
			}
			return orguuid;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getRegulationByOrg(String ouid) {
		RegulationDAO dao = new RegulationDAO();
		dao.setOrg(ouid);
		dao.addOrderBy("createTime", false);

		// dao.addOrderBy("id", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new RegulationVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 取得管理制度系统的组织
	 * 
	 * @return
	 */

	public List getRegulationOrg() {
		RegulationOrgDAO dao = new RegulationOrgDAO();
		dao.addOrderBy("sequence", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new RegulationOrgVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 删除组织
	 * 
	 * @param orguuid
	 */
	public void delOrg(String orguuid) {
		RegulationOrgDAO dao = new RegulationOrgDAO();
		dao.setOrguuid(orguuid);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改组织
	 * 
	 * @param vo
	 */
	public void editOrg(RegulationOrgVO vo) {
		RegulationOrgDAO dao = new RegulationOrgDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.update(true);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 新建组织
	 * 
	 * @param vo
	 */
	public void newOrg(RegulationOrgVO vo) {
		RegulationOrgDAO dao = new RegulationOrgDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据uuid 取得 组织的名字
	 * 
	 * @param orguuid
	 * @return
	 */
	public String getROrgName(String orguuid) {
		List orglist = null;
		String orgname = "";
		try {
			DAOFactory factory = new DAOFactory(conn);
			RegulationOrgDAO dao = new RegulationOrgDAO();
			dao.setOrguuid(orguuid);
			factory.setDAO(dao);

			orglist = factory.find(new RegulationOrgVO());
			if (!orglist.isEmpty()) {
				RegulationOrgVO vo = (RegulationOrgVO) orglist.get(0);
				orgname = vo.getOrgname();
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orgname;
	}

	/**
	 * 取得组织hashmap
	 * 
	 * @return
	 */
	public static HashMap getOrgMap() {
		Connection conn = null;
		List orglist = null;
		HashMap org = new HashMap();
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			DAOFactory factory = new DAOFactory(conn);
			RegulationOrgDAO dao = new RegulationOrgDAO();
			factory.setDAO(dao);

			orglist = factory.find(new RegulationOrgVO());
			for (int i = 0; i < orglist.size(); i++) {
				RegulationOrgVO vo = (RegulationOrgVO) orglist.get(i);
				org.put(vo.getOrguuid(), vo.getOrgname());
			}
		} catch (DBConnectionLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return org;
	}

	/**
	 * 取得管理组织
	 * 
	 * @return
	 */
	public List getROrg() {
		List orglist = null;
		try {
			DAOFactory factory = new DAOFactory(conn);
			RegulationOrgDAO dao = new RegulationOrgDAO();
			dao.addOrderBy("sequence", true);
			factory.setDAO(dao);
			orglist = factory.find(new RegulationOrgVO());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orglist;
	}

	public List countRecord(String time, String time2) {

		Statement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			st = conn.createStatement();
			String sql = "select (select orgname from regulation_org where orguuid =org),count(*),sum(flag) from regulation_record where CREAT_TIME >=to_date('"
					+ time
					+ "','yyyy-mm-dd') and CREAT_TIME <=to_date('"
					+ time2 + "','yyyy-mm-dd') group by org ";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				RegulationCountVO vo = new RegulationCountVO();
				vo.setOrgname(rs.getString(1));
				vo.setTotal(rs.getInt(2));
				vo.setUnvalid(rs.getInt(3));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	
	/**
	 * 采用通用分页sql方法，支持order by,注意在sql中不要用别名
	 * 
	 * @param sqlIn
	 * @return
	 * @throws Exception
	 */

	private String getSqlPage(String sqlIn) throws Exception {
		try {
			StringBuffer pagingSelect = new StringBuffer(1000);
			pagingSelect
					.append("select * from ( select row_.*, rownum rownum_ from ( ");
			pagingSelect.append(sqlIn);
			pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
			return pagingSelect.toString();
		} catch (Exception ex) {
			throw new Exception("执行getSqlPage函数出错！错误信息：" + ex.getMessage());
		}
	}

	/**
	 * 获得总记录数sql
	 * 
	 * @param sqlIn
	 * @return
	 */

	private String getSqlRecordCount(String sqlIn) {
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect.append("select count(*) from ( ");
		pagingSelect.append(sqlIn);
		pagingSelect.append(" ) ");
		return pagingSelect.toString();
	}

	
	/**
	 * 查询对应页的内容
	 * 
	 * @param key
	 * @param iCurPageIn
	 * @param iPageSizeIn
	 * @return
	 * @throws Exception
	 */
	public List queryPage(String sql, int iCurPageIn, int iPageSizeIn)
			throws Exception {
		PreparedStatement pm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			String dbSql = getSqlPage(sql);
			int iBegin = (iCurPageIn - 1) * iPageSizeIn;
			int iEnd = iBegin + iPageSizeIn;
			System.out.println("dbsql" + dbSql);
			pm = conn.prepareStatement(dbSql);
			pm.setInt(1, iEnd); // 从第几行结束
			pm.setInt(2, iBegin); // 从第几行开始
			rs = pm.executeQuery();
			while (rs.next()) {
				RegulationVO vo = new RegulationVO();
				vo.setId(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setOrg(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("更新页面时出错，错误信息：" + ex.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pm != null)
					pm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	/**
	 * 获得总记录数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int querySumPages(String sql) throws Exception {

		int iSumRecords = 0;
		String dbSql = "";
		Statement st = null;
		ResultSet rs = null;
		try {
			dbSql = getSqlRecordCount(sql);
			st = conn.createStatement();
			rs = st.executeQuery(dbSql);
			if (rs.next())
				iSumRecords = rs.getInt(1);
			rs.close();
		} catch (Exception ex) {

			throw new Exception("计算总页数时出错，错误信息：" + ex.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return iSumRecords;
	}
}