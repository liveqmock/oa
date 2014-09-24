package com.icss.oa.multivote.vo;

import com.icss.j2ee.vo.ValueObject;

public class VotePersonVO extends ValueObject {

	private Integer personId;

	private Integer planPlanid;

	private String uuid;

	private String hrid;

	private String name;

	private String orgCode;

	private String orgName;

	private String depCode;

	private String depName;

	private String password;

	private String flag;

	private String orgParentName;

	private String headshipName;

	private String headshipLevelCode;

	private String headshipLevelName;

	private String firstflag;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer _personId) {
		
		personId = _personId;
	}

	public Integer getPlanPlanid() {
		return planPlanid;
	}

	public void setPlanPlanid(Integer _planPlanid) {
	
		planPlanid = _planPlanid;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String _name) {
	
		name = _name;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String _orgCode) {
		
		orgCode = _orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String _orgName) {
		
		orgName = _orgName;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String _depCode) {
		
		depCode = _depCode;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String _depName) {
	
		depName = _depName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		
		password = _password;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String _flag) {
		
		flag = _flag;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String _orgParentName) {
		
		orgParentName = _orgParentName;
	}

	public String getHeadshipName() {
		return headshipName;
	}

	public void setHeadshipName(String _headshipName) {
		
		headshipName = _headshipName;
	}

	public String getHeadshipLevelCode() {
		return headshipLevelCode;
	}

	public void setHeadshipLevelCode(String _headshipLevelCode) {

		headshipLevelCode = _headshipLevelCode;
	}

	public String getHeadshipLevelName() {
		return headshipLevelName;
	}

	public void setHeadshipLevelName(String _headshipLevelName) {
	
				
		headshipLevelName = _headshipLevelName;
	}

	public String getFirstflag() {
		return firstflag;
	}

	public void setFirstflag(String _firstflag) {
	
		firstflag = _firstflag;
	}
}