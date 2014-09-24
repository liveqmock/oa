/*
 * Created on 2004-7-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.statsite.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatSiteIpcontentListDAO extends DAO {

	String ipcontent;

	public String getIpcontent() {
		return ipcontent;
	}

	public void setIpcontent(String _ipcontent) {
		firePropertyChange("ipcontent", ipcontent, _ipcontent);
		ipcontent = _ipcontent;
	}

	protected void setupFields() throws DAOException {
		addField("ipcontent", "IPCONTENT");
		setTableName("STAT_SITE_IPCONTENT");
		addKey("ID");
	}

	public StatSiteIpcontentListDAO(Connection conn) {
		super(conn);
	}

	public StatSiteIpcontentListDAO() {
		super();
	}
}
