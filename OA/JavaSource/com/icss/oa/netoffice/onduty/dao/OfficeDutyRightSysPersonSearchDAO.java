/*
 * Created on 2005-1-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.netoffice.onduty.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OfficeDutyRightSysPersonSearchDAO extends SearchDAO {

	String userid;

	String deltag;

	String cnname;

	String orguuid;

	String personuuid;
	
	String sql;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String _deltag) {
		firePropertyChange("deltag", deltag, _deltag);
		deltag = _deltag;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}

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
		addField("userid", "SYS_PERSON.USERID");
		addField("deltag", "SYS_PERSON.DELTAG");
		addField("cnname", "SYS_PERSON.CNNAME");
		addField("orguuid", "OFFICE_DUTY_RIGHT.ORGUUID");
		addField("personuuid", "OFFICE_DUTY_RIGHT.PERSONUUID");
	}

	public String getSearchSQL() {
		return sql.toString();
	}
	
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}

	public OfficeDutyRightSysPersonSearchDAO() {
		super();
	}
}
