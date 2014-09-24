/*
 * Created on 2004-7-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SysOrgpersonSysPersonVO extends ValueObject {
	private String personuuid;
	private String orguuid;
	private String isbelong;
	private String userid;
	private String cnname;
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
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
}