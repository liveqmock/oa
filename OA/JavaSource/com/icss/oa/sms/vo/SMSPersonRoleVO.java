package com.icss.oa.sms.vo;

import com.icss.j2ee.vo.ValueObject;

public class SMSPersonRoleVO extends ValueObject {
	private Integer id;
	private String userid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}

}