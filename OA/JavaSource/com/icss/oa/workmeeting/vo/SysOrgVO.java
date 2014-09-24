/*
 * Created on 2004-10-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.workmeeting.vo;  

import com.icss.j2ee.vo.ValueObject;  

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgVO extends ValueObject {

	private String orguuid;

	private String cnname;

	private String enname;

	private String orgcode;

	private String contact;

	private String orggrade;

	private String orgprop;

	private Integer orglevel;

	private Integer serialindex;

	private String memo;

	private String parentorguuid;

	private Integer status;

	private String orglevelcode;

	private String deltag;

	private Integer sequenceno;

	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}

	public String getOrguuid() {
		return orguuid;
	}

	public void setCnname(String _cnname) {
		cnname = _cnname;
	}

	public String getCnname() {
		return cnname;
	}

	public void setEnname(String _enname) {
		enname = _enname;
	}

	public String getEnname() {
		return enname;
	}

	public void setOrgcode(String _orgcode) {
		orgcode = _orgcode;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setContact(String _contact) {
		contact = _contact;
	}

	public String getContact() {
		return contact;
	}

	public void setOrggrade(String _orggrade) {
		orggrade = _orggrade;
	}

	public String getOrggrade() {
		return orggrade;
	}

	public void setOrgprop(String _orgprop) {
		orgprop = _orgprop;
	}

	public String getOrgprop() {
		return orgprop;
	}

	public void setOrglevel(Integer _orglevel) {
		orglevel = _orglevel;
	}

	public Integer getOrglevel() {
		return orglevel;
	}

	public void setSerialindex(Integer _serialindex) {
		serialindex = _serialindex;
	}

	public Integer getSerialindex() {
		return serialindex;
	}

	public void setMemo(String _memo) {
		memo = _memo;
	}

	public String getMemo() {
		return memo;
	}

	public void setParentorguuid(String _parentorguuid) {
		parentorguuid = _parentorguuid;
	}

	public String getParentorguuid() {
		return parentorguuid;
	}

	public void setStatus(Integer _status) {
		status = _status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setOrglevelcode(String _orglevelcode) {
		orglevelcode = _orglevelcode;
	}

	public String getOrglevelcode() {
		return orglevelcode;
	}

	public void setDeltag(String _deltag) {
		deltag = _deltag;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setSequenceno(Integer _sequenceno) {
		sequenceno = _sequenceno;
	}

	public Integer getSequenceno() {
		return sequenceno;
	}
}
