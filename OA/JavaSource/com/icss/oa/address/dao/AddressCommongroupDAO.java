/*
 * Created on 2004-7-30
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.address.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressCommongroupDAO extends DAO {

	private Integer id;

	private String groupname;

	private String groupdes;

	private String needaccredit;

	private String owner;

	private Integer levels;

	private Integer rootid;

	private Integer parentid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
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

	public String getNeedaccredit() {
		return needaccredit;
	}

	public void setNeedaccredit(String _needaccredit) {
		firePropertyChange("needaccredit", needaccredit, _needaccredit);
		needaccredit = _needaccredit;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String _owner) {
		firePropertyChange("owner", owner, _owner);
		owner = _owner;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer _levels) {
		firePropertyChange("levels", levels, _levels);
		levels = _levels;
	}

	public Integer getRootid() {
		return rootid;
	}

	public void setRootid(Integer _rootid) {
		firePropertyChange("rootid", rootid, _rootid);
		rootid = _rootid;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer _parentid) {
		firePropertyChange("parentid", parentid, _parentid);
		parentid = _parentid;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("groupname", "GROUPNAME");
		addField("groupdes", "GROUPDES");
		addField("needaccredit", "NEEDACCREDIT");
		addField("owner", "OWNER");
		addField("levels", "LEVELS");
		addField("rootid", "ROOTID");
		addField("parentid", "PARENTID");
		setTableName("ADDRESS_COMMONGROUP");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public AddressCommongroupDAO(Connection conn) {
		super(conn);
	}

	public AddressCommongroupDAO() {
		super();
	}
}
