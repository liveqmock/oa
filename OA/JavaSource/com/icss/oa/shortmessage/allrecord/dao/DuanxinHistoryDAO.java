/*
 * Created on 2004-5-19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.allrecord.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DuanxinHistoryDAO extends DAO {
	Integer shId;
	String senderId;
	String shContent;
	Long shDate;
	String depId;
	String receiverId;
	String senderCnname;
	public Integer getShId() {
		return shId;
	}
	public void setShId(Integer _shId) {
		firePropertyChange("shId", shId, _shId);
		shId = _shId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String _senderId) {
		firePropertyChange("senderId", senderId, _senderId);
		senderId = _senderId;
	}
	public String getShContent() {
		return shContent;
	}
	public void setShContent(String _shContent) {
		firePropertyChange("shContent", shContent, _shContent);
		shContent = _shContent;
	}
	public Long getShDate() {
		return shDate;
	}
	public void setShDate(Long _shDate) {
		firePropertyChange("shDate", shDate, _shDate);
		shDate = _shDate;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String _depId) {
		firePropertyChange("depId", depId, _depId);
		depId = _depId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String _receiverId) {
		firePropertyChange("receiverId", receiverId, _receiverId);
		receiverId = _receiverId;
	}
	public String getSenderCnname() {
		return senderCnname;
	}
	public void setSenderCnname(String _senderCnname) {
		firePropertyChange("senderCnname", senderCnname, _senderCnname);
		senderCnname = _senderCnname;
	}
	protected void setupFields() throws DAOException {
		addField("shId", "SH_ID");
		addField("senderId", "SENDER_ID");
		addField("shContent", "SH_CONTENT");
		addField("shDate", "SH_DATE");
		addField("depId", "DEP_ID");
		addField("receiverId", "RECEIVER_ID");
		addField("senderCnname", "SENDER_CNNAME");
		setTableName("DUANXIN_HISTORY");
		addKey("SH_ID");
		this.setAutoIncremented("SH_ID");
	}
	public DuanxinHistoryDAO(Connection conn) {
		super(conn);
	}
	public DuanxinHistoryDAO() {
		super();
	}
}
