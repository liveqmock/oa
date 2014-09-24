/*
 * Created on 2004-6-1
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SysPersonVO extends ValueObject {

	private String personuuid;
	private String userid;
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public String getUserid() {
		return userid;
	}
}
