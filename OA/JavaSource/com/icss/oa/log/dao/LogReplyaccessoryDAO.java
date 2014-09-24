/*
 * Created on 2006-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.log.dao;

import java.sql.Connection;
import java.sql.Timestamp;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LogReplyaccessoryDAO extends DAO {

	Integer accessoryId;

	Integer replyId;

	String accessoryName;

	String accessoryOrder;

	String accessoryBlob;

	Timestamp accessoryTime;

	String accessoryUploadusr;

	public Integer getAccessoryId() {
		return accessoryId;
	}

	public void setAccessoryId(Integer _accessoryId) {
		firePropertyChange("accessoryId", accessoryId, _accessoryId);
		accessoryId = _accessoryId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer _replyId) {
		firePropertyChange("replyId", replyId, _replyId);
		replyId = _replyId;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String _accessoryName) {
		firePropertyChange("accessoryName", accessoryName, _accessoryName);
		accessoryName = _accessoryName;
	}

	public String getAccessoryOrder() {
		return accessoryOrder;
	}

	public void setAccessoryOrder(String _accessoryOrder) {
		firePropertyChange("accessoryOrder", accessoryOrder, _accessoryOrder);
		accessoryOrder = _accessoryOrder;
	}

	public String getAccessoryBlob() {
		return accessoryBlob;
	}

	public void setAccessoryBlob(String _accessoryBlob) {
		firePropertyChange("accessoryBlob", accessoryBlob, _accessoryBlob);
		accessoryBlob = _accessoryBlob;
	}

	public Timestamp getAccessoryTime() {
		return accessoryTime;
	}

	public void setAccessoryTime(Timestamp _accessoryTime) {
		firePropertyChange("accessoryTime", accessoryTime, _accessoryTime);
		accessoryTime = _accessoryTime;
	}

	public String getAccessoryUploadusr() {
		return accessoryUploadusr;
	}

	public void setAccessoryUploadusr(String _accessoryUploadusr) {
		firePropertyChange("accessoryUploadusr", accessoryUploadusr,
				_accessoryUploadusr);
		accessoryUploadusr = _accessoryUploadusr;
	}

	protected void setupFields() throws DAOException {
		addField("accessoryId", "ACCESSORY_ID");
		addField("replyId", "REPLY_ID");
		addField("accessoryName", "ACCESSORY_NAME");
		addField("accessoryOrder", "ACCESSORY_ORDER");
		addField("accessoryBlob", "ACCESSORY_BLOB");
		addField("accessoryTime", "ACCESSORY_TIME");
		addField("accessoryUploadusr", "ACCESSORY_UPLOADUSR");
		setTableName("LOG_REPLYACCESSORY");
		addKey("ACCESSORY_ID");
		setAutoIncremented("ACCESSORY_ID");
	}

	public LogReplyaccessoryDAO(Connection conn) {
		super(conn);
	}

	public LogReplyaccessoryDAO() {
		super();
	}
}
