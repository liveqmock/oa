/*
 * Created on 2004-9-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgpersonDAO extends DAO {

	private String personuuid;

	private String orguuid;

	private String isbelong;

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

	public String getIsbelong() {
		return isbelong;
	}

	public void setIsbelong(String _isbelong) {
		firePropertyChange("isbelong", isbelong, _isbelong);
		isbelong = _isbelong;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "PERSONUUID");
		addField("orguuid", "ORGUUID");
		addField("isbelong", "ISBELONG");
		setTableName("SYS_ORGPERSON");
	}

	public SysOrgpersonDAO(Connection conn) {
		super(conn);
	}

	public SysOrgpersonDAO() {
		super();
	}
}
