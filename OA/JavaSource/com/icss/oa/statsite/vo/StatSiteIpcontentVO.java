/*
 * Created on 2004-7-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.statsite.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatSiteIpcontentVO extends ValueObject{

	Integer id;

	Long startip;

	Long endip;

	String ipcontent;

	String ipmeno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		id = _id;
	}

	public Long getStartip() {
		return startip;
	}

	public void setStartip(Long _startip) {
		startip = _startip;
	}

	public Long getEndip() {
		return endip;
	}

	public void setEndip(Long _endip) {
		endip = _endip;
	}

	public String getIpcontent() {
		return ipcontent;
	}

	public void setIpcontent(String _ipcontent) {
		ipcontent = _ipcontent;
	}

	public String getIpmeno() {
		return ipmeno;
	}

	public void setIpmeno(String _ipmeno) {
		ipmeno = _ipmeno;
	}
}
