/*
 * Created on 2004-12-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PhoneSysNameDAO extends SearchDAO {

	Integer noteid;

	String orguuid;

	String useruuid;

	String username;

	String officephone;

	String homephone;

	String netphone;

	String mobilephone;

	String faxphone;

	String officeaddress;

	String function;

	String ispermission;

	String maintanperson;

	Long lastmaintantime;

	Integer noteorder;

	String nameids;

	String isparttime;

    String cnname;
	
	String sql;

	public Integer getNoteid() {
		return noteid;
	}

	public void setNoteid(Integer _noteid) {
		firePropertyChange("noteid", noteid, _noteid);
		noteid = _noteid;
	}

	public String getOrguuid() {
		return orguuid;
	}

	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}

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

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String _officephone) {
		firePropertyChange("officephone", officephone, _officephone);
		officephone = _officephone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String _homephone) {
		firePropertyChange("homephone", homephone, _homephone);
		homephone = _homephone;
	}

	public String getNetphone() {
		return netphone;
	}

	public void setNetphone(String _netphone) {
		firePropertyChange("netphone", netphone, _netphone);
		netphone = _netphone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String _mobilephone) {
		firePropertyChange("mobilephone", mobilephone, _mobilephone);
		mobilephone = _mobilephone;
	}

	public String getFaxphone() {
		return faxphone;
	}

	public void setFaxphone(String _faxphone) {
		firePropertyChange("faxphone", faxphone, _faxphone);
		faxphone = _faxphone;
	}

	public String getOfficeaddress() {
		return officeaddress;
	}

	public void setOfficeaddress(String _officeaddress) {
		firePropertyChange("officeaddress", officeaddress, _officeaddress);
		officeaddress = _officeaddress;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String _function) {
		firePropertyChange("function", function, _function);
		function = _function;
	}

	public String getIspermission() {
		return ispermission;
	}

	public void setIspermission(String _ispermission) {
		firePropertyChange("ispermission", ispermission, _ispermission);
		ispermission = _ispermission;
	}

	public String getMaintanperson() {
		return maintanperson;
	}

	public void setMaintanperson(String _maintanperson) {
		firePropertyChange("maintanperson", maintanperson, _maintanperson);
		maintanperson = _maintanperson;
	}

	public Long getLastmaintantime() {
		return lastmaintantime;
	}

	public void setLastmaintantime(Long _lastmaintantime) {
		firePropertyChange("lastmaintantime", lastmaintantime, _lastmaintantime);
		lastmaintantime = _lastmaintantime;
	}

	public Integer getNoteorder() {
		return noteorder;
	}

	public void setNoteorder(Integer _noteorder) {
		firePropertyChange("noteorder", noteorder, _noteorder);
		noteorder = _noteorder;
	}

	public String getNameids() {
		return nameids;
	}

	public void setNameids(String _nameids) {
		firePropertyChange("nameids", nameids, _nameids);
		nameids = _nameids;
	}

	public String getIsparttime() {
		return isparttime;
	}

	public void setIsparttime(String _isparttime) {
		firePropertyChange("isparttime", isparttime, _isparttime);
		isparttime = _isparttime;
	}

	public String getCnname() {
		return isparttime;
	}

	public void setCnname(String _isparttime) {
		firePropertyChange("isparttime", isparttime, _isparttime);
		isparttime = _isparttime;
	}

	
	

	protected void setupFields() throws DAOException {
		addField("noteid", "PHONE_INFO.NOTEID");
		addField("orguuid", "PHONE_INFO.ORGUUID");
		addField("useruuid", "PHONE_INFO.USERUUID");
		addField("username", "PHONE_INFO.USERNAME");
		addField("officephone", "PHONE_INFO.OFFICEPHONE");
		addField("homephone", "PHONE_INFO.HOMEPHONE");
		addField("netphone", "PHONE_INFO.NETPHONE");
		addField("mobilephone", "PHONE_INFO.MOBILEPHONE");
		addField("faxphone", "PHONE_INFO.FAXPHONE");
		addField("officeaddress", "PHONE_INFO.OFFICEADDRESS");
		addField("function", "PHONE_INFO.FUNCTION");
		addField("ispermission", "PHONE_INFO.ISPERMISSION");
		addField("maintanperson", "PHONE_INFO.MAINTANPERSON");
		addField("lastmaintantime", "PHONE_INFO.LASTMAINTANTIME");
		addField("noteorder", "PHONE_INFO.NOTEORDER");
		addField("nameids", "PHONE_INFO.NAMEIDS");
		addField("isparttime", "PHONE_INFO.ISPARTTIME");
		addField("cnname", "SYS_ORG.CNNAME");
	}

	public String getSearchSQL() {
		return sql;
	}

	public void setSearchSQL(String _sql){
		this.sql = _sql;
	}
	public PhoneSysNameDAO() {
		super();
	}
}
