/*
 * Created on 2004-4-10
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
public class AddressGroupDAO extends DAO {

	private Integer id;
	private String groupuser;
	private String groupname;
	private String groupdes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getGroupuser() {
		return groupuser;
	}
	public void setGroupuser(String _groupuser) {
		firePropertyChange("grouptype", groupuser, _groupuser);
		groupuser = _groupuser;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String _groupname) {
		firePropertyChange("groupname", groupname, _groupname);
		groupname = _groupname;
	}
	public String getGroupdes() {
		return groupdes;
	}
	public void setGroupdes(String _groupdes) {
		firePropertyChange("groupdes", groupdes, _groupdes);
		groupdes = _groupdes;
	}
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("groupuser", "GROUPUSER");
		addField("groupname", "GROUPNAME");
		addField("groupdes", "GROUPDES");
		setTableName("ADDRESS_GROUP");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public AddressGroupDAO(Connection conn) {
		super(conn);
	}
	public AddressGroupDAO() {
		super();
	}
}
