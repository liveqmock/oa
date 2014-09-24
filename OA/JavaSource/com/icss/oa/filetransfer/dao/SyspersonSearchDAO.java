package com.icss.oa.filetransfer.dao;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class SyspersonSearchDAO extends DAO {

	private String personuuid;

	private String cnname;
	
	private String userid;
	
	protected void setupFields() throws DAOException {
		addField("personuuid", "PERSONUUID");
		addField("cnname", "CNNAME");
		addField("userid", "USERID");
		setTableName("SYS_PERSON");
	}

	public SyspersonSearchDAO() {
		super();
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		this.cnname = _cnname;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
}
