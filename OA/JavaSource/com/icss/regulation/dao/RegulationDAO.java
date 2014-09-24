package com.icss.regulation.dao;


import java.sql.Connection;
import java.sql.Timestamp;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class RegulationDAO extends DAO {

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
	

	public RegulationDAO(Connection conn) {
		super(conn);
	}

	public RegulationDAO() {
		super();
	}

	public void setId(java.lang.Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setFlag(java.lang.Integer _flag) {
		firePropertyChange("flag", flag, _flag);
		flag = _flag;
	}

	public java.lang.Integer getFlag() {
		return flag;
	}

	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		title = _title;
	}

	public String getTitle() {
		return title;
	}

	public void setOrg(java.lang.String _org) {
		firePropertyChange("org", org, _org);
		org = _org;
	}

	public java.lang.String getOrg() {
		return org;
	}

	public void setCreateTime(java.sql.Timestamp _createTime) {
		firePropertyChange("createTime", createTime, _createTime);
		createTime = _createTime;
	}

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setContent(java.lang.String _content) {
		firePropertyChange("content", content, _content);
		content = _content;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setEditTime(java.sql.Timestamp _editTime) {
		firePropertyChange("editTime", editTime, _editTime);
		editTime = _editTime;
	}

	public java.sql.Timestamp getEditTime() {
		return editTime;
	}
	
	public void setMemo(java.lang.String _memo) {
		firePropertyChange("memo", memo, _memo);
		memo = _memo;
	}

	public java.lang.String getMemo() {
		return memo;
	}
	
	public void setPersonuuid(java.lang.String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public java.lang.String getPersonuuid() {
		return personuuid;
	}

	public void setRecordNo(java.lang.String _recordNo) {
		firePropertyChange("recordNo", recordNo, _recordNo);
		recordNo = _recordNo;
	}

	public java.lang.String getRecordNo() {
		return recordNo;
	}
	
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("flag", "FLAG");
		addField("title", "TITLE");
		addField("org", "ORG");
		addField("createTime", "CREAT_TIME");
		addField("editTime", "EDIT_TIME");
		addField("content", "CONTENT");
		addField("memo", "MEMO");
		addField("personuuid", "PERSONUUID");
		addField("recordNo","RECORD_NO");
		setTableName("REGULATION_RECORD");
		addKey("ID");
		setAutoIncremented("ID");
	}

}