/*
 * Created on 2004-7-30
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.address.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GrouprightDAO extends DAO {

	private String userid;

	private Integer groupid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer _groupid) {
		firePropertyChange("groupid", groupid, _groupid);
		groupid = _groupid;
	}

	protected void setupFields() throws DAOException {
		addField("userid", "USERID");
		addField("groupid", "GROUPID");
		setTableName("ADDRESS_GROUPRIGHT");
	}

	public GrouprightDAO(Connection conn) {
		super(conn);
	}

	public GrouprightDAO() {
		super();
	}
}
