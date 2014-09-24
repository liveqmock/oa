package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;


public class SelectOrgpersonVO extends ValueObject {
	private String userid;//组id或人员userid
	private String useruuid;//人员useruuid
	private String name;//组或人员名称
	private String isperson;//标志，1为个人，00为公共分组，01为个人分组
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String _useruuid) {
		useruuid = _useruuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		name = _name;
	}
	public String getIsperson() {
		return isperson;
	}
	public void setIsperson(String _isperson) {
		isperson = _isperson;
	}
}