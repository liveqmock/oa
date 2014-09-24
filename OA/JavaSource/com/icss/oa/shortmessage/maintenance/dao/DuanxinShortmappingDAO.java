/*
 * Created on 2004-5-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.maintenance.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DuanxinShortmappingDAO extends DAO {
	Integer smId;
	String smCode;
	String depid;
	public Integer getSmId() {
		return smId;
	}
	public void setSmId(Integer _smId) {
		firePropertyChange("smId", smId, _smId);
		smId = _smId;
	}
	public String getSmCode() {
		return smCode;
	}
	public void setSmCode(String _smCode) {
		firePropertyChange("smCode", smCode, _smCode);
		smCode = _smCode;
	}
	public String getDepid() {
		return depid;
	}
	public void setDepid(String _depid) {
		firePropertyChange("depid", depid, _depid);
		depid = _depid;
	}
	protected void setupFields() throws DAOException {
		addField("smId", "SM_ID");
		addField("smCode", "SM_CODE");
		addField("depid", "DEPID");
		setTableName("DUANXIN_SHORTMAPPING");
		addKey("SM_ID");
		setAutoIncremented("SM_ID");
	}
	public DuanxinShortmappingDAO(Connection conn) {
		super(conn);
	}
	public DuanxinShortmappingDAO() {
		super();
	}
}
