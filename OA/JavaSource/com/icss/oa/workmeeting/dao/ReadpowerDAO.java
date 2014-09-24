/*
 * Created on 2004-12-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.workmeeting.dao;  

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReadpowerDAO extends DAO {

	private String userid;
	private String cnname;
	private String personuuid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}
	protected void setupFields() throws DAOException {
		addField("userid", "USERID");
		addField("cnname", "CNNAME");
		addField("personuuid", "PERSONUUID");
		setTableName("READPOWER");
	}
	public ReadpowerDAO(Connection conn) {
		super(conn);
	}
	public ReadpowerDAO() {
		super();
	}
}
