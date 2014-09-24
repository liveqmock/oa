/*
 * Created on 2002-3-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.address.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressTransferDAO extends DAO {

	private Integer id;

	private String personuuid;

	private Integer commongroup;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public Integer getCommongroup() {
		return commongroup;
	}

	public void setCommongroup(Integer _commongroup) {
		firePropertyChange("commongroup", commongroup, _commongroup);
		commongroup = _commongroup;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("personuuid", "PERSONUUID");
		addField("commongroup", "COMMONGROUP");
		setTableName("ADDRESS_TRANSFER");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public AddressTransferDAO(Connection conn) {
		super(conn);
	}

	public AddressTransferDAO() {
		super();
	}
}
