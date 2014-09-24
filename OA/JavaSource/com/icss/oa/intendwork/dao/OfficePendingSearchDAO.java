/*
 * Created on 2005-1-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.intendwork.dao;    

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OfficePendingSearchDAO extends SearchDAO {

	private Integer opId;

	private String opTopic;

	private String opSource;

	private String opUrl;

	private String opType;

	private Long opDate;

	private String opFlag;

	private String personid;

	private Long opModify;

	private String opNavigate;

	private String opCode;

	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer _opId) {
		firePropertyChange("opId", opId, _opId);
		opId = _opId;
	}

	public String getOpTopic() {
		return opTopic;
	}

	public void setOpTopic(String _opTopic) {
		firePropertyChange("opTopic", opTopic, _opTopic);
		opTopic = _opTopic;
	}

	public String getOpSource() {
		return opSource;
	}

	public void setOpSource(String _opSource) {
		firePropertyChange("opSource", opSource, _opSource);
		opSource = _opSource;
	}

	public String getOpUrl() {
		return opUrl;
	}

	public void setOpUrl(String _opUrl) {
		firePropertyChange("opUrl", opUrl, _opUrl);
		opUrl = _opUrl;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String _opType) {
		firePropertyChange("opType", opType, _opType);
		opType = _opType;
	}

	public Long getOpDate() {
		return opDate;
	}

	public void setOpDate(Long _opDate) {
		firePropertyChange("opDate", opDate, _opDate);
		opDate = _opDate;
	}

	public String getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(String _opFlag) {
		firePropertyChange("opFlag", opFlag, _opFlag);
		opFlag = _opFlag;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		personid = _personid;
	}

	public Long getOpModify() {
		return opModify;
	}

	public void setOpModify(Long _opModify) {
		firePropertyChange("opModify", opModify, _opModify);
		opModify = _opModify;
	}

	public String getOpNavigate() {
		return opNavigate;
	}

	public void setOpNavigate(String _opNavigate) {
		firePropertyChange("opNavigate", opNavigate, _opNavigate);
		opNavigate = _opNavigate;
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String _opCode) {
		firePropertyChange("opCode", opCode, _opCode);
		opCode = _opCode;
	}

	protected void setupFields() throws DAOException {
		addField("opId", "OFFICE_PENDING.OP_ID");
		addField("opTopic", "OFFICE_PENDING.OP_TOPIC");
		addField("opSource", "OFFICE_PENDING.OP_SOURCE");
		addField("opUrl", "OFFICE_PENDING.OP_URL");
		addField("opType", "OFFICE_PENDING.OP_TYPE");
		addField("opDate", "OFFICE_PENDING.OP_DATE");
		addField("opFlag", "OFFICE_PENDING.OP_FLAG");
		addField("personid", "OFFICE_PENDING.PERSONID");
		addField("opModify", "OFFICE_PENDING.OP_MODIFY");
		addField("opNavigate", "OFFICE_PENDING.OP_NAVIGATE");
		addField("opCode", "OFFICE_PENDING.OP_CODE");
	}

	public OfficePendingSearchDAO() {
		super();
	}
	
	protected String getSearchSQL() {
		return sql;
	}
	
	public void setSearchSQL(String sqlline){
		sql=sqlline;
	}
	private String sql;
}
