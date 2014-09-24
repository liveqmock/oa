package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

public class SimpleApplyBaseVO extends ValueObject {
	String uuid;
	String hrid;
	Integer state;
	String userid;
	String password;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		uuid = _uuid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String _hrid) {
		hrid = _hrid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer _state) {
		state = _state;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String _password) {
		password = _password;
	}
}