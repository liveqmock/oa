package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;


public class SelectOrgpersonVO extends ValueObject {
	private String userid;//��id����Աuserid
	private String useruuid;//��Աuseruuid
	private String name;//�����Ա����
	private String isperson;//��־��1Ϊ���ˣ�00Ϊ�������飬01Ϊ���˷���
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