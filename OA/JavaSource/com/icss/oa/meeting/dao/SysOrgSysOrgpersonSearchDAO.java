/*
 * Created on 2004-7-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgSysOrgpersonSearchDAO extends SearchDAO {

	String orguuid;

	String cnname;

	String personuuid;

	String isbelong;

	public String getOrguuid() {
		return orguuid;
	}

	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public String getIsbelong() {
		return isbelong;
	}

	public void setIsbelong(String _isbelong) {
		firePropertyChange("isbelong", isbelong, _isbelong);
		isbelong = _isbelong;
	}

	protected void setupFields() throws DAOException {
		addField("orguuid", "SYS_ORG.ORGUUID");
		addField("cnname", "SYS_ORG.CNNAME");
		addField("personuuid", "SYS_ORGPERSON.PERSONUUID");
		addField("isbelong", "SYS_ORGPERSON.ISBELONG");
	}

	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	private String sql;

	public SysOrgSysOrgpersonSearchDAO() {
		super();
	}
}
