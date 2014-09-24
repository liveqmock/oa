/*
 * Created on 2004-6-1
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SysPersonDAO extends DAO {

	private String personuuid;
	private String userid;
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	protected void setupFields() throws DAOException {
		addField("personuuid", "PERSONUUID");
		addField("userid", "USERID");
		setTableName("SYS_PERSON");
	}
	public SysPersonDAO(Connection conn) {
		super(conn);
	}
	public SysPersonDAO() {
		super();
	}
}
