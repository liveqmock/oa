/*
 * Created on 2004-7-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommongroupRightSearchDAO extends SearchDAO {

	private String sql;
	String groupname;
	String groupdes;
	String needaccredit;
	String owner;
	Integer levels;
	Integer rootid;
	Integer parentid;
	String userid;
	Integer groupid;
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
	protected void setupFields() throws DAOException {
		addField("groupname", "ADDRESS_COMMONGROUP.GROUPNAME");
		addField("groupdes", "ADDRESS_COMMONGROUP.GROUPDES");
		addField("needaccredit", "ADDRESS_COMMONGROUP.NEEDACCREDIT");
		addField("owner", "ADDRESS_COMMONGROUP.OWNER");
		addField("levels", "ADDRESS_COMMONGROUP.LEVELS");
		addField("rootid", "ADDRESS_COMMONGROUP.ROOTID");
		addField("parentid", "ADDRESS_COMMONGROUP.PARENTID");
		addField("userid", "ADDRESS_GROUPRIGHT.USERID");
		addField("groupid", "ADDRESS_COMMONGROUP.ID");
	}
	protected String getSearchSQL()
	{
		return sql;
	}
	public CommongroupRightSearchDAO() {
		super();
	}
	public void setSearchSQL(String sql)
	{
		this.sql = sql;
	}
}
