package com.icss.oa.sms.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

public class SMSPersonRoleSysPersonSearchDAO extends SearchDAO {

	private String personuuid;

	private String userid;

	private Integer id;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "SYS_PERSON.PERSONUUID");
		addField("userid", "TB_SMS_PERSONROLE.USERID");
		addField("id", "TB_SMS_PERSONROLE.SMSR_ID");
		
	}

	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	private String sql;

	public SMSPersonRoleSysPersonSearchDAO() {
		super();
	}
}
