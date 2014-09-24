/*
 * Created on 2004-10-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgpersonVO extends ValueObject {

	private String personuuid;

	private String orguuid;

	private String isbelong;

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}

	public String getOrguuid() {
		return orguuid;
	}

	public void setIsbelong(String _isbelong) {
		isbelong = _isbelong;
	}

	public String getIsbelong() {
		return isbelong;
	}
}
