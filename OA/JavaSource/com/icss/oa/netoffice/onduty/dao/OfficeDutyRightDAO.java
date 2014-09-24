/*
 * Created on 2005-1-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.netoffice.onduty.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OfficeDutyRightDAO extends DAO {

	String orguuid;

	String personuuid;

	public String getOrguuid() {
		return orguuid;
	}

	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	protected void setupFields() throws DAOException {
		addField("orguuid", "ORGUUID");
		addField("personuuid", "PERSONUUID");
		setTableName("OFFICE_DUTY_RIGHT");
		addKey("ORGUUID");
		addKey("PERSONUUID");
	}

	public OfficeDutyRightDAO(Connection conn) {
		super(conn);
	}

	public OfficeDutyRightDAO() {
		super();
	}
}
