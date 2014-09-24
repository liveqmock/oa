/*
 * Created on 2004-6-2
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.phonebook.vo;

import com.icss.j2ee.vo.ValueObject;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SysOrgVO extends ValueObject {
	private String orguuid;
	private String cnname;
	private Integer orglevel;
	private String parentorguuid;
	private String deltag;
	private Integer serialindex;
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
	public Integer getOrglevel() {
		return orglevel;
	}
	public void setOrglevel(Integer _orglevel) {
		orglevel = _orglevel;
	}
	public String getParentorguuid() {
		return parentorguuid;
	}
	public void setParentorguuid(String _parentorguuid) {
		parentorguuid = _parentorguuid;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String _deltag) {
		deltag = _deltag;
	}
	public Integer getSerialindex() {
		return serialindex;
	}
	public void setSerialindex(Integer _serialindex) {
		serialindex = _serialindex;
	}
}



