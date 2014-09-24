package com.icss.oa.sms.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;


public class SMSIdDAO extends DAO {
	
	private String uuid;
	private String smsid;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}
	
	public String getSmsid() {
		return smsid;
	}
	public void setSmsid(String _smsid) {
		firePropertyChange("smsid", smsid, _smsid);
		smsid = _smsid;
	}
	
		
	protected void setupFields() throws DAOException {
		
		addField("uuid", "PERSONUUID");
		addField("smsid", "SMSID");
		setTableName("SYS_PERSON");
	}
	
	public SMSIdDAO(Connection conn) {
		super(conn);
	}
	public SMSIdDAO() {
		super();
	}

}