/*
 * Created on 2006-4-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.log.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LogSysVO extends ValueObject {

	Integer sysId;

	Integer logSysId;

	String sysName;

	String sysCode;

	String sysOrder;

	String sysDetail;

	String sysMemo;

	public void setSysId(Integer _sysId) {
		sysId = _sysId;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setLogSysId(Integer _logSysId) {
		logSysId = _logSysId;
	}

	public Integer getLogSysId() {
		return logSysId;
	}

	public void setSysName(String _sysName) {
		sysName = _sysName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysCode(String _sysCode) {
		sysCode = _sysCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysOrder(String _sysOrder) {
		sysOrder = _sysOrder;
	}

	public String getSysOrder() {
		return sysOrder;
	}

	public void setSysDetail(String _sysDetail) {
		sysDetail = _sysDetail;
	}

	public String getSysDetail() {
		return sysDetail;
	}

	public void setSysMemo(String _sysMemo) {
		sysMemo = _sysMemo;
	}

	public String getSysMemo() {
		return sysMemo;
	}
}
