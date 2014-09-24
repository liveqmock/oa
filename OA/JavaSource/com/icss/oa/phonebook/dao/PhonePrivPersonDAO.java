/*
 * Created on 2004-12-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PhonePrivPersonDAO extends DAO {

	Integer ppp_id;
	String pp_id;
	String personuuid;
	String userid;
	String personname;


	public Integer getPpp_id() {
		return ppp_id;
	}
	public void setPpp_id(Integer _ppp_id) {
		firePropertyChange("ppp_id", ppp_id, _ppp_id);
		ppp_id = _ppp_id;
	}
	
	
	public String getPp_id() {
		return pp_id;
	}
	public void setPp_id(String _pp_id) {
		firePropertyChange("pp_id", pp_id, _pp_id);
		pp_id = _pp_id;
	}

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
	
	
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		personname = _personname;
	}
	
	
	
	protected void setupFields() throws DAOException {
		addField("ppp_id", "PPP_ID");
		addField("pp_id", "PP_ID");
		addField("personuuid", "PERSONUUID");
		addField("userid", "USERID");
		addField("personname", "PERSONNAME");

		setTableName("PHONE_PRIVPERSON");          
		addKey("PPP_ID");
		this.setAutoIncremented("PPP_ID");
	}

	public PhonePrivPersonDAO(Connection conn) {
		super(conn);
	}

	public PhonePrivPersonDAO() {
		super();
	}
}



