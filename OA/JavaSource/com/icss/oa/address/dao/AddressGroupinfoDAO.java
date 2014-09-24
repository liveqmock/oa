/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressGroupinfoDAO extends DAO {

	private Integer groupid;
	private String userid;
	private String grouptype;
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer _groupid) {
		firePropertyChange("groupid", groupid, _groupid);
		groupid = _groupid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	protected void setupFields() throws DAOException {
		addField("groupid", "GROUPID");
		addField("userid", "USERID");
		addField("grouptype", "GROUPTYPE");
		setTableName("ADDRESS_GROUPINFO");
	}
	public AddressGroupinfoDAO(Connection conn) {
		super(conn);
	}
	public AddressGroupinfoDAO() {
		super();
	}
	/**
	 * @return
	 */
	public String getGrouptype() {
		return grouptype;
	}

	/**
	 * @param string
	 */
	public void setGrouptype(String _grouptype) {
		firePropertyChange("grouptype", grouptype, _grouptype);
		grouptype = _grouptype;
	}

}
