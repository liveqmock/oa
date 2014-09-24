/*
 * Created on 2006-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.log.vo;

import java.io.InputStream;
import java.sql.Timestamp;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LogAccessoryVO extends ValueObject {

	Integer logId;

	Integer accessoryId;

	String accessoryName;

	String accessoryOrder;

	InputStream accessoryBlob;

	Long accessoryTime;

	String accessoryUploadusr;

	public void setLogId(Integer _logId) {
		logId = _logId;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setAccessoryId(Integer _accessoryId) {
		accessoryId = _accessoryId;
	}

	public Integer getAccessoryId() {
		return accessoryId;
	}

	public void setAccessoryName(String _accessoryName) {
		accessoryName = _accessoryName;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryOrder(String _accessoryOrder) {
		accessoryOrder = _accessoryOrder;
	}

	public String getAccessoryOrder() {
		return accessoryOrder;
	}

	public void setAccessoryBlob(InputStream _accessoryBlob) {
		accessoryBlob = _accessoryBlob;
	}

	public InputStream getAccessoryBlob() {
		return accessoryBlob;
	}

	public void setAccessoryTime(Long _accessoryTime) {
		accessoryTime = _accessoryTime;
	}

	public Long getAccessoryTime() {
		return accessoryTime;
	}

	public void setAccessoryUploadusr(String _accessoryUploadusr) {
		accessoryUploadusr = _accessoryUploadusr;
	}

	public String getAccessoryUploadusr() {
		return accessoryUploadusr;
	}
}
