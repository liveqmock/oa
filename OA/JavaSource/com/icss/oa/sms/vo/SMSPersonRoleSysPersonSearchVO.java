package com.icss.oa.sms.vo;

import com.icss.j2ee.vo.ValueObject;

public class SMSPersonRoleSysPersonSearchVO extends ValueObject {

	private String personuuid;

	private String userid;

	private Integer id;

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

	public void setId(Integer _id) {
		id = _id;
	}

	public Integer getId() {
		return id;
	}

}
