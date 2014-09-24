/*
 * Created on 2002-3-9
 *
 */
package com.icss.oa.address.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author sunchuanting
 *
 * 
 */
public class SysPersonDAO extends DAO {

	private String personuuid;

	private String cnname;

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
