package com.icss.oa.sms.vo;

import com.icss.j2ee.vo.ValueObject;

public class SMSPersonRoleSearchVO extends ValueObject {

	private String personuuid;
	private Integer isout;
	private Integer ishistory;
	private Integer sendnumber;

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setIsout(Integer _isout) {
		isout = _isout;
	}

	public Integer getIsout() {
		return isout;
	}

	public void setIshistory(Integer _ishistory) {
		ishistory = _ishistory;
	}

	public Integer getIshistory() {
		return ishistory;
	}
	
	public void setSendnumber(Integer _sendnumber) {
		sendnumber = _sendnumber;
	}

	public Integer getSendnumber() {
		return sendnumber;
	}

}
