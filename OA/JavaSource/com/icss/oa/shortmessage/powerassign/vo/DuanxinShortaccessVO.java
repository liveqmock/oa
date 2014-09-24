/*
 * Created on 2004-5-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DuanxinShortaccessVO extends ValueObject {
	Integer saId;
	String personid;
	String depid;
	String accessdepid;
	public void setSaId(Integer _saId) {
		saId = _saId;
	}
	public Integer getSaId() {
		return saId;
	}
	public void setPersonid(String _personid) {
		personid = _personid;
	}
	public String getPersonid() {
		return personid;
	}
	public void setDepid(String _depid) {
		depid = _depid;
	}
	public String getDepid() {
		return depid;
	}
	public void setAccessdepid(String _accessdepid) {
		accessdepid = _accessdepid;
	}
	public String getAccessdepid() {
		return accessdepid;
	}
}
