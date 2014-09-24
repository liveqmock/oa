package com.icss.oa.hr.vo;

import com.icss.j2ee.vo.ValueObject;

public class HRSysPersonVO extends ValueObject {

	private String personuuid;
    private String userid;
    private String cnname;
    private String enname;
    private Integer sex;
    private String hometel;
    private String officetel;
    private String mobile;
    private String email1;
    private String job;
    private Integer tqid;
    private String hrid;
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String personuuid) {
		this.personuuid = personuuid;
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
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getHometel() {
		return hometel;
	}
	public void setHometel(String hometel) {
		this.hometel = hometel;
	}
	public String getOfficetel() {
		return officetel;
	}
	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getTqid() {
		return tqid;
	}
	public void setTqid(Integer tqid) {
		this.tqid = tqid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}


	
}