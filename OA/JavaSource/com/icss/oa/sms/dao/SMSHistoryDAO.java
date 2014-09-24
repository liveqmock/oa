package com.icss.oa.sms.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;


public class SMSHistoryDAO extends DAO {
	
	private Integer id;
	private String senderuuid;
	private String sendername;
	private String time;
	private String content;
	private String receivername;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getSenderuuid() {
		return senderuuid;
	}
	public void setSenderuuid(String _senderuuid) {
		firePropertyChange("senderuuid", senderuuid, _senderuuid);
		senderuuid = _senderuuid;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String _sendername) {
		firePropertyChange("sendername", sendername, _sendername);
		sendername = _sendername;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String _time) {
		firePropertyChange("time", time, _time);
		time = _time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		firePropertyChange("content", content, _content);
		content = _content;
	}
	
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String _receivername) {
		firePropertyChange("receivername", receivername, _receivername);
		receivername = _receivername;
	}

	
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("senderuuid", "SENDERUUID");
		addField("sendername", "SENDERNAME");
		addField("time", "TIME");
		addField("content", "CONTENT");
		addField("receivername", "RECEIVERNAME");
		setTableName("TB_SMS_HISTORY");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public SMSHistoryDAO(Connection conn) {
		super(conn);
	}
	public SMSHistoryDAO() {
		super();
	}
}
