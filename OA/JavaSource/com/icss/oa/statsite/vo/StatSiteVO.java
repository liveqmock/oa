package com.icss.oa.statsite.vo;

import com.icss.j2ee.vo.ValueObject;

public class StatSiteVO extends ValueObject {
	private Integer id;
	private String moduleid;
	private String userid;
	private Long time;
	private String ip;
	private String address;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String _moduleid) {
		moduleid = _moduleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long _time) {
		time = _time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String _ip) {
		ip = _ip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String _address) {
		address = _address;
	}
	
	
	
}