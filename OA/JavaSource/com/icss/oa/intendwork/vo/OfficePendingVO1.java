/*
 * Created on 2005-1-6
 *
 *
 */
package com.icss.oa.intendwork.vo;      

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * 
 */
public class OfficePendingVO1 extends ValueObject {

	private Integer opId;

	private String opTopic;

	private String opSource;

	private String opUrl;

	private String opType;

	private Integer opDate;

	private String opFlag;

	private String personid;

	private Integer opModify;

	private String opNavigate;

	private String opCode;

	public void setOpId(Integer _opId) {
		opId = _opId;
	}

	public Integer getOpId() {
		return opId;
	}

	public void setOpTopic(String _opTopic) {
		opTopic = _opTopic;
	}

	public String getOpTopic() {
		return opTopic;
	}

	public void setOpSource(String _opSource) {
		opSource = _opSource;
	}

	public String getOpSource() {
		return opSource;
	}

	public void setOpUrl(String _opUrl) {
		opUrl = _opUrl;
	}

	public String getOpUrl() {
		return opUrl;
	}

	public void setOpType(String _opType) {
		opType = _opType;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpDate(Integer _opDate) {
		opDate = _opDate;
	}

	public Integer getOpDate() {
		return opDate;
	}

	public void setOpFlag(String _opFlag) {
		opFlag = _opFlag;
	}

	public String getOpFlag() {
		return opFlag;
	}

	public void setPersonid(String _personid) {
		personid = _personid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setOpModify(Integer _opModify) {
		opModify = _opModify;
	}

	public Integer getOpModify() {
		return opModify;
	}

	public void setOpNavigate(String _opNavigate) {
		opNavigate = _opNavigate;
	}

	public String getOpNavigate() {
		return opNavigate;
	}

	public void setOpCode(String _opCode) {
		opCode = _opCode;
	}

	public String getOpCode() {
		return opCode;
	}
}
