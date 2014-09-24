/*
 * Created on 2005-1-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.intendwork.dao;    

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InfoStopDAO extends DAO {

	private Integer id1;

	private String personuuid;

	private String stopbuffer;

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer _id1) {
		firePropertyChange("id1", id1, _id1);
		id1 = _id1;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public String getStopbuffer() {
		return stopbuffer;
	}

	public void setStopbuffer(String _stopbuffer) {
		firePropertyChange("stopbuffer", stopbuffer, _stopbuffer);
		stopbuffer = _stopbuffer;
	}

	protected void setupFields() throws DAOException {
		addField("id1", "ID1");
		addField("personuuid", "PERSONUUID");
		addField("stopbuffer", "STOPBUFFER");
		setTableName("INFO_STOP");
		addKey("ID1");
		setAutoIncremented("ID1");
	}

	public InfoStopDAO(Connection conn) {
		super(conn);
	}

	public InfoStopDAO() {
		super();
	}
}
