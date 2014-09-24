/*
 * Created on 2006-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.log.dao;

import java.io.InputStream;
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
public class LogAccessoryDAO extends DAO {

	Integer logId;

	Integer accessoryId;

	String accessoryName;

	String accessoryOrder;

	InputStream accessoryBlob;

	Long accessoryTime;

	String accessoryUploadusr;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer _logId) {
		firePropertyChange("logId", logId, _logId);
		logId = _logId;
	}

	public Integer getAccessoryId() {
		return accessoryId;
	}

	public void setAccessoryId(Integer _accessoryId) {
		firePropertyChange("accessoryId", accessoryId, _accessoryId);
		accessoryId = _accessoryId;
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

	public InputStream getAccessoryBlob() {
		return accessoryBlob;
	}

	public void setAccessoryBlob(InputStream _accessoryBlob) {
		firePropertyChange("accessoryBlob", accessoryBlob, _accessoryBlob);
		accessoryBlob = _accessoryBlob;
	}

	public Long getAccessoryTime() {
		return accessoryTime;
	}

	public void setAccessoryTime(Long _accessoryTime) {
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
		addField("logId", "LOG_ID");
		addField("accessoryId", "ACCESSORY_ID");
		addField("accessoryName", "ACCESSORY_NAME");
		addField("accessoryOrder", "ACCESSORY_ORDER");
		addField("accessoryBlob", "ACCESSORY_BLOB");
		addField("accessoryTime", "ACCESSORY_TIME");
		addField("accessoryUploadusr", "ACCESSORY_UPLOADUSR");
		setTableName("LOG_ACCESSORY");
		addKey("ACCESSORY_ID");
		setAutoIncremented("ACCESSORY_ID");
	}

	public LogAccessoryDAO(Connection conn) {
		super(conn);
	}

	public LogAccessoryDAO() {
		super();
	}
}
