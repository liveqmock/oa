/*
 * Created on 2006-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.log.vo;

import java.sql.Timestamp;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LogReplyaccessoryVO extends ValueObject {

	Integer accessoryId;

	Integer replyId;

	String accessoryName;

	String accessoryOrder;

	String accessoryBlob;

	Timestamp accessoryTime;

	String accessoryUploadusr;

	public void setAccessoryId(Integer _accessoryId) {
		accessoryId = _accessoryId;
	}

	public Integer getAccessoryId() {
		return accessoryId;
	}

	public void setReplyId(Integer _replyId) {
		replyId = _replyId;
	}

	public Integer getReplyId() {
		return replyId;
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

	public void setAccessoryBlob(String _accessoryBlob) {
		accessoryBlob = _accessoryBlob;
	}

	public String getAccessoryBlob() {
		return accessoryBlob;
	}

	public void setAccessoryTime(Timestamp _accessoryTime) {
		accessoryTime = _accessoryTime;
	}

	public Timestamp getAccessoryTime() {
		return accessoryTime;
	}

	public void setAccessoryUploadusr(String _accessoryUploadusr) {
		accessoryUploadusr = _accessoryUploadusr;
	}

	public String getAccessoryUploadusr() {
		return accessoryUploadusr;
	}
}
