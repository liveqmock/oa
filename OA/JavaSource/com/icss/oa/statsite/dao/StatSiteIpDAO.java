/*
 * Created on 2004-7-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.statsite.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatSiteIpDAO extends DAO {

	Integer id;

	String ipadress;

	Long number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public String getIpadress() {
		return ipadress;
	}

	public void setIpadress(String _ipadress) {
		firePropertyChange("ipadress", ipadress, _ipadress);
		ipadress = _ipadress;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long _number) {
		firePropertyChange("number", number, _number);
		number = _number;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("ipadress", "IPADRESS");
		addField("number", "NUMBER");
		setTableName("STAT_SITE_IP");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public StatSiteIpDAO(Connection conn) {
		super(conn);
	}

	public StatSiteIpDAO() {
		super();
	}
}
