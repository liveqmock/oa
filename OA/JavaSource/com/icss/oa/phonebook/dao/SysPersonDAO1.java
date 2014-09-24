/*
 * Created on 2005-6-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysPersonDAO1 extends DAO {

	private String personuuid;

	private String job;

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String _job) {
		firePropertyChange("job", job, _job);
		job = _job;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "PERSONUUID");
		addField("job", "JOB");
		setTableName("SYS_PERSON");
	}

	public SysPersonDAO1(Connection conn) {
		super(conn);
	}

	public SysPersonDAO1() {
		super();
	}
}
