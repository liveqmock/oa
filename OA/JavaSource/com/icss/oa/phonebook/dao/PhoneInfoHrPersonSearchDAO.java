/*
 * Created on 2005-1-21
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
public class PhoneInfoHrPersonSearchDAO extends SearchDAO {

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
	String orgname;

	String deptname;
	
	String hrofficephone;
	String hrusername;
	
	String hrhomephone;
	String hrnetphone;
	String hrmobilephone;
	String hrfaxphone;
	String hrvpn;
	
	String hrorgcode;
	String hrorgname;
	String hrdeptcode;
	String hrdeptname;
	String hrjobcode;
	String hrjob;
	String hrhomeaddress;
	String hremail;
	
	
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
	
	
	public String getHrofficephone(){
		return hrofficephone;
	}
	public void setHrofficephone(String _hrofficephone) {
		firePropertyChange("hrofficephone", hrofficephone, _hrofficephone);
		hrofficephone = _hrofficephone;
	}
	
	public String getHrusername() {
		return hrusername;
	}
	public void setHrusername(String _hrusername) {
		firePropertyChange("hrusername", hrusername, _hrusername);
		hrusername = _hrusername;
	}
	
	
	
	
	public String getHrhomephone() {
		return hrhomephone;
	}
	public void setHrhomephone(String _hrhomephone) {
		firePropertyChange("hrhomephone", hrhomephone, _hrhomephone);
		hrhomephone = _hrhomephone;
	}
	
	public String getHrnetphone() {
		return hrnetphone;
	}
	public void setHrnetphone(String _hrnetphone) {
		firePropertyChange("hrnetphone", _hrnetphone, _hrnetphone);
		hrnetphone = _hrnetphone;
	}
	
	public String getHrmobilephone() {
		return hrmobilephone;
	}
	public void setHrmobilephone(String _hrmobilephone) {
		firePropertyChange("hrmobilephone", _hrmobilephone, _hrmobilephone);
		hrmobilephone = _hrmobilephone;
	}
	
	
	public String getHrfaxphone() {
		return hrfaxphone;
	}
	public void setHrfaxphone(String _hrfaxphone) {
		firePropertyChange("hrfaxphone", _hrfaxphone, _hrfaxphone);
		hrfaxphone = _hrfaxphone;
	}
	
	public String getHrvpn() {
		return hrvpn;
	}
	public void setHrvpn(String _hrvpn) {
		firePropertyChange("hrvpn", _hrvpn, _hrvpn);
		hrvpn = _hrvpn;
	}
	

	public String getHrorgcode() {
		return hrorgcode;
	}
	public void setHrorgcode(String _hrorgcode) {
		firePropertyChange("hrorgcode", _hrorgcode, _hrorgcode);
		hrorgcode = _hrorgcode;
	}
	
	public String getHrorgname() {
		return hrorgname;
	}
	public void setHrorgname(String _hrorgname) {
		firePropertyChange("hrorgname", _hrorgname, _hrorgname);
		hrorgname = _hrorgname;
	}
	
	
	public String getHrdeptcode() { 
		return hrdeptcode;
	}
	public void setHrdeptcode(String _hrdeptcode) {
		firePropertyChange("hrdeptcode", _hrdeptcode, _hrdeptcode);
		hrdeptcode = _hrdeptcode;
	}
	public String getHrdeptname() { 
		return hrdeptname;
	}
	public void setHrdeptname(String _hrdeptname) {
		firePropertyChange("hrdeptname", _hrdeptname, _hrdeptname);
		hrdeptname = _hrdeptname;
	}
	
	public String getHrjobcode() { 
		return hrjobcode;
	}
	public void setHrjobcode(String _hrjobcode) {
		firePropertyChange("hrjobcode", _hrjobcode, _hrjobcode);
		hrjobcode = _hrjobcode;
	}
	
	public String getHrjob() {
		return hrjob;
	}
	public void setHrjob(String _hrjob) {
		firePropertyChange("hrjob", _hrjob, _hrjob);
		hrjob = _hrjob;
	}
	
	public String getHrhomeaddress() {
		return hrhomeaddress;
	}
	public void setHrhomeaddress(String _hrhomeaddress) {
		firePropertyChange("hrhomeaddress", _hrhomeaddress, _hrhomeaddress);
		hrhomeaddress = _hrhomeaddress;
	}
	
	
	public String getHremail() {
		return hremail;
	}
	public void setHremail(String _hremail) {
		firePropertyChange("hremail", _hremail, _hremail);
		hremail = _hremail;
	}
	
	
	protected void setupFields() throws DAOException {
		addField("noteid", "HR_PHONE_INFO.PINOTEID");
		addField("orguuid", "HR_PHONE_INFO.PIORGUUID");
		addField("useruuid", "HR_PHONE_INFO.PIUSERUUID");
		addField("username", "HR_PHONE_INFO.PIUSERNAME");
		addField("officephone", "HR_PHONE_INFO.PIOFFICEPHONE");
		addField("homephone", "HR_PHONE_INFO.PIHOMEPHONE");
		addField("netphone", "HR_PHONE_INFO.PINETPHONE");
		addField("mobilephone", "HR_PHONE_INFO.PIMOBILEPHONE");
		addField("faxphone", "HR_PHONE_INFO.PIFAXPHONE");
		addField("officeaddress", "HR_PHONE_INFO.PIOFFICEADDRESS");
		addField("function", "HR_PHONE_INFO.PIFUNCTION");
		addField("ispermission", "HR_PHONE_INFO.PIISPERMISSION");
		addField("maintanperson", "HR_PHONE_INFO.PIMAINTANPERSON");
		addField("lastmaintantime", "HR_PHONE_INFO.PILASTMAINTANTIME");
		addField("noteorder", "HR_PHONE_INFO.PINOTEORDER");
		addField("nameids", "HR_PHONE_INFO.PINAMEIDS");
		addField("isparttime", "HR_PHONE_INFO.PIISPARTTIME");
		addField("orgname", "HR_PHONE_INFO.PIORGNAME");
		addField("deptname", "HR_PHONE_INFO.PIDEPTNAME");
		
		addField("hrusername", "HRPERSON.USERNAME");
		addField("hrofficephone", "HRPERSON.OFFICEPHONE");
		addField("hrhomephone", "HRPERSON.HOMEPHONE");
		addField("hrnetphone", "HRPERSON.NETPHONE");
		addField("hrmobilephone","HRPERSON.MOBILEPHONE");
		addField("hrfaxphone", "HRPERSON.FAXPHONE");
		addField("hrvpn","HRPERSON.HRVPN");
		
		addField("hrorgname","HRPERSON.ORGNAME");
		addField("hrdeptname","HRPERSON.DEPTNAME");
		addField("hrorgcode","HRPERSON.ORGCODE");
		addField("hrdeptcode","HRPERSON.DEPTCODE");
		addField("hrjobcode","HRPERSON.JOBCODE");
		addField("hrjob","HRPERSON.JOB");
		addField("hrhomeaddress","HRPERSON.HOMEADDRESS");
		addField("hremail","HRPERSON.EMAIL");
	}

	public String getSearchSQL() {
		return sql;
	}
	
	public void setSearchSQL(String _sql){
		sql = _sql;
	}
    
	public PhoneInfoHrPersonSearchDAO() {
		super();
	}
}   



    