/*
 * Created on 2004-6-2
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.orgtree.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SysOrgDAO extends DAO {
	private String orguuid;
	private String cnname;
	private Integer orglevel;
	private String parentorguuid;
	private String deltag;
	private Integer serialindex;
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
	public Integer getOrglevel() {
		return orglevel;
	}
	public void setOrglevel(Integer _orglevel) {
		firePropertyChange("orglevel", orglevel, _orglevel);
		orglevel = _orglevel;
	}
	public String getParentorguuid() {
		return parentorguuid;
	}
	public void setParentorguuid(String _parentorguuid) {
		firePropertyChange("parentorguuid", parentorguuid, _parentorguuid);
		parentorguuid = _parentorguuid;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String _deltag) {
		firePropertyChange("deltag", deltag, _deltag);
		deltag = _deltag;
	}
	public Integer getSerialindex() {
		return serialindex;
	}
	public void setSerialindex(Integer _serialindex) {
		firePropertyChange("serialindex", serialindex, _serialindex);
		serialindex = _serialindex;
	}
	protected void setupFields() throws DAOException {
		addField("orguuid", "ORGUUID");
		addField("cnname", "CNNAME");
		addField("orglevel", "ORGLEVEL");
		addField("parentorguuid", "PARENTORGUUID");
		addField("deltag", "DELTAG");
		addField("serialindex", "SERIALINDEX");
		setTableName("SYS_ORG");
	}
	public SysOrgDAO(Connection conn) {
		super(conn);
	}
	public SysOrgDAO() {
		super();
	}
}
