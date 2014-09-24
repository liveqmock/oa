/*
 * Created on 2004-12-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PhoneJobnameDAO extends DAO {

	Integer nameid;

	String name;

	Integer jobid;

	String orguuid;

	public Integer getNameid() {
		return nameid;
	}

	public void setNameid(Integer _nameid) {
		firePropertyChange("nameid", nameid, _nameid);
		nameid = _nameid;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		firePropertyChange("name", name, _name);
		name = _name;
	}

	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer _jobid) {
		firePropertyChange("jobid", jobid, _jobid);
		jobid = _jobid;
	}

	public String getOrguuid() {
		return orguuid;
	}

	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}

	protected void setupFields() throws DAOException {
		addField("nameid", "NAMEID");
		addField("name", "NAME");
		addField("jobid", "JOBID");
		addField("orguuid", "ORGUUID");
		setTableName("PHONE_JOBNAME");
		addKey("NAMEID");
		this.setAutoIncremented("NAMEID");
	}

	public PhoneJobnameDAO(Connection conn) {
		super(conn);
	}

	public PhoneJobnameDAO() {
		super();
	}
}
