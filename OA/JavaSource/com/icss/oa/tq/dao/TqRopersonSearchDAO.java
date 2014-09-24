package com.icss.oa.tq.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

public class TqRopersonSearchDAO extends SearchDAO {

	private String personuuid;

	private String cnname;
	
	private String sex;
	
	private String officetel;
	
	private String email;
	
	private String userid;

	private String password;
	
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
	
	public String  getSex() {
		return sex;
	}

	public void setSex(String _sex) {
		firePropertyChange("sex", sex, _sex);
		sex = _sex;
	}
	
	public String getOfficetel() {
		return officetel;
	}

	public void setOfficetel(String _officetel) {
		firePropertyChange("officetel", officetel, _officetel);
		officetel = _officetel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String _email) {
		firePropertyChange("email", email, _email);
		email = _email;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		firePropertyChange("password", password, _password);
		password = _password;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "RO_PERSON.PERSONUUID");
		addField("cnname", "RO_PERSON.CNNAME");
		addField("sex", "RO_PERSON.SEX");
		addField("officetel", "RO_PERSON.OFFICETEL");
		addField("email", "RO_PERSON.EMAIL1");
		addField("userid", "RO_PERSONACCOUNT.USERID");
		addField("password", "RO_PERSONACCOUNT.PASSWORD");
		
	}

	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	private String sql;

	public TqRopersonSearchDAO() {
		super();
	}
}
