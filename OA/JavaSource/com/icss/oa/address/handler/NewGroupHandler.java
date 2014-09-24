package com.icss.oa.address.handler;

import java.sql.Connection;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.dao.AddressGroupinfoDAO;
import com.icss.oa.address.dao.AddressGroupinfoSysPersonSearch1DAO;
import com.icss.oa.address.vo.AddressGroupinfoSysPersonSearch1VO;
import com.icss.oa.address.vo.AddressGroupinfoVO;


public class NewGroupHandler {
	private Connection conn;
	public NewGroupHandler(Connection connection) {
		this.conn = connection;
	}

	public NewGroupHandler() {
	}
	
	
	/**
	 * 取得分组里的人员
	 * @param groupid
	 * @return
	 */
	public List personInGroupbyName(Integer groupid, String grouptype) {

		AddressGroupinfoSysPersonSearch1DAO dao = new AddressGroupinfoSysPersonSearch1DAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("ADDRESS_GROUPINFO.USERID,ADDRESS_GROUPINFO.GROUPID,ADDRESS_GROUPINFO.GROUPTYPE,SYS_PERSON.PERSONUUID ");
		sql.append("FROM ");
		sql.append("ADDRESS_GROUPINFO,SYS_PERSON ");
		sql.append(" WHERE ");
		sql.append("SYS_PERSON.PERSONUUID=ADDRESS_GROUPINFO.USERID AND SYS_PERSON.DELTAG=0 AND ADDRESS_GROUPINFO.GROUPTYPE= '" + grouptype + "'");
		sql.append("AND ADDRESS_GROUPINFO.GROUPID=" + groupid);
		sql.append(" order by SYS_PERSON.USERID");
		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new AddressGroupinfoSysPersonSearch1VO());
			return list;
		} catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * 取得分组里的人员
	 * @param groupid
	 * @return
	 */
	public List personInGroup(Integer groupid, String grouptype) {
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupinfoDAO addressGroupinfoDAO = new AddressGroupinfoDAO();
		addressGroupinfoDAO.setGroupid(groupid);
		addressGroupinfoDAO.setGrouptype(grouptype);

		factory.setDAO(addressGroupinfoDAO);
		List list = null;
		try {
			list = factory.find(new AddressGroupinfoVO());
		} catch (DAOException e) {
			throw new RuntimeException("查找分组内人员错误！");
		}
		return list;
	}

}