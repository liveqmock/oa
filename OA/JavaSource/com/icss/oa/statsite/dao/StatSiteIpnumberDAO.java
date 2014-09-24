/*
 * Created on 2004-7-2
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
public class StatSiteIpnumberDAO extends DAO {

	Integer id;

	String address;

	Long counter;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String _address) {
		firePropertyChange("address", address, _address);
		address = _address;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long _counter) {
		firePropertyChange("counter", counter, _counter);
		counter = _counter;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("address", "ADDRESS");
		addField("counter", "COUNTER");
		setTableName("STAT_SITE_IPNUMBER");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public StatSiteIpnumberDAO(Connection conn) {
		super(conn);
	}

	public StatSiteIpnumberDAO() {
		super();
	}
}
