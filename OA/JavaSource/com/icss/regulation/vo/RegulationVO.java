package com.icss.regulation.vo;

import java.sql.Timestamp;

import com.icss.j2ee.vo.ValueObject;

public class RegulationVO extends ValueObject {
	private Integer id;
	private Integer flag;
	private String title;
	private String org;
	private Timestamp createTime;
	private Timestamp editTime;
	private String content;
	private String memo;
	private String personuuid;
	private String recordNo;

	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String personuuid) {
		this.personuuid = personuuid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getEditTime() {
		return editTime;
	}
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}