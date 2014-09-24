package com.icss.oa.sms.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;


public class SMSReceiveDAO extends DAO {
	
	private Integer id;
	private String fromNo;
	private String toNo;
	private String receiver;
	private String cotent;
	private String time;

	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getFromNo() {
		return fromNo;
	}
	public void setFromNo(String _fromNo) {
		firePropertyChange("fromNo", fromNo, _fromNo);
		fromNo = _fromNo;
	}
	
	public String getToNo() {
		return toNo;
	}
	public void setToNo(String _toNo) {
		firePropertyChange("toNo", toNo, _toNo);
		toNo = _toNo;
	}
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String _receiver) {
		firePropertyChange("receiver", receiver, _receiver);
		receiver = _receiver;
	}
	
	public String getCotent() {
		return cotent;
	}
	public void setCotent(String _cotent) {
		firePropertyChange("cotent", cotent, _cotent);
		cotent = _cotent;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String _time) {
		firePropertyChange("time", time, _time);
		time = _time;
	}
	
	
	
	protected void setupFields() throws DAOException {
		
		addField("id", "ID");
		addField("fromNo", "FROMNO");
		addField("toNo", "TONO");
		addField("receiver", "RECEIVER");
		addField("cotent", "CONTENT");
		addField("time", "TIME");
		setTableName("TB_SMS_RECEIVE");
		addKey("ID");
		setAutoIncremented("ID");
	
	}
	
	public SMSReceiveDAO(Connection conn) {
		super(conn);
	}
	public SMSReceiveDAO() {
		super();
	}

}