/*
 * Created on 2004-5-20
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.partrecord.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DuanxinShortaccessSysOrgSearchDAO extends SearchDAO {
	Integer saId;
	String personid;
	String depid;
	String accessdepid;
	String orguuid;
	String cnname;
	public Integer getSaId() {
		return saId;
	}
	public void setSaId(Integer _saId) {
		firePropertyChange("saId", saId, _saId);
		saId = _saId;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		personid = _personid;
	}
	public String getDepid() {
		return depid;
	}
	public void setDepid(String _depid) {
		firePropertyChange("depid", depid, _depid);
		depid = _depid;
	}
	public String getAccessdepid() {
		return accessdepid;
	}
	public void setAccessdepid(String _accessdepid) {
		firePropertyChange("accessdepid", accessdepid, _accessdepid);
		accessdepid = _accessdepid;
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
		addField("saId", "DUANXIN_SHORTACCESS.SA_ID");
		addField("personid", "DUANXIN_SHORTACCESS.PERSONID");
		addField("depid", "DUANXIN_SHORTACCESS.DEPID");
		addField("accessdepid", "DUANXIN_SHORTACCESS.ACCESSDEPID");
		addField("orguuid", "SYS_ORG.ORGUUID");
		addField("cnname", "SYS_ORG.CNNAME");
	}
	protected String getSearchSQL() {
		return sql;
	}
	public DuanxinShortaccessSysOrgSearchDAO() {
		super();
	}
	public void setSearchSQL(String sql){
		this.sql=sql;
	}
	
	private String sql;
	
}
