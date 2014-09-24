package com.icss.oa.sms.vo;

import com.icss.j2ee.vo.ValueObject;

public class SMSIdVO extends ValueObject {
	
	private String uuid;
	private String smsid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSmsid() {
		return smsid;
	}
	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}
	
}