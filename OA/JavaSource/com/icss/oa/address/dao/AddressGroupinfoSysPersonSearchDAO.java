/*
 * Created on 2004-12-16
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
public class AddressGroupinfoSysPersonSearchDAO extends SearchDAO {
   
	private String sql;
	
	private String userid;

	private Integer groupid;

	private String grouptype;

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
		addField("userid", "ADDRESS_GROUPINFO.USERID");
		addField("groupid", "ADDRESS_GROUPINFO.GROUPID");
		addField("grouptype", "ADDRESS_GROUPINFO.GROUPTYPE");
	}

	protected String getSearchSQL()
	{
		return sql;
	}
	public void setSearchSQL(String sql)
	{
		this.sql = sql;
	}

	public AddressGroupinfoSysPersonSearchDAO() {
		super();
	}
}
