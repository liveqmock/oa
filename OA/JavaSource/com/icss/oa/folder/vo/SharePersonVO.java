/*
 * Created on 2004-5-23
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.folder.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SharePersonVO extends ValueObject {
	private String fscAccessright;
	private String cnname;
	private String personuuid;
	public String getFscAccessright() {
		return fscAccessright;
	}
	public void setFscAccessright(String _fscAccessright) {
		fscAccessright = _fscAccessright;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
}