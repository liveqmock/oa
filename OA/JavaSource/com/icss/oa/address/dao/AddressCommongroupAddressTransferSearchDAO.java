/*
 * Created on 2002-3-13
 *
 */
package com.icss.oa.address.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author �ﴫͤ
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressCommongroupAddressTransferSearchDAO extends SearchDAO {

	private Integer id;

	private String groupname;

	private String groupdes;

	private String needaccredit;

	private String owner;

	private Integer levels;

	private Integer rootid;

	private Integer parentid;

	private String personuuid;

	private Integer commongroup;

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

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public Integer getCommongroup() {
		return commongroup;
	}

	public void setCommongroup(Integer _commongroup) {
		firePropertyChange("commongroup", commongroup, _commongroup);
		commongroup = _commongroup;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ADDRESS_COMMONGROUP.ID");
		addField("groupname", "ADDRESS_COMMONGROUP.GROUPNAME");
		addField("groupdes", "ADDRESS_COMMONGROUP.GROUPDES");
		addField("needaccredit", "ADDRESS_COMMONGROUP.NEEDACCREDIT");
		addField("owner", "ADDRESS_COMMONGROUP.OWNER");
		addField("levels", "ADDRESS_COMMONGROUP.LEVELS");
		addField("rootid", "ADDRESS_COMMONGROUP.ROOTID");
		addField("parentid", "ADDRESS_COMMONGROUP.PARENTID");
		addField("personuuid", "ADDRESS_TRANSFER.PERSONUUID");
		addField("commongroup", "ADDRESS_TRANSFER.COMMONGROUP");
	}

	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	private String sql;

	public AddressCommongroupAddressTransferSearchDAO() {
		super();
	}
}
