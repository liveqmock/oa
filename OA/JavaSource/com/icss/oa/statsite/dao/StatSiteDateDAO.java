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
public class StatSiteDateDAO extends DAO {
	Integer id;
	Integer visNumber;
	Long visDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public Integer getVisNumber() {
		return visNumber;
	}
	public void setVisNumber(Integer _visNumber) {
		firePropertyChange("visNumber", visNumber, _visNumber);
		visNumber = _visNumber;
	}
	public Long getVisDate() {
		return visDate;
	}
	public void setVisDate(Long _visDate) {
		firePropertyChange("visDate", visDate, _visDate);
		visDate = _visDate;
	}
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("visNumber", "VIS_NUMBER");
		addField("visDate", "VIS_DATE");
		setTableName("STAT_SITE_DATE");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public StatSiteDateDAO(Connection conn) {
		super(conn);
	}
	public StatSiteDateDAO() {
		super();
	}
}
