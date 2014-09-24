/*
 * Created on 2004-12-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.workmeeting.dao;  

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GzwjviewRecDAO extends DAO {

	private Integer wrNo;
	private String username;
	private String truename;
	private Integer officeid;
	private String officenname;
	private String ip;
	private Long viewtime;
	public Integer getWrNo() {
		return wrNo;
	}
	public void setWrNo(Integer _wrNo) {
		firePropertyChange("wrNo", wrNo, _wrNo);
		wrNo = _wrNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String _username) {
		firePropertyChange("username", username, _username);
		username = _username;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String _truename) {
		firePropertyChange("truename", truename, _truename);
		truename = _truename;
	}
	public Integer getOfficeid() {
		return officeid;
	}
	public void setOfficeid(Integer _officeid) {
		firePropertyChange("officeid", officeid, _officeid);
		officeid = _officeid;
	}
	public String getOfficenname() {
		return officenname;
	}
	public void setOfficenname(String _officenname) {
		firePropertyChange("officenname", officenname, _officenname);
		officenname = _officenname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String _ip) {
		firePropertyChange("ip", ip, _ip);
		ip = _ip;
	}
	public Long getViewtime() {
		return viewtime;
	}
	public void setViewtime(Long _viewtime) {
		firePropertyChange("viewtime", viewtime, _viewtime);
		viewtime = _viewtime;
	}
	protected void setupFields() throws DAOException {
		addField("wrNo", "WR_NO");
		addField("username", "USERNAME");
		addField("truename", "TRUENAME");
		addField("officeid", "OFFICEID");
		addField("officenname", "OFFICENNAME");
		addField("ip", "IP");
		addField("viewtime", "VIEWTIME");
		setTableName("GZWJVIEW_REC");
	}
	public GzwjviewRecDAO(Connection conn) {
		super(conn);
	}
	public GzwjviewRecDAO() {
		super();
	}
}
