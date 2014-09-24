package com.icss.oa.hr.vo;

import com.icss.j2ee.vo.ValueObject;

public class HRPersonTempVO extends ValueObject {


	private String hrid;
    private String username;
    private String deptcode;
    private String orgcode;
    private Integer flag;
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    
}