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
public class LogMsgDAO extends DAO {

	Integer logId;

	Integer sysId;

	Long logTime;

	String logReason;

	String logPheno;

	String logAnalyse;

	String logDesc;

	String logPerson;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer _logId) {
		firePropertyChange("logId", logId, _logId);
		logId = _logId;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer _sysId) {
		firePropertyChange("sysId", sysId, _sysId);
		sysId = _sysId;
	}

	public Long getLogTime() {
		return logTime;
	}

	public void setLogTime(Long _logTime) {
		firePropertyChange("logTime", logTime, _logTime);
		logTime = _logTime;
	}

	public String getLogReason() {
		return logReason;
	}

	public void setLogReason(String _logReason) {
		firePropertyChange("logReason", logReason, _logReason);
		logReason = _logReason;
	}

	public String getLogPheno() {
		return logPheno;
	}

	public void setLogPheno(String _logPheno) {
		firePropertyChange("logPheno", logPheno, _logPheno);
		logPheno = _logPheno;
	}

	public String getLogAnalyse() {
		return logAnalyse;
	}

	public void setLogAnalyse(String _logAnalyse) {
		firePropertyChange("logAnalyse", logAnalyse, _logAnalyse);
		logAnalyse = _logAnalyse;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String _logDesc) {
		firePropertyChange("logDesc", logDesc, _logDesc);
		logDesc = _logDesc;
	}

	public String getLogPerson() {
		return logPerson;
	}

	public void setLogPerson(String _logPerson) {
		firePropertyChange("logPerson", logPerson, _logPerson);
		logPerson = _logPerson;
	}

	protected void setupFields() throws DAOException {
		addField("logId", "LOG_ID");
		addField("sysId", "sys_id");
		addField("logTime", "LOG_TIME");
		addField("logReason", "LOG_REASON");
		addField("logPheno", "LOG_PHENO");
		addField("logAnalyse", "LOG_ANALYSE");
		addField("logDesc", "LOG_DESC");
		addField("logPerson", "LOG_PERSON");
		setTableName("LOG_MSG");
		addKey("LOG_ID");
		setAutoIncremented("LOG_ID");
	}

	public LogMsgDAO(Connection conn) {
		super(conn);
	}

	public LogMsgDAO() {
		super();
	}
}
