/*
 * Created on 2002-3-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressCommongroupAddressTransferVO extends ValueObject {

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

	public void setId(Integer _id) {
		id = _id;
	}

	public Integer getId() {
		return id;
	}

	public void setGroupname(String _groupname) {
		groupname = _groupname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupdes(String _groupdes) {
		groupdes = _groupdes;
	}

	public String getGroupdes() {
		return groupdes;
	}

	public void setNeedaccredit(String _needaccredit) {
		needaccredit = _needaccredit;
	}

	public String getNeedaccredit() {
		return needaccredit;
	}

	public void setOwner(String _owner) {
		owner = _owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setLevels(Integer _levels) {
		levels = _levels;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setRootid(Integer _rootid) {
		rootid = _rootid;
	}

	public Integer getRootid() {
		return rootid;
	}

	public void setParentid(Integer _parentid) {
		parentid = _parentid;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setCommongroup(Integer _commongroup) {
		commongroup = _commongroup;
	}

	public Integer getCommongroup() {
		return commongroup;
	}
}
