/*
 * Created on 2004-5-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DuanxinShortaccessDAO extends DAO {
	Integer saId;
	String personid;
	String depid;
	String accessdepid;
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
	protected void setupFields() throws DAOException {
		addField("saId", "SA_ID");
		addField("personid", "PERSONID");
		addField("depid", "DEPID");
		addField("accessdepid", "ACCESSDEPID");
		setTableName("DUANXIN_SHORTACCESS");
		addKey("SA_ID");
		this.setAutoIncremented("SA_ID");
	}
	public DuanxinShortaccessDAO(Connection conn) {
		super(conn);
	}
	public DuanxinShortaccessDAO() {
		super();
	}
}
