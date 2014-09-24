/*
 * Created on 2004-8-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgSysOrgpersonVO extends ValueObject {

	private String orguuid;

	private String cnname;

	private String personuuid;

	private String isbelong;

	private String sql;

	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}

	public String getOrguuid() {
		return orguuid;
	}

	public void setCnname(String _cnname) {
		cnname = _cnname;
	}

	public String getCnname() {
		return cnname;
	}

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setIsbelong(String _isbelong) {
		isbelong = _isbelong;
	}

	public String getIsbelong() {
		return isbelong;
	}

	public void setSql(String _sql) {
		sql = _sql;
	}

	public String getSql() {
		return sql;
	}
}
