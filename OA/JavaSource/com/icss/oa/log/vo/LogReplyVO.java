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
public class LogReplyVO extends ValueObject {

	Integer replyId;

	Integer logId;

	String replyTime;

	String replyContent;

	String replyDesc;

	String replyPerson;

	String replyTitle;

	public void setReplyId(Integer _replyId) {
		replyId = _replyId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setLogId(Integer _logId) {
		logId = _logId;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setReplyTime(String _replyTime) {
		replyTime = _replyTime;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyContent(String _replyContent) {
		replyContent = _replyContent;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyDesc(String _replyDesc) {
		replyDesc = _replyDesc;
	}

	public String getReplyDesc() {
		return replyDesc;
	}

	public void setReplyPerson(String _replyPerson) {
		replyPerson = _replyPerson;
	}

	public String getReplyPerson() {
		return replyPerson;
	}

	public void setReplyTitle(String _replyTitle) {
		replyTitle = _replyTitle;
	}

	public String getReplyTitle() {
		return replyTitle;
	}
}
