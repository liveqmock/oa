/*
 * Created on 2004-6-2
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SysOrgSearchDAO extends SearchDAO {
    String orguuid;
    String cnname;
	Integer orglevel;
	String parentorguuid;
	String deltag;
    Integer serialindex;
	String sql;
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
		addField("orguuid", "SYS_ORG.ORGUUID");
		addField("cnname", "SYS_ORG.CNNAME");
		addField("orglevel", "SYS_ORG.ORGLEVEL");
		addField("parentorguuid", "SYS_ORG.PARENTORGUUID");
		addField("deltag", "SYS_ORG.DELTAG");
		addField("serialindex", "SYS_ORG.SERIALINDEX");
		//setTableName("SYS_ORG");
	}
    
	public String getSearchSQL() {
		return this.sql;
	}
	
	public void setSearchSQL(String _sql){
		this.sql = _sql;
	}
	

	public SysOrgSearchDAO() {
		super();
	}
}



