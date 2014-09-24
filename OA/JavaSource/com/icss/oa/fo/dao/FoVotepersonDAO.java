package com.icss.oa.fo.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FoVotepersonDAO extends DAO {
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
	
	private	String	firstflag;
	
	public Integer getPlanPlanid() {
		return planPlanid;
	}

	public void setPlanPlanid(Integer _planPlanid) {
		firePropertyChange("planPlanid", planPlanid, _planPlanid);
		planPlanid = _planPlanid;
	}
	
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer _personId) {
		firePropertyChange("personId", personId, _personId);
		personId = _personId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	public String getHrid() {
		return hrid;
	}

	public void setHrid(String _hrid) {
		firePropertyChange("hrid", hrid, _hrid);
		hrid = _hrid;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		firePropertyChange("name", name, _name);
		name = _name;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String _orgCode) {
		firePropertyChange("orgCode", orgCode, _orgCode);
		orgCode = _orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String _orgName) {
		firePropertyChange("orgName", orgName, _orgName);
		orgName = _orgName;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String _depCode) {
		firePropertyChange("depCode", depCode, _depCode);
		depCode = _depCode;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String _depName) {
		firePropertyChange("depName", depName, _depName);
		depName = _depName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		firePropertyChange("password", password, _password);
		password = _password;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String _flag) {
		firePropertyChange("flag", flag, _flag);
		flag = _flag;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String _orgParentName) {
		firePropertyChange("orgParentName", orgParentName, _orgParentName);
		orgParentName = _orgParentName;
	}

	public String getHeadshipName() {
		return headshipName;
	}

	public void setHeadshipName(String _headshipName) {
		firePropertyChange("headshipName", headshipName, _headshipName);
		headshipName = _headshipName;
	}

	public String getHeadshipLevelCode() {
		return headshipLevelCode;
	}

	public void setHeadshipLevelCode(String _headshipLevelCode) {
		firePropertyChange("headshipLevelCode", headshipLevelCode,
				_headshipLevelCode);
		headshipLevelCode = _headshipLevelCode;
	}

	public String getHeadshipLevelName() {
		return headshipLevelName;
	}

	public void setHeadshipLevelName(String _headshipLevelName) {
		firePropertyChange("headshipLevelName", headshipLevelName,
				_headshipLevelName);
		headshipLevelName = _headshipLevelName;
	}
	public String getFirstflag() {
		return firstflag;
	}

	public void setFirstflag(String _firstflag) {
		firePropertyChange("firstflag", firstflag,
				_firstflag);
		firstflag = _firstflag;
	}

	protected void setupFields() throws DAOException {
		addField("personId","PERSON_ID");
		addField("planPlanid","PlAN_PLANID");
		addField("uuid", "UUID");
		addField("hrid", "HRID");
		addField("name", "NAME");
		addField("orgCode", "ORG_CODE");
		addField("orgName", "ORG_NAME");
		addField("depCode", "DEP_CODE");
		addField("depName", "DEP_NAME");
		addField("password", "PASSWORD");
		addField("flag", "FLAG");
		addField("orgParentName", "ORG_PARENT_NAME");
		addField("headshipName", "HEADSHIP_NAME");
		addField("headshipLevelCode", "HEADSHIP_LEVEL_CODE");
		addField("headshipLevelName", "HEADSHIP_LEVEL_NAME");
		addField("firstflag","FIRSTFLAG");
		setTableName("FO_VOTEPERSON");
		addKey("PERSON_ID");
		setAutoIncremented("PERSON_ID");
	}

	public FoVotepersonDAO(Connection conn) {
		super(conn);
	}

	public FoVotepersonDAO() {
		super();
	}

}
