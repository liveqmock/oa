/*
 * Created on 2004-12-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressGroupinfoSysPersonSearch1VO extends ValueObject {

	private String personuuid;

	private String userid;

	private Integer groupid;

	private String grouptype;

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

	public void setGroupid(Integer _groupid) {
		groupid = _groupid;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGrouptype(String _grouptype) {
		grouptype = _grouptype;
	}

	public String getGrouptype() {
		return grouptype;
	}

	
}
