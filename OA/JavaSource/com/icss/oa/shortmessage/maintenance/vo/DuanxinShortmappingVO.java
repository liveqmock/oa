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
public class DuanxinShortmappingVO extends ValueObject {
	Integer smId;
	String smCode;
	String depid;
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
}
