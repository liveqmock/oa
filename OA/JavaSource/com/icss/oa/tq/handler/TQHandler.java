package com.icss.oa.tq.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.dao.SysOrgDAO;
import com.icss.oa.address.dao.SysOrgpersonSearchDAO;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.SysOrgVO;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.tq.dao.TqOrgpersonSearchDAO;
import com.icss.oa.tq.dao.TqRopersonDAO;
import com.icss.oa.tq.dao.TqRopersonSearchDAO;
import com.icss.oa.tq.dao.TqSyspersonSearchDAO;
import com.icss.oa.tq.vo.TqOnlineVO;
import com.icss.oa.tq.vo.TqOrgpersonVO;
import com.icss.oa.tq.vo.TqRopersonSearchVO;
import com.icss.oa.tq.vo.TqRopersonVO;
import com.icss.oa.tq.vo.TqSyspersonSearchVO;

public class TQHandler {

	private Connection conn;

	public TQHandler() {
	}

	public TQHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 取得所有人员
	 * 
	 * @return
	 * @throws HandlerException
	 */
	public List getAllPerson() throws HandlerException {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct ");
		sql
				.append("RO_PERSON.PERSONUUID,RO_PERSON.CNNAME,RO_PERSON.SEX,RO_PERSON.OFFICETEL,RO_PERSON.EMAIL1,RO_PERSONACCOUNT.USERID,RO_PERSONACCOUNT.PASSWORD ");
		sql.append(" from ");
		sql.append("RO_PERSON,RO_PERSONACCOUNT ");
		sql.append("where ");
		sql.append("RO_PERSON.PERSONUUID= RO_PERSONACCOUNT.PERSONUUID and ");
		sql
				.append("RO_PERSONACCOUNT.DELTAG='0' and RO_PERSONACCOUNT.TTLFLAG='0'");
		DAOFactory factory = new DAOFactory(conn);
		TqRopersonSearchDAO dao = new TqRopersonSearchDAO();
		System.out.println("!!!!!getpersonSQL=" + sql.toString());
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try {
			return factory.find(new TqRopersonSearchVO());
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * 设置TQ号
	 * 
	 * @param vo
	 * @throws HandlerException
	 */
	public void setTqid(TqRopersonVO vo) throws HandlerException {
		try {
			TqRopersonDAO dao = new TqRopersonDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			dao.update(true);
		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * 取得TQ号
	 * 
	 * @param personuuid
	 * @return
	 */

	public Integer getTqid(String personuuid) {
		DAOFactory factory = new DAOFactory(conn);
		TqRopersonDAO dao = new TqRopersonDAO();
		dao.setPersonuuid(personuuid);
		factory.setDAO(dao);
		List list = null;
		Integer id = 0;
		try {
			list = factory.find(new TqRopersonVO());
			if (list != null && list.size() > 0) {
				TqRopersonVO vo = (TqRopersonVO) list.get(0);
				return vo.getTqid();
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;

	}

	/**
	 * 取得分组里的人员
	 * 
	 * @param groupid
	 * @return
	 */
	public List personInGroupbyName(Integer groupid, String grouptype) {

		TqSyspersonSearchDAO dao = new TqSyspersonSearchDAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("ADDRESS_GROUPINFO.USERID,ADDRESS_GROUPINFO.GROUPID,ADDRESS_GROUPINFO.GROUPTYPE,SYS_PERSON.PERSONUUID,SYS_PERSON.CNNAME,SYS_PERSON.TQID ");
		sql.append("FROM ");
		sql.append("ADDRESS_GROUPINFO,SYS_PERSON ");
		sql.append(" WHERE ");
		sql
				.append("SYS_PERSON.PERSONUUID=ADDRESS_GROUPINFO.USERID AND ADDRESS_GROUPINFO.GROUPTYPE= '"
						+ grouptype + "'");
		sql.append("AND ADDRESS_GROUPINFO.GROUPID=" + groupid);
		sql.append(" order by SYS_PERSON.USERID");
		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new TqSyspersonSearchVO());
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 取得组织里的人员
	 * 
	 * @param orguuid
	 * @return
	 * @throws HandlerException
	 */
	public List getOrgPerson(String orguuid) throws HandlerException {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql
				.append("SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO,SYS_PERSON.TQID ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.DELTAG = '0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_ORGPERSON.ORGUUID= '" + orguuid + "' ");
		sql.append(" order by SYS_PERSON.SEQUENCENO ,SYS_PERSON.ENNAME ASC");
		System.out.println("SysOrgpersonHandler getList() sql = "
				+ sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		TqOrgpersonSearchDAO dao = new TqOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try {
			return factory.find(new TqOrgpersonVO());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}
	
	/**
	 * 取得本部门里的人员
	 * 
	 * @param orguuid
	 * @return
	 * @throws HandlerException
	 */
	public List getDptPerson(String orglevel) throws HandlerException {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql
				.append("SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO,SYS_PERSON.TQID ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.DELTAG = '0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_ORGPERSON.ORGUUID in  (select orguuid from sys_org  where DELTAG=0 and orglevelcode like '"+orglevel+"%' )");
		sql.append(" order by SYS_PERSON.CNNAME ASC");
		System.out.println("SysOrgpersonHandler getList() sql = "
				+ sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		TqOrgpersonSearchDAO dao = new TqOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try {
			return factory.find(new TqOrgpersonVO());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}
	
	
	/**
	 * 取得本部门
	 * 
	 * @param orguuid
	 * @return
	 * @throws HandlerException
	 */
	public SysOrgVO getDpt(String personuuid) throws HandlerException {
		
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		SysOrgVO vo =null;
		dao.setWhereClause(" orguuid=(select orguuid from sys_orgperson where personuuid='"+personuuid+"')");
		factory.setDAO(dao);
		try {
			List list =  factory.find(new SysOrgVO());
			vo = (SysOrgVO) list.get(0);
			
			String s = vo.getOrglevelcode();
			if(s.length()>16)
			s = s.substring(0, 16);
			
			SysOrgDAO sdao = new SysOrgDAO();
			factory.setDAO(sdao);
			sdao.setOrglevelcode(s);
			List list1 =  factory.find(new SysOrgVO());
			vo = (SysOrgVO) list1.get(0);
			return vo;
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}
	

	/**
	 * 取得人员的在线状态
	 * 
	 * @param uin
	 * @return
	 * @throws SQLException
	 */
	public int getTqStatus(Integer uin) throws SQLException {

		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int status = 109;

		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// String url = "jdbc:oracle:thin:@10.102.1.35:1521:orcl";
			//
			// String user = "tracqdev";
			// String password = "tracq123";
			// conn = DriverManager.getConnection(url, user, password);
			String sql = "select online_status   from user_info_tb where uin="
					+ uin;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				status = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;

	}

	/**
	 * 取得所有的在线人员
	 * 
	 * @return
	 * @throws SQLException
	 */

	public List getOnlineUser() throws SQLException {

		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// String url = "jdbc:oracle:thin:@10.102.1.35:1521:orcl";
			//
			// String user = "tracqdev";
			// String password = "tracq123";
			// conn = DriverManager.getConnection(url, user, password);
			String sql = "select uin from user_info_tb where online_flag >=20";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String uin = rs.getString(1);
				list.add(uin);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}

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

	public int querySumPages(String key) throws Exception {

		int iSumRecords = 0;
		String dbSql = "";
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		try {

			// Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// String url = "jdbc:oracle:thin:@10.102.1.35:1521:orcl";
			//
			// String user = "tracqdev";
			// String password = "tracq123";
			// conn = DriverManager.getConnection(url, user, password);
			// String sql = "select uin,nickname,online_status from user_info_tb
			// where online_flag>=20 and nickname like '%"
			// + key+ "%'";
			if (key != null && key.length() > 0) {
				sql = "select uin,nickname,online_status from user_info_tb where online_flag>=20 and nickname like '%"
						+ key + "%'";
			} else {
				sql = "select uin,nickname,online_status from user_info_tb where online_flag>=20";
			}

			dbSql = getSqlRecordCount(sql);
			// System.out.println("@@@@@@@@@@@@@@@@@@@@" + dbSql);
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

	/**
	 * 查询对应页的内容
	 * 
	 * @param key
	 * @param iCurPageIn
	 * @param iPageSizeIn
	 * @return
	 * @throws Exception
	 */
	public List queryPage(String key, int iCurPageIn, int iPageSizeIn)
			throws Exception {

		// String sql ="select uin,nickname,online_status from user_info_tb
		// where online_flag>=20 and nickname like '%"
		// + key+ "%'";
		String sql = "";

		if (key != null && key.length() > 0) {
			sql = "select user_info_tb.uin,user_info_tb.nickname,user_info_tb.online_status,tq_org.orgname,tq_org.deptname from user_info_tb ,tq_org  where user_info_tb.uin=tq_org.tqid and user_info_tb.online_flag>=20 and user_info_tb.nickname like '%"
					+ key + "%'";
		} else {
			sql = "select user_info_tb.uin,user_info_tb.nickname,user_info_tb.online_status,tq_org.orgname,tq_org.deptname from user_info_tb ,tq_org  where user_info_tb.uin=tq_org.tqid and user_info_tb.online_flag>=20";
		}

		PreparedStatement pm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			String dbSql = getSqlPage(sql);
			// Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// String url = "jdbc:oracle:thin:@10.102.1.35:1521:orcl";
			//
			// String user = "tracqdev";
			// String password = "tracq123";
			// conn = DriverManager.getConnection(url, user, password);

			int iBegin = (iCurPageIn - 1) * iPageSizeIn;
			int iEnd = iBegin + iPageSizeIn;
			System.out.println("dbsql" + dbSql);
			pm = conn.prepareStatement(dbSql);
			pm.setInt(1, iEnd); // 从第几行结束
			pm.setInt(2, iBegin); // 从第几行开始
			rs = pm.executeQuery();
			while (rs.next()) {
				TqOnlineVO vo = new TqOnlineVO();
				vo.setTqid(rs.getString(1));
				vo.setCnname(rs.getString(2));
				String orgname = rs.getString(4);
				String deptname = rs.getString(5);
				if (deptname != null && deptname.length() > 0) {
					orgname = orgname + "-->" + deptname;
				}
				vo.setOrgname(orgname);
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
	 * 根据orgname 取得org中的在线人员
	 * 
	 * @param orguuid
	 * @return
	 * @throws Exception
	 */
	public List getOLByOrgname(String orgname) throws Exception {
		List list = new ArrayList();
		String sql = "";
		if (orgname != null && orgname.length() > 0) {
			sql = "select u.uin, u.nickname, u.online_status from user_info_tb u,tq_org  t where u.uin=t.tqid and u.online_flag>=20  and t.ORGNAME = '"
					+ orgname.trim() + "'";
		}
		// System.out.println("sql="+sql);
		PreparedStatement pm = null;
		ResultSet rs = null;
		try {
			pm = conn.prepareStatement(sql);
			rs = pm.executeQuery();
			while (rs.next()) {
				TqOnlineVO vo = new TqOnlineVO();
				vo.setTqid(rs.getString(1));
				vo.setCnname(rs.getString(2));
				list.add(vo);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("出错，错误信息：" + ex.getMessage());
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
	 * 根据人民模糊查找人员,num 为几个人,返回的人员信息不全
	 * @param name
	 * @return
	 * @throws DAOException
	 */
	public List getUserbyName(String name,int num) {
		
		PreparedStatement pm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("select distinct ");
			sql.append("SYS_PERSON.PERSONUUID,SYS_PERSON.USERID,SYS_PERSON.CNNAME,SYS_PERSON.ENNAME ");
			sql.append(" from ");
			sql.append("SYS_PERSON ");
			sql.append("where ");
			sql.append("SYS_PERSON.DELTAG='0' and SYS_PERSON.TTLFLAG='0' and (");
			sql.append("SYS_PERSON.CNNAME like '" + name
					+ "%' or SYS_PERSON.USERID like'" + name
					+ "%') order by SYS_PERSON.USERID");
			String dbSql = getSqlPage(sql.toString());

			int iBegin = 0;
			int iEnd = num;
			//System.out.println("dbsql" + dbSql);
			pm = conn.prepareStatement(dbSql);
			pm.setInt(1, iEnd); // 从第几行结束
			pm.setInt(2, iBegin); // 从第几行开始
			rs = pm.executeQuery();
			while (rs.next()) {
				SysOrgpersonVO vo = new SysOrgpersonVO();
				vo.setPersonuuid(rs.getString(1));
				vo.setUserid(rs.getString(2));
				vo.setCnname(rs.getString(3));
				vo.setEnname(rs.getString(4));
				list.add(vo);
			}

			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
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
}
