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
public class LogReplyDAO extends DAO {

	Integer replyId;

	Integer logId;

	String replyTime;

	String replyContent;

	String replyDesc;

	String replyPerson;

	String replyTitle;

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer _replyId) {
		firePropertyChange("replyId", replyId, _replyId);
		replyId = _replyId;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer _logId) {
		firePropertyChange("logId", logId, _logId);
		logId = _logId;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String _replyTime) {
		firePropertyChange("replyTime", replyTime, _replyTime);
		replyTime = _replyTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String _replyContent) {
		firePropertyChange("replyContent", replyContent, _replyContent);
		replyContent = _replyContent;
	}

	public String getReplyDesc() {
		return replyDesc;
	}

	public void setReplyDesc(String _replyDesc) {
		firePropertyChange("replyDesc", replyDesc, _replyDesc);
		replyDesc = _replyDesc;
	}

	public String getReplyPerson() {
		return replyPerson;
	}

	public void setReplyPerson(String _replyPerson) {
		firePropertyChange("replyPerson", replyPerson, _replyPerson);
		replyPerson = _replyPerson;
	}

	public String getReplyTitle() {
		return replyTitle;
	}

	public void setReplyTitle(String _replyTitle) {
		firePropertyChange("replyTitle", replyTitle, _replyTitle);
		replyTitle = _replyTitle;
	}

	protected void setupFields() throws DAOException {
		addField("replyId", "REPLY_ID");
		addField("logId", "LOG_ID");
		addField("replyTime", "REPLY_TIME");
		addField("replyContent", "REPLY_CONTENT");
		addField("replyDesc", "REPLY_DESC");
		addField("replyPerson", "REPLY_PERSON");
		addField("replyTitle", "REPLY_TITLE");
		setTableName("LOG_REPLY");
		addKey("REPLY_ID");
		setAutoIncremented("REPLY_ID");
	}

	public LogReplyDAO(Connection conn) {
		super(conn);
	}

	public LogReplyDAO() {
		super();
	}
}
