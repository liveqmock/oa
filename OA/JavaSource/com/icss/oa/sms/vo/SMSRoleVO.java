package com.icss.oa.sms.vo;

import com.icss.j2ee.vo.ValueObject;

public class SMSRoleVO extends ValueObject {
	private Integer id;
	private String rolename;
	private Integer isout;
	private Integer ishistory;
	private Integer sendnumber;
	private String roledes;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String _rolename) {
		rolename = _rolename;
	}
	public Integer getIsout() {
		return isout;
	}
	public void setIsout(Integer _isout) {
		isout = _isout;
	}
	public Integer getIshistory() {
		return ishistory;
	}
	public void setIshistory(Integer _ishistory) {
		ishistory = _ishistory;
	}
	public Integer getSendnumber() {
		return sendnumber;
	}
	public void setSendnumber(Integer _sendnumber) {
		sendnumber = _sendnumber;
	}
	public String getRoledes() {
		return roledes;
	}
	public void setRoledes(String _roledes) {
		roledes = _roledes;
	}
	
}