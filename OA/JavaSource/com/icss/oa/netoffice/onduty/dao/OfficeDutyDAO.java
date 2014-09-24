/*
 * Created on 2004-8-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OfficeDutyDAO extends DAO {

	private Integer dutyid;
	private String personuuid;
	private String orguuid;
	private Long starttime;
	private Long endtime;
	private String dutyname;
	public Integer getDutyid() {
		return dutyid;
	}
	public void setDutyid(Integer _dutyid) {
		firePropertyChange("dutyid", dutyid, _dutyid);
		dutyid = _dutyid;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long _starttime) {
		firePropertyChange("starttime", starttime, _starttime);
		starttime = _starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long _endtime) {
		firePropertyChange("endtime", endtime, _endtime);
		endtime = _endtime;
	}
	public String getDutyname() {
		return dutyname;
	}
	public void setDutyname(String _dutyname) {
		firePropertyChange("dutyname", dutyname, _dutyname);
		dutyname = _dutyname;
	}
	protected void setupFields() throws DAOException {
		addField("dutyid", "DUTYID");
		addField("personuuid", "PERSONUUID");
		addField("orguuid", "ORGUUID");
		addField("starttime", "STARTTIME");
		addField("endtime", "ENDTIME");
		addField("dutyname", "DUTYNAME");
		setTableName("OFFICE_DUTY");
		addKey("DUTYID");
		setAutoIncremented("DUTYID");
	}
	public OfficeDutyDAO(Connection conn) {
		super(conn);
	}
	public OfficeDutyDAO() {
		super();
	}
}
