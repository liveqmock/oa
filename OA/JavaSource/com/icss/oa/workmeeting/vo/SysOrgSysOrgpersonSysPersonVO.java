/*
 * Created on 2004-12-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.workmeeting.vo;  

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgSysOrgpersonSysPersonVO extends ValueObject {

	String orguuid;

	String cnname;

	Integer orglevel;

	String personuuid;

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

	public void setOrglevel(Integer _orglevel) {
		orglevel = _orglevel;
	}

	public Integer getOrglevel() {
		return orglevel;
	}

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}
}
