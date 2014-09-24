package com.icss.oa.sms.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

public class SMSPersonRoleSearchDAO extends SearchDAO {

	private String personuuid;
	private Integer isout;
	private Integer ishistory;
	private Integer sendnumber;
	
	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public Integer getIsout() {
		return isout;
	}

	public void setIsout(Integer _isout) {
		firePropertyChange("isout", isout, _isout);
		isout = _isout;
	}

	public Integer getIshistory() {
		return ishistory;
	}

	public void setIshistory(Integer _ishistory) {
		firePropertyChange("ishistory", ishistory, _ishistory);
		ishistory = _ishistory;
	}
	
	public Integer getSendnumber() {
		return sendnumber;
	}

	public void setSendnumber(Integer _sendnumber) {
		firePropertyChange("sendnumber", sendnumber, _sendnumber);
		sendnumber = _sendnumber;
	}
	
	

	protected void setupFields() throws DAOException {
		addField("personuuid", "TB_SMS_PERSONROLE.USERID");
		addField("isout", "TB_SMS_ROLE.ISOUT");
		addField("ishistory", "TB_SMS_ROLE.ISHISTORY");
		addField("sendnumber", "TB_SMS_ROLE.SENDNUMBER");
		
	}

	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	private String sql;

	public SMSPersonRoleSearchDAO() {
		super();
	}
}
