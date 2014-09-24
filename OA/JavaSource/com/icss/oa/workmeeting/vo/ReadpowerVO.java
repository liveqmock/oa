package com.icss.oa.workmeeting.vo;

import com.icss.j2ee.vo.ValueObject;  

public class ReadpowerVO extends ValueObject {
	private String userid;
	private String cnname;
	private String personuuid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
}