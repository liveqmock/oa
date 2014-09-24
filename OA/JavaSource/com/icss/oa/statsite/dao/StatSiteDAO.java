/*
 * Created on 2004-6-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.statsite.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StatSiteDAO extends DAO {
	Integer id;
	String moduleid;
	String userid;
	Long time;
	String ip;
	String address;

	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String _moduleid) {
		firePropertyChange("moduleid", moduleid, _moduleid);
		moduleid = _moduleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long _time) {
		firePropertyChange("time", time, _time);
		time = _time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String _ip) {
		firePropertyChange("ip", ip, _ip);
		ip = _ip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String _address) {
		firePropertyChange("address", address, _address);
		address = _address;
	}
	
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("moduleid", "MODULEID");
		addField("userid", "USERID");
		addField("time", "TIME");
		addField("ip", "IP");
		addField("address", "ADDRESS");
		setTableName("STAT_SITE");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public StatSiteDAO(Connection conn) {
		super(conn);
	}
	public StatSiteDAO() {
		super();
	}
}
