package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

public class CommongroupRightVO extends ValueObject {
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
		groupname = _groupname;
	}
	public String getGroupdes() {
		return groupdes;
	}
	public void setGroupdes(String _groupdes) {
		groupdes = _groupdes;
	}
	public String getNeedaccredit() {
		return needaccredit;
	}
	public void setNeedaccredit(String _needaccredit) {
		needaccredit = _needaccredit;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String _owner) {
		owner = _owner;
	}
	public Integer getLevels() {
		return levels;
	}
	public void setLevels(Integer _levels) {
		levels = _levels;
	}
	public Integer getRootid() {
		return rootid;
	}
	public void setRootid(Integer _rootid) {
		rootid = _rootid;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer _parentid) {
		parentid = _parentid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer _groupid) {
		groupid = _groupid;
	}
}