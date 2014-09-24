package com.icss.oa.hr.vo;

import com.icss.j2ee.vo.ValueObject;

public class HRGroupVO extends ValueObject {

	private String groupid;
	private String orgname;
	private String description;
	private String parentid;
	private String grouplevel;


	public void setGroupid(String _groupid) {
		groupid = _groupid;
	}

	public String getGroupid() {
		return groupid;
	}
	
	public void setOrgname(String _orgname) {
		orgname = _orgname;
	}

	public String getOrgname() {
		return orgname;
	}
	public void setDescription(String _description) {
		description = _description;
	}

	public String getDescription() {
		return description;
	}

	public void setParentid(String _parentid) {
		parentid = _parentid;
	}

	public String getParentid() {
		return parentid;
	}
	
	public void setGrouplevel(String _grouplevel) {
		grouplevel = _grouplevel;
	}

	public String getGrouplevel() {
		return grouplevel;
	}
	
}