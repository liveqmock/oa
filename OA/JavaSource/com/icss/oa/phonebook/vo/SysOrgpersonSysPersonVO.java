/*
 * Created on 2004-12-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgpersonSysPersonVO extends ValueObject {
	String personuuid;
	String orguuid;
	String isbelong;
	String userid;
	String deltag;
	String cnname;
	String job;
	String idnum;
	Integer sex;
	Integer sequenceno;
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
	public String getIsbelong() {
		return isbelong;
	}
	public void setIsbelong(String _isbelong) {
		isbelong = _isbelong;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String _deltag) {
		deltag = _deltag;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String _job) {
		job = _job;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String _idnum) {
		idnum = _idnum;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer _sex) {
		sex = _sex;
	}
	public Integer getSequenceno() {
		return sequenceno;
	}
	public void setSequenceno(Integer _sequenceno) {
		sequenceno = _sequenceno;
	}
}