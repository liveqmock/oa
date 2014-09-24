package com.icss.oa.workmeeting.vo;

import com.icss.j2ee.vo.ValueObject;

public class GzwjviewRecVO extends ValueObject { 
	private Integer wrNo;
	private String username;
	private String truename;
	private Integer officeid;
	private String officenname;
	private String ip;
	private Long viewtime;
	public Integer getWrNo() {
		return wrNo;
	}
	public void setWrNo(Integer _wrNo) {
		wrNo = _wrNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String _username) {
		username = _username;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String _truename) {
		truename = _truename;
	}
	public Integer getOfficeid() {
		return officeid;
	}
	public void setOfficeid(Integer _officeid) {
		officeid = _officeid;
	}
	public String getOfficenname() {
		return officenname;
	}
	public void setOfficenname(String _officenname) {
		officenname = _officenname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String _ip) {
		ip = _ip;
	}
	public Long getViewtime() {
		return viewtime;
	}
	public void setViewtime(Long _viewtime) {
		viewtime = _viewtime;
	}
}