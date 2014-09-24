package com.icss.oa.sms.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;


public class HRSMSMobileSearchDAO extends SearchDAO {
	
	private String useruuid;
	private String username;
	private String userid;
	private String hrid;
	private String mobilephone;
	private String orgname;
	private String deptname;
	
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String _useruuid) {
		firePropertyChange("useruuid", useruuid, _useruuid);
		useruuid = _useruuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String _username) {
		firePropertyChange("username", username, _username);
		username = _username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String _hrid) {
		firePropertyChange("hrid", hrid, _hrid);
		hrid = _hrid;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String _mobilephone) {
		firePropertyChange("mobilephone", mobilephone, _mobilephone);
		mobilephone = _mobilephone;
	}
	
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String _orgname) {
		firePropertyChange("orgname", orgname, _orgname);
		orgname = _orgname;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String _deptname) {
		firePropertyChange("deptname", deptname, _deptname);
		deptname = _deptname;
	}
	
	
	protected void setupFields() throws DAOException {
		addField("useruuid", "SYS_PERSON.PERSONUUID");
		addField("username", "SYS_PERSON.CNNAME");
		addField("userid", "SYS_PERSON.USERID");
		addField("hrid", "SYS_PERSON.HRID");
		addField("mobilephone", "HRPERSON.MOBILEPHONE");
		addField("orgname", "HRPERSON.ORGNAME");
		addField("deptname", "HRPERSON.DEPTNAME");

	}
	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	private String sql;

	public HRSMSMobileSearchDAO() {
		super();
	}
}
