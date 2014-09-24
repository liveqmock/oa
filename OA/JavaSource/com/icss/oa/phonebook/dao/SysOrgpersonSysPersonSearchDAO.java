/*
 * Created on 2004-12-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgpersonSysPersonSearchDAO extends SearchDAO {

	String personuuid;
	String orguuid;
	String isbelong;
	String userid;
	String deltag;
	String cnname;
	String job;
	String idnum;
	Integer sex;
	String sql;

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

	public String getJob() {
		return job;
	}

	public void setJob(String _job) {
		firePropertyChange("job", job, _job);
		job = _job;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String _idnum) {
		firePropertyChange("idnum", idnum, _idnum);
		idnum = _idnum;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer _sex) {
		firePropertyChange("sex", sex, _sex);
		sex = _sex;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "SYS_ORGPERSON.PERSONUUID");
		addField("orguuid", "SYS_ORGPERSON.ORGUUID");
		addField("isbelong", "SYS_ORGPERSON.ISBELONG");
		addField("userid", "SYS_PERSON.USERID");
		addField("deltag", "SYS_PERSON.DELTAG");
		addField("cnname", "SYS_PERSON.CNNAME");
		addField("job", "SYS_PERSON.JOB");
		addField("idnum", "SYS_PERSON.IDNUM");
		addField("sex", "SYS_PERSON.SEX");
	}

	public String getSearchSQL() {
		return this.sql;
	}
	
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}

	public SysOrgpersonSysPersonSearchDAO() {
		super();
	}
}
