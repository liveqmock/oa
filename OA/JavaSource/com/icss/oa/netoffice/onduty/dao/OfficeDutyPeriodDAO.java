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
public class OfficeDutyPeriodDAO extends DAO {

	private Integer periodid;
	private String orguuid;
	private Long periodstart;
	private Long periodend;
	public Integer getPeriodid() {
		return periodid;
	}
	public void setPeriodid(Integer _periodid) {
		firePropertyChange("periodid", periodid, _periodid);
		periodid = _periodid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}
	public Long getPeriodstart() {
		return periodstart;
	}
	public void setPeriodstart(Long _periodstart) {
		firePropertyChange("periodstart", periodstart, _periodstart);
		periodstart = _periodstart;
	}
	public Long getPeriodend() {
		return periodend;
	}
	public void setPeriodend(Long _periodend) {
		firePropertyChange("periodend", periodend, _periodend);
		periodend = _periodend;
	}
	protected void setupFields() throws DAOException {
		addField("periodid", "PERIODID");
		addField("orguuid", "ORGUUID");
		addField("periodstart", "PERIODSTART");
		addField("periodend", "PERIODEND");
		setTableName("OFFICE_DUTY_PERIOD");
		addKey("PERIODID");
		setAutoIncremented("PERIODID");
	}
	public OfficeDutyPeriodDAO(Connection conn) {
		super(conn);
	}
	public OfficeDutyPeriodDAO() {
		super();
	}
}
