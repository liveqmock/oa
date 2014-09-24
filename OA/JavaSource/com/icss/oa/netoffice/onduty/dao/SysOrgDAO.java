/*
 * Created on 2004-7-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SysOrgDAO extends DAO {

	private String orguuid;
	private String cnname;
	private String enname;
	private String orgcode;
	private String orggrade;
	private String parentorguuid;
	private Integer status;
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
	public String getEnname() {
		return enname;
	}
	public void setEnname(String _enname) {
		firePropertyChange("enname", enname, _enname);
		enname = _enname;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String _orgcode) {
		firePropertyChange("orgcode", orgcode, _orgcode);
		orgcode = _orgcode;
	}
	public String getOrggrade() {
		return orggrade;
	}
	public void setOrggrade(String _orggrade) {
		firePropertyChange("orggrade", orggrade, _orggrade);
		orggrade = _orggrade;
	}
	public String getParentorguuid() {
		return parentorguuid;
	}
	public void setParentorguuid(String _parentorguuid) {
		firePropertyChange("parentorguuid", parentorguuid, _parentorguuid);
		parentorguuid = _parentorguuid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer _status) {
		firePropertyChange("status", status, _status);
		status = _status;
	}
	protected void setupFields() throws DAOException {
		addField("orguuid", "ORGUUID");
		addField("cnname", "CNNAME");
		addField("enname", "ENNAME");
		addField("orgcode", "ORGCODE");
		addField("orggrade", "ORGGRADE");
		addField("parentorguuid", "PARENTORGUUID");
		addField("status", "STATUS");
		setTableName("SYS_ORG");
	}
	public SysOrgDAO(Connection conn) {
		super(conn);
	}
	public SysOrgDAO() {
		super();
	}
}
