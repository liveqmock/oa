/*
 * Created on 2004-12-23
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
public class PhoneJobDAO extends DAO {

	Integer jobId;

	String jobName;

	Integer jobLevel;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer _jobId) {
		firePropertyChange("jobId", jobId, _jobId);
		jobId = _jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String _jobName) {
		firePropertyChange("jobName", jobName, _jobName);
		jobName = _jobName;
	}

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer _jobLevel) {
		firePropertyChange("jobLevel", jobLevel, _jobLevel);
		jobLevel = _jobLevel;
	}

	protected void setupFields() throws DAOException {
		addField("jobId", "JOB_ID");
		addField("jobName", "JOB_NAME");
		addField("jobLevel", "JOB_LEVEL");
		setTableName("PHONE_JOB");
		addKey("JOB_ID");
		this.setAutoIncremented("JOB_ID");
	}

	public PhoneJobDAO(Connection conn) {
		super(conn);
	}

	public PhoneJobDAO() {
		super();
	}
}
