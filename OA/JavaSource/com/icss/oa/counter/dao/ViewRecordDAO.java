package com.icss.oa.counter.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class ViewRecordDAO extends DAO {

	private Integer id;

	private String site;

	private long date;

	private String useruuid;

	private String agent;

	private String osVersion;

	private String ip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long _date) {
		firePropertyChange("date", date, _date);
		date = _date;
	}

	public String getUseruuid() {
		return useruuid;
	}

	public void setUseruuid(String _useruuid) {
		firePropertyChange("useruuid", useruuid, _useruuid);
		useruuid = _useruuid;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String _agent) {
		firePropertyChange("agent", agent, _agent);
		agent = _agent;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String _osVersion) {
		firePropertyChange("osVersion", osVersion, _osVersion);
		osVersion = _osVersion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String _ip) {
		firePropertyChange("ip", ip, _ip);
		ip = _ip;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String _site) {
		firePropertyChange("site", site, _site);
		site = _site;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("site", "SITE");
		addField("date", "TIME");
		addField("useruuid", "USERUUID");
		addField("agent", "AGENT");
		addField("osVersion", "OSVERSION");
		addField("ip", "IP");
		setTableName("VIEWRECORD");
		addKey("ID");
		this.setAutoIncremented("ID");
	}

	public ViewRecordDAO(Connection conn) {
		super(conn);
	}

	public ViewRecordDAO() {
		super();
	}
}
