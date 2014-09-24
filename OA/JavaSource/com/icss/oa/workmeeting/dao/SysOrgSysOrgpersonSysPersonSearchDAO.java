/*
 * Created on 2004-12-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.workmeeting.dao;  

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgSysOrgpersonSysPersonSearchDAO extends SearchDAO {

	String sql;
	
	String orguuid;

	String cnname;

	Integer orglevel;

	String personuuid;

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

	public Integer getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(Integer _orglevel) {
		firePropertyChange("orglevel", orglevel, _orglevel);
		orglevel = _orglevel;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	protected void setupFields() throws DAOException {
		addField("orguuid", "SYS_ORG.ORGUUID");
		addField("cnname", "SYS_ORG.CNNAME");
		addField("orglevel", "SYS_ORG.ORGLEVEL");
		addField("personuuid", "SYS_PERSON.PERSONUUID");
	}

	public String getSearchSQL() {
		return sql.toString();
	}
	
	public void setSearchSQL(String sql) {
		this.sql = sql;
	}


	public SysOrgSysOrgpersonSysPersonSearchDAO() {
		super();
	}
}
