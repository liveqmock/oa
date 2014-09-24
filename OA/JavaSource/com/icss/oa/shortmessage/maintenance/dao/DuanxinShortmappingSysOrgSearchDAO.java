/*
 * Created on 2004-5-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.maintenance.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DuanxinShortmappingSysOrgSearchDAO extends SearchDAO {
	Integer smId;
	String smCode;
	String depid;
	String orguuid;
	String cnname;
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
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	protected void setupFields() throws DAOException {
		addField("smId", "DUANXIN_SHORTMAPPING.SM_ID");
		addField("smCode", "DUANXIN_SHORTMAPPING.SM_CODE");
		addField("depid", "DUANXIN_SHORTMAPPING.DEPID");
		addField("orguuid", "SYS_ORG.ORGUUID");
		addField("cnname", "SYS_ORG.CNNAME");
	}
	protected String getSearchSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("DUANXIN_SHORTMAPPING.SM_ID,DUANXIN_SHORTMAPPING.SM_CODE,DUANXIN_SHORTMAPPING.DEPID,SYS_ORG.ORGUUID,SYS_ORG.CNNAME ");
		sql.append("FROM ");
		sql.append("DUANXIN_SHORTMAPPING,SYS_ORG ");
		sql.append("WHERE ");
		sql.append("DUANXIN_SHORTMAPPING.DEPID = SYS_ORG.ORGUUID ");
		return sql.toString();
	}
	public DuanxinShortmappingSysOrgSearchDAO() {
		super();
	}
}
