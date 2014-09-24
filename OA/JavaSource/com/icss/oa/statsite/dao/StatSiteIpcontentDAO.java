/*
 * Created on 2004-7-1
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
public class StatSiteIpcontentDAO extends DAO {

	Integer id;

	Long startip;

	Long endip;

	String ipcontent;

	String ipmeno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public Long getStartip() {
		return startip;
	}

	public void setStartip(Long _startip) {
		firePropertyChange("startip", startip, _startip);
		startip = _startip;
	}

	public Long getEndip() {
		return endip;
	}

	public void setEndip(Long _endip) {
		firePropertyChange("endip", endip, _endip);
		endip = _endip;
	}

	public String getIpcontent() {
		return ipcontent;
	}

	public void setIpcontent(String _ipcontent) {
		firePropertyChange("ipcontent", ipcontent, _ipcontent);
		ipcontent = _ipcontent;
	}

	public String getIpmeno() {
		return ipmeno;
	}

	public void setIpmeno(String _ipmeno) {
		firePropertyChange("ipmeno", ipmeno, _ipmeno);
		ipmeno = _ipmeno;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("startip", "STARTIP");
		addField("endip", "ENDIP");
		addField("ipcontent", "IPCONTENT");
		addField("ipmeno", "IPMENO");
		setTableName("STAT_SITE_IPCONTENT");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public StatSiteIpcontentDAO(Connection conn) {
		super(conn);
	}

	public StatSiteIpcontentDAO() {
		super();
	}
}
