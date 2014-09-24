package com.icss.oa.tq.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

public class TqSyspersonSearchDAO extends SearchDAO {

	private String personuuid;

	private String cnname;
	
	private Integer tqid;
	
	private String userid;

	private Integer groupid;

	private String grouptype;

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	
	public Integer getTqid() {
		return tqid;
	}

	public void setTqid(Integer _tqid) {
		firePropertyChange("tqid", tqid, _tqid);
		tqid = _tqid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer _groupid) {
		firePropertyChange("groupid", groupid, _groupid);
		groupid = _groupid;
	}

	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String _grouptype) {
		firePropertyChange("grouptype", grouptype, _grouptype);
		grouptype = _grouptype;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "SYS_PERSON.PERSONUUID");
		addField("cnname", "SYS_PERSON.CNNAME");
		addField("tqid", "SYS_PERSON.TQID");
		addField("userid", "ADDRESS_GROUPINFO.USERID");
		addField("groupid", "ADDRESS_GROUPINFO.GROUPID");
		addField("grouptype", "ADDRESS_GROUPINFO.GROUPTYPE");
	}

	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	private String sql;

	public TqSyspersonSearchDAO() {
		super();
	}
}
