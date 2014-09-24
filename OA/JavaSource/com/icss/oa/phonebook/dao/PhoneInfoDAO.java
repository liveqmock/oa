/*
 * Created on 2004-12-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PhoneInfoDAO extends DAO {

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
	
	String hrid;

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

	public String getHrid(){
		return hrid;
	}
	
	public void setHrid(String _hrid){
		firePropertyChange("hrid", hrid, _hrid);
		hrid = _hrid;
	}
	
	protected void setupFields() throws DAOException {
		addField("noteid", "NOTEID");
		addField("orguuid", "ORGUUID");
		addField("useruuid", "USERUUID");
		addField("username", "USERNAME");
		addField("officephone", "OFFICEPHONE");
		addField("homephone", "HOMEPHONE");
		addField("netphone", "NETPHONE");
		addField("mobilephone", "MOBILEPHONE");
		addField("faxphone", "FAXPHONE");
		addField("officeaddress", "OFFICEADDRESS");
		addField("function", "FUNCTION");
		addField("ispermission", "ISPERMISSION");
		addField("maintanperson", "MAINTANPERSON");
		addField("lastmaintantime", "LASTMAINTANTIME");
		addField("noteorder", "NOTEORDER");
		addField("nameids", "NAMEIDS");
		addField("isparttime", "ISPARTTIME");
		addField("hrid","HRID");
		setTableName("PHONE_INFO");
		addKey("NOTEID");
		this.setAutoIncremented("NOTEID");
	}

	public PhoneInfoDAO(Connection conn) {
		super(conn);
	}

	public PhoneInfoDAO() {
		super();
	}
}
