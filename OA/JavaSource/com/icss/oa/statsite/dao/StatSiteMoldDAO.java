/*
 * Created on 2004-6-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.statsite.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StatSiteMoldDAO extends DAO {
	Integer id;
	String visMold;
	Integer visNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getVisMold() {
		return visMold;
	}
	public void setVisMold(String _visMold) {
		firePropertyChange("visMold", visMold, _visMold);
		visMold = _visMold;
	}
	public Integer getVisNumber() {
		return visNumber;
	}
	public void setVisNumber(Integer _visNumber) {
		firePropertyChange("visNumber", visNumber, _visNumber);
		visNumber = _visNumber;
	}
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("visMold", "VIS_MOLD");
		addField("visNumber", "VIS_NUMBER");
		setTableName("STAT_SITE_MOLD");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public StatSiteMoldDAO(Connection conn) {
		super(conn);
	}
	public StatSiteMoldDAO() {
		super();
	}
}
