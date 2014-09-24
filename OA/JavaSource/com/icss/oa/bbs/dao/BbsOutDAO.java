package com.icss.oa.bbs.dao;

import java.sql.Connection;
import java.sql.Timestamp;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class BbsOutDAO extends DAO {

	private String cnname;
	private String personuuid;
	private Timestamp time;


	public BbsOutDAO(Connection conn){
		super(conn);
	}

	public BbsOutDAO(){
		super();
	}

	public void setCnname(java.lang.String _cnname){ 
		firePropertyChange("cnname",cnname,_cnname);
		cnname = _cnname;
	}

	public java.lang.String getCnname(){
		return cnname;
	}


	public void setPersonuuid(java.lang.String _personuuid){ 
		firePropertyChange("personuuid",personuuid,_personuuid);
		personuuid = _personuuid;
	}

	public java.lang.String getPersonuuid(){
		return personuuid;
	}

	public void setTime(java.sql.Timestamp _time) {
		firePropertyChange("time", time, _time);
		time = _time;
	}

	public java.sql.Timestamp getTime() {
		return time;
	}

	protected void setupFields() throws DAOException {
		addField("cnname","CNNAME");
		addField("personuuid","PERSONUUID");
		addField("time","TIME");
		setTableName("BBS_OUT");
	}

}