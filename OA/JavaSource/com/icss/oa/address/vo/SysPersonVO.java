/*
 * Created on 2002-3-9
 *
 * 
 */
package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author sunchuanting
 *
 * 
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
