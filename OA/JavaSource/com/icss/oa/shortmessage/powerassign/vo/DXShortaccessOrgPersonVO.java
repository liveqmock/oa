/*
 * Created on 2004-5-12
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
public class DXShortaccessOrgPersonVO extends ValueObject {
	Integer saId;
	String personid;
	String depid;
	String accessdepid;
	String orguuid;
	String cnname;
	String personuuid;
	String cnnameper;
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
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setCnnameper(String _cnnameper) {
		cnnameper = _cnnameper;
	}
	public String getCnnameper() {
		return cnnameper;
	}
}
