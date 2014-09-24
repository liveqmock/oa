/*
 * Created on 2004-7-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysPersonDAO extends DAO {

	String personuuid;

	String cnname;

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "PERSONUUID");
		addField("cnname", "CNNAME");
		setTableName("SYS_PERSON");
	}

	public SysPersonDAO(Connection conn) {
		super(conn);
	}

	public SysPersonDAO() {
		super();
	}
}
