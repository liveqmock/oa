/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PhoneInfoSysPersonVO extends ValueObject {
	private String mobilephone;
	private String personuuid;
	private String cnname;
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String _mobilephone) {
		mobilephone = _mobilephone;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
}