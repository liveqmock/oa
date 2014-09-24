/*
 * Created on 2006-4-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.log.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class LogSysDAO extends DAO {

	Integer sysId;

	Integer logSysId;

	String sysName;

	String sysCode;

	String sysOrder;

	String sysDetail;

	String sysMemo;

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer _sysId) {
		firePropertyChange("sysId", sysId, _sysId);
		sysId = _sysId;
	}

	public Integer getLogSysId() {
		return logSysId;
	}

	public void setLogSysId(Integer _logSysId) {
		firePropertyChange("logSysId", logSysId, _logSysId);
		logSysId = _logSysId;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String _sysName) {
		firePropertyChange("sysName", sysName, _sysName);
		sysName = _sysName;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String _sysCode) {
		firePropertyChange("sysCode", sysCode, _sysCode);
		sysCode = _sysCode;
	}

	public String getSysOrder() {
		return sysOrder;
	}

	public void setSysOrder(String _sysOrder) {
		firePropertyChange("sysOrder", sysOrder, _sysOrder);
		sysOrder = _sysOrder;
	}

	public String getSysDetail() {
		return sysDetail;
	}

	public void setSysDetail(String _sysDetail) {
		firePropertyChange("sysDetail", sysDetail, _sysDetail);
		sysDetail = _sysDetail;
	}

	public String getSysMemo() {
		return sysMemo;
	}

	public void setSysMemo(String _sysMemo) {
		firePropertyChange("sysMemo", sysMemo, _sysMemo);
		sysMemo = _sysMemo;
	}

	protected void setupFields() throws DAOException {
		addField("sysId", "SYS_ID");
		addField("logSysId", "LOG_SYS_ID");
		addField("sysName", "SYS_NAME");
		addField("sysCode", "SYS_CODE");
		addField("sysOrder", "SYS_ORDER");
		addField("sysDetail", "SYS_DETAIL");
		addField("sysMemo", "SYS_MEMO");
		setTableName("LOG_SYS");
		addKey("SYS_ID");
		setAutoIncremented("SYS_ID");
	}

	public LogSysDAO(Connection conn) {
		super(conn);
	}

	public LogSysDAO() {
		super();
	}

}
