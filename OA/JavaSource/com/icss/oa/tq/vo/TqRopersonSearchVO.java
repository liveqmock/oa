package com.icss.oa.tq.vo;

import com.icss.j2ee.vo.ValueObject;


public class TqRopersonSearchVO extends ValueObject {
	private String personuuid;

	private String cnname;
	
	private String sex;
	
	private String officetel;
	
	private String email;
	
	private String userid;

	private String password;

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String personuuid) {
		this.personuuid = personuuid;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOfficetel() {
		return officetel;
	}

	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
