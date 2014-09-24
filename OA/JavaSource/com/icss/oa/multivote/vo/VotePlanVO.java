package com.icss.oa.multivote.vo;

import com.icss.j2ee.vo.ValueObject;

public class VotePlanVO extends ValueObject {
	private Integer planPlanid;

	private Integer planNum;

	private String planScale;

	private String planName;

	private String planDesc;

	private String planOrg;

	private Integer planPersonnum;

	private Integer planNumlow;

	private Integer planNumhigh;

	private Integer planStatus;

	private String planSeason;

	public Integer getPlanPlanid() {
		return planPlanid;
	}

	public void setPlanPlanid(Integer _planPlanid) {
		
		planPlanid = _planPlanid;
	}

	public Integer getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Integer _planNum) {
		
		planNum = _planNum;
	}

	public String getPlanScale() {
		return planScale;
	}

	public void setPlanScale(String _planScale) {
		
		planScale = _planScale;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String _planName) {
		
		planName = _planName;
	}

	public String getPlanDesc() {
		return planDesc;
	}

	public void setPlanDesc(String _planDesc) {
		
		planDesc = _planDesc;
	}

	public String getPlanOrg() {
		return planOrg;
	}

	public void setPlanOrg(String _planOrg) {
		
		planOrg = _planOrg;
	}

	public Integer getPlanPersonnum() {
		return planPersonnum;
	}

	public void setPlanPersonnum(Integer _planPersonnum) {
		
		planPersonnum = _planPersonnum;
	}

	public Integer getPlanNumlow() {
		return planNumlow;
	}

	public void setPlanNumlow(Integer _planNumlow) {
		
		planNumlow = _planNumlow;
	}

	public Integer getPlanNumhigh() {
		return planNumhigh;
	}

	public void setPlanNumhigh(Integer _planNumhigh) {
		
		planNumhigh = _planNumhigh;
	}

	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Integer _planStatus) {
		
		planStatus = _planStatus;
	}

	public String getPlanSeason() {
		return planSeason;
	}

	public void setPlanSeason(String _planSeason) {
		
		planSeason = _planSeason;
	}

}