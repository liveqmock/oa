/*
 * Created on 2004-8-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SimpleApplyBaseDAO extends DAO {

	String uuid;
	String hrid;
	Integer state;
	String userid;
	String password;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String _hrid) {
		firePropertyChange("hrid", hrid, _hrid);
		hrid = _hrid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer _state) {
		firePropertyChange("state", state, _state);
		state = _state;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String _password) {
		firePropertyChange("password", password, _password);
		password = _password;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("hrid", "HRID");
		addField("state", "STATE");
		addField("userid", "USERID");
		addField("password", "PASSWORD");
		setTableName("VI_PF_HR_APPLY_BASE");
		addKey("HRID");
	}
	public SimpleApplyBaseDAO(Connection conn) {
		super(conn);
	}
	public SimpleApplyBaseDAO() {
		super();
	}
}
