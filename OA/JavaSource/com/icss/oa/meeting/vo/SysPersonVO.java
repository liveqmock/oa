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
public class SysPersonVO extends ValueObject {

	private String personuuid;

	private String cnname;

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setCnname(String _cnname) {
		cnname = _cnname;
	}

	public String getCnname() {
		return cnname;
	}
}
