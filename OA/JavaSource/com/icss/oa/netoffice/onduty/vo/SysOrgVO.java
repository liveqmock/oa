/*
 * Created on 2004-7-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SysOrgVO extends ValueObject {
	private String orguuid;
	private String cnname;
	private String enname;
	private String orgcode;
	private String orggrade;
	private String parentorguuid;
	private Integer status;
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
	public String getOrggrade() {
		return orggrade;
	}
	public void setOrggrade(String _orggrade) {
		orggrade = _orggrade;
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
}