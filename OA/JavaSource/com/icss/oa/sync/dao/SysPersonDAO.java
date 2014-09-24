package com.icss.oa.sync.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class SysPersonDAO extends DAO {

	private String personuuid;
	
	
	private String userid;
	
	private String cnname;
	private String sex;
	private String mobile;
	private String officetel;
	private String email1;
	private String email2;
	private String deltag;
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}
	
	
	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String _sex) {
		firePropertyChange("sex", sex, _sex);
		sex = _sex;
	}
	
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String _mobile) {
		firePropertyChange("mobile", mobile, _mobile);
		mobile = _mobile;
	}
	
	
	public String getOfficetel() {
		return officetel;
	}

	public void setOfficetel(String _officetel) {
		firePropertyChange("officetel", officetel, _officetel);
		officetel = _officetel;
	}
	
	
	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String _email1) {
		firePropertyChange("email1", email1, _email1);
		email1 = _email1;
	}
	
	
	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String _email2) {
		firePropertyChange("email2", email2, _email2);
		email2 = _email2;
	}
	
	
	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String _deltag) {
		firePropertyChange("deltag", deltag, _deltag);
		deltag = _deltag;
	}
	

	protected void setupFields() throws DAOException {
		addField("userid", "USERID");
		
		addField("cnname", "CNNAME");
		addField("sex", "SEX");
		addField("mobile", "MOBILE");
		addField("officetel", "OFFICETEL");
		addField("email1", "EMAIL1");
		addField("email2", "EMAIL2");
		addField("deltag", "DELTAG");
		
		setTableName("SYS_PERSON");
	}

	public SysPersonDAO(Connection conn) {
		super(conn);
	}

	public SysPersonDAO() {
		super();
	}
}
