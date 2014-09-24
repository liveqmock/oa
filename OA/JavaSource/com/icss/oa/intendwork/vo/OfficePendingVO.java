package com.icss.oa.intendwork.vo;

import com.icss.j2ee.vo.ValueObject;    

public class OfficePendingVO extends ValueObject {
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
		opId = _opId;
	}
	public String getOpTopic() {
		return opTopic;
	}
	public void setOpTopic(String _opTopic) {
		opTopic = _opTopic;
	}
	public String getOpSource() {
		return opSource;
	}
	public void setOpSource(String _opSource) {
		opSource = _opSource;
	}
	public String getOpUrl() {
		return opUrl;
	}
	public void setOpUrl(String _opUrl) {
		opUrl = _opUrl;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String _opType) {
		opType = _opType;
	}
	public Long getOpDate() {
		return opDate;
	}
	public void setOpDate(Long _opDate) {
		opDate = _opDate;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String _opFlag) {
		opFlag = _opFlag;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		personid = _personid;
	}
	public Long getOpModify() {
		return opModify;
	}
	public void setOpModify(Long _opModify) {
		opModify = _opModify;
	}
	public String getOpNavigate() {
		return opNavigate;
	}
	public void setOpNavigate(String _opNavigate) {
		opNavigate = _opNavigate;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String _opCode) {
		opCode = _opCode;
	}
}