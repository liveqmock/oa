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
public class LogMsgVO extends ValueObject {

	Integer logId;

	Integer sysId;

	Long logTime;

	String logReason;

	String logPheno;

	String logAnalyse;

	String logDesc;

	String logPerson;

	public void setLogId(Integer _logId) {
		logId = _logId;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setSysId(Integer _sysId) {
		sysId = _sysId;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setLogTime(Long _logTime) {
		logTime = _logTime;
	}

	public Long getLogTime() {
		return logTime;
	}

	public void setLogReason(String _logReason) {
		logReason = _logReason;
	}

	public String getLogReason() {
		return logReason;
	}

	public void setLogPheno(String _logPheno) {
		logPheno = _logPheno;
	}

	public String getLogPheno() {
		return logPheno;
	}

	public void setLogAnalyse(String _logAnalyse) {
		logAnalyse = _logAnalyse;
	}

	public String getLogAnalyse() {
		return logAnalyse;
	}

	public void setLogDesc(String _logDesc) {
		logDesc = _logDesc;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogPerson(String _logPerson) {
		logPerson = _logPerson;
	}

	public String getLogPerson() {
		return logPerson;
	}
}
