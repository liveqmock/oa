package com.icss.oa.sync.vo;


import com.icss.j2ee.vo.ValueObject;

public class SysPersonVO extends ValueObject {

	private String personuuid;
    private String userid;
    
    private String cnname;
	private String sex;
	private String mobile;
	private String officetel;
	private String email1;
	private String email2;
	private String deltag;
    
    
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		this.personuuid = _personuuid;
	}
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String _sex) {
		sex = _sex;
	}
	
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String _mobile) {
		mobile = _mobile;
	}
	
	
	public String getOfficetel() {
		return officetel;
	}

	public void setOfficetel(String _officetel) {
		officetel = _officetel;
	}
	
	
	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String _email1) {
		email1 = _email1;
	}
	
	
	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String _email2) {
		email2 = _email2;
	}
	
	
	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String _deltag) {
		deltag = _deltag;
	}
	
	
}