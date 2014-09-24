package com.icss.oa.address.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.dao.SysOrgDAO;
import com.icss.oa.address.vo.SysOrgVO;

public class SysOrgHandler {
	private Connection conn = null;

	public SysOrgHandler()
	{
	}

	public SysOrgHandler(Connection conn)
	{
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public List getChildOrgs(String orguuid) throws DAOException
	{
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		dao.setWhereClause(" PARENTORGUUID = '" + orguuid + "'");
		factory.setDAO(dao);
		return factory.find(new SysOrgVO());
	}
}
