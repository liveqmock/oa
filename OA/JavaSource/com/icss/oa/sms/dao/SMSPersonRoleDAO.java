package com.icss.oa.sms.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class SMSPersonRoleDAO extends DAO {

	private Integer id;
	private String userid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	protected void setupFields() throws DAOException {
		addField("id", "SMSR_ID");
		addField("userid", "USERID");
		setTableName("TB_SMS_PERSONROLE");
	}
	public SMSPersonRoleDAO(Connection conn) {
		super(conn);
	}
	public SMSPersonRoleDAO() {
		super();
	}

}
