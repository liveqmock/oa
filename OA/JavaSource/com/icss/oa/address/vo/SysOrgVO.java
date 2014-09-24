package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

public class SysOrgVO extends ValueObject {
	String orguuid;
	String cnname;
	String enname;
	String orgcode;
	String contact;
	String orggrade;
	String orgprop;
	Integer orglevel;
	Integer serialindex;
	String memo;
	String parentorguuid;
	Integer status;
	String orglevelcode;
	String deltag;
	Integer sequenceno;
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String _enname) {
		enname = _enname;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String _orgcode) {
		orgcode = _orgcode;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String _contact) {
		contact = _contact;
	}
	public String getOrggrade() {
		return orggrade;
	}
	public void setOrggrade(String _orggrade) {
		orggrade = _orggrade;
	}
	public String getOrgprop() {
		return orgprop;
	}
	public void setOrgprop(String _orgprop) {
		orgprop = _orgprop;
	}
	public Integer getOrglevel() {
		return orglevel;
	}
	public void setOrglevel(Integer _orglevel) {
		orglevel = _orglevel;
	}
	public Integer getSerialindex() {
		return serialindex;
	}
	public void setSerialindex(Integer _serialindex) {
		serialindex = _serialindex;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String _memo) {
		memo = _memo;
	}
	public String getParentorguuid() {
		return parentorguuid;
	}
	public void setParentorguuid(String _parentorguuid) {
		parentorguuid = _parentorguuid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer _status) {
		status = _status;
	}
	public String getOrglevelcode() {
		return orglevelcode;
	}
	public void setOrglevelcode(String _orglevelcode) {
		orglevelcode = _orglevelcode;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String _deltag) {
		deltag = _deltag;
	}
	public Integer getSequenceno() {
		return sequenceno;
	}
	public void setSequenceno(Integer _sequenceno) {
		sequenceno = _sequenceno;
	}
}