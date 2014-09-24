package com.icss.oa.sms.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;


public class SMSRoleDAO extends DAO {
	
	private Integer id;
	private String rolename;
	private Integer isout;
	private Integer ishistory;
	private Integer sendnumber;
	private String roledes;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String _rolename) {
		firePropertyChange("rolename", rolename, _rolename);
		rolename = _rolename;
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
	
	public String getRoledes() {
		return roledes;
	}
	public void setRoledes(String _roledes) {
		firePropertyChange("roledes", roledes, _roledes);
		roledes = _roledes;
	}

	
	protected void setupFields() throws DAOException {
		addField("id", "SMSR_ID");
		addField("rolename", "ROLENAME");
		addField("isout", "ISOUT");
		addField("ishistory", "ISHISTORY");
		addField("sendnumber", "SENDNUMBER");
		addField("roledes", "ROLEDES");
		setTableName("TB_SMS_ROLE");
		addKey("SMSR_ID");
		setAutoIncremented("SMSR_ID");
	}
	public SMSRoleDAO(Connection conn) {
		super(conn);
	}
	public SMSRoleDAO() {
		super();
	}
}
