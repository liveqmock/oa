/*
 * Created on 2004-5-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.maintenance.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DuanxinShortmappingSysOrgVO extends ValueObject {
	Integer smId;
	String smCode;
	String depid;
	String orguuid;
	String cnname;
	public void setSmId(Integer _smId) {
		smId = _smId;
	}
	public Integer getSmId() {
		return smId;
	}
	public void setSmCode(String _smCode) {
		smCode = _smCode;
	}
	public String getSmCode() {
		return smCode;
	}
	public void setDepid(String _depid) {
		depid = _depid;
	}
	public String getDepid() {
		return depid;
	}
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
}
