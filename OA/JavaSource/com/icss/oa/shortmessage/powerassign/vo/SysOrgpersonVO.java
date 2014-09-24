/*
 * Created on 2004-5-15
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SysOrgpersonVO extends ValueObject {
	String personuuid;
	String orguuid;
	String isbelong;
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
