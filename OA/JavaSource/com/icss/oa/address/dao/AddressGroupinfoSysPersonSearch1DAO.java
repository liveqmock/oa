/*
 * Created on 2004-12-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.address.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressGroupinfoSysPersonSearch1DAO extends SearchDAO {

	private String personuuid;

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

	public AddressGroupinfoSysPersonSearch1DAO() {
		super();
	}
}
