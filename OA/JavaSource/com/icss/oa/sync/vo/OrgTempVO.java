package com.icss.oa.sync.vo;

import com.icss.j2ee.vo.ValueObject;

public class OrgTempVO extends ValueObject {
	private String id;
	private String type;
	private String business;
	private String orgname;
	private String orgcode;
	private String serialindex;
	private String parentorgid;
	private String updcontent;
	private String oldmsg;
	private String newmsg;
	private Integer pass;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	
	public String getSerialindex() {
		return serialindex;
	}

	public void setSerialindex(String serialindex) {
		this.serialindex = serialindex;
	}

	public String getParentorgid() {
		return parentorgid;
	}

	public void setParentorgid(String parentorgid) {
		this.parentorgid = parentorgid;
	}

	public String getUpdcontent() {
		return updcontent;
	}

	public void setUpdcontent(String updcontent) {
		this.updcontent = updcontent;
	}

	public String getOldmsg() {
		return oldmsg;
	}

	public void setOldmsg(String oldmsg) {
		this.oldmsg = oldmsg;
	}

	public String getNewmsg() {
		return newmsg;
	}

	public void setNewmsg(String newmsg) {
		this.newmsg = newmsg;
	}
	public Integer getPass() {
		return pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}

}