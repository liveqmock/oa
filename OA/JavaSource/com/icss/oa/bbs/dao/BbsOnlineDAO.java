/*
 * Created on 2004-4-6
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
public class BbsOnlineDAO extends DAO {

	private String onlineid;
	private String userid;
	private Long begintime;
	private Long onlinetime;
	private Long lasttime;
	private String ip;
	public String getOnlineid() {
		return onlineid;
	}
	public void setOnlineid(String _onlineid) {
		firePropertyChange("onlineid", onlineid, _onlineid);
		onlineid = _onlineid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public Long getBegintime() {
		return begintime;
	}
	public void setBegintime(Long _begintime) {
		firePropertyChange("begintime", begintime, _begintime);
		begintime = _begintime;
	}
	public Long getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(Long _onlinetime) {
		firePropertyChange("onlinetime", onlinetime, _onlinetime);
		onlinetime = _onlinetime;
	}
	public Long getLasttime() {
		return lasttime;
	}
	public void setLasttime(Long _lasttime) {
		firePropertyChange("lasttime", lasttime, _lasttime);
		lasttime = _lasttime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String _ip) {
		firePropertyChange("ip", ip, _ip);
		ip = _ip;
	}
	protected void setupFields() throws DAOException {
		addField("onlineid", "ONLINEID");
		addField("userid", "USERID");
		addField("begintime", "BEGINTIME");
		addField("onlinetime", "ONLINETIME");
		addField("lasttime", "LASTTIME");
		addField("ip", "IP");
		setTableName("BBS_ONLINE");
		addKey("ONLINEID");
		addUUID("ONLINEID");
	}
	public BbsOnlineDAO(Connection conn) {
		super(conn);
	}
	public BbsOnlineDAO() {
		super();
	}
}
