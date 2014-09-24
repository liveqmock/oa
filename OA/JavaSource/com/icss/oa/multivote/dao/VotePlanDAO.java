package com.icss.oa.multivote.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class VotePlanDAO extends DAO {

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
		firePropertyChange("planPlanid", planPlanid, _planPlanid);
		planPlanid = _planPlanid;
	}

	public Integer getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Integer _planNum) {
		firePropertyChange("planNum", planNum, _planNum);
		planNum = _planNum;
	}

	public String getPlanScale() {
		return planScale;
	}

	public void setPlanScale(String _planScale) {
		firePropertyChange("planScale", planScale, _planScale);
		planScale = _planScale;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String _planName) {
		firePropertyChange("planName", planName, _planName);
		planName = _planName;
	}

	public String getPlanDesc() {
		return planDesc;
	}

	public void setPlanDesc(String _planDesc) {
		firePropertyChange("planDesc", planDesc, _planDesc);
		planDesc = _planDesc;
	}

	public String getPlanOrg() {
		return planOrg;
	}

	public void setPlanOrg(String _planOrg) {
		firePropertyChange("planOrg", planOrg, _planOrg);
		planOrg = _planOrg;
	}

	public Integer getPlanPersonnum() {
		return planPersonnum;
	}

	public void setPlanPersonnum(Integer _planPersonnum) {
		firePropertyChange("planPersonnum", planPersonnum, _planPersonnum);
		planPersonnum = _planPersonnum;
	}

	public Integer getPlanNumlow() {
		return planNumlow;
	}

	public void setPlanNumlow(Integer _planNumlow) {
		firePropertyChange("planNumlow", planNumlow, _planNumlow);
		planNumlow = _planNumlow;
	}

	public Integer getPlanNumhigh() {
		return planNumhigh;
	}

	public void setPlanNumhigh(Integer _planNumhigh) {
		firePropertyChange("planNumhigh", planNumhigh, _planNumhigh);
		planNumhigh = _planNumhigh;
	}

	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Integer _planStatus) {
		firePropertyChange("planStatus", planStatus, _planStatus);
		planStatus = _planStatus;
	}

	public String getPlanSeason() {
		return planSeason;
	}

	public void setPlanSeason(String _planSeason) {
		firePropertyChange("planSeason", planSeason, _planSeason);
		planSeason = _planSeason;
	}

	protected void setupFields() throws DAOException {
		addField("planPlanid", "PLAN_PLANID");
		addField("planNum", "PLAN_NUM");
		addField("planScale", "PLAN_SCALE");
		addField("planName", "PLAN_NAME");
		addField("planDesc", "PLAN_DESC");
		addField("planOrg", "PLAN_ORG");
		addField("planPersonnum", "PLAN_PERSONNUM");
		addField("planNumlow", "PLAN_NUMLOW");
		addField("planNumhigh", "PLAN_NUMHIGH");
		addField("planStatus", "PLAN_STATUS");
		addField("planSeason", "PLAN_SEASON");
		setTableName("VOTE_PLAN");
		addKey("PLAN_PLANID");
		setAutoIncremented("PLAN_PLANID");
	}

	public VotePlanDAO(Connection conn) {
		super(conn);
	}

	public VotePlanDAO() {
		super();
	}

}
