/*
 * Created on 2004-7-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SysOrgpersonSysPersonSearchDAO extends SearchDAO {

    private String sql;
	private String personuuid;
	private String orguuid;
	private String isbelong;
	private String userid;
	private String cnname;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	protected void setupFields() throws DAOException {
		addField("personuuid", "SYS_ORGPERSON.PERSONUUID");
		addField("orguuid", "SYS_ORGPERSON.ORGUUID");
		addField("isbelong", "SYS_ORGPERSON.ISBELONG");
		addField("userid", "SYS_PERSON.USERID");
		addField("cnname", "SYS_PERSON.CNNAME");
	}
	protected String getSearchSQL() {
		return this.sql;
	}
	public SysOrgpersonSysPersonSearchDAO() {
		super();
	}
	public void setSearchSQL(String sql)
	{
		this.sql = sql;
	}
}
