/*
 * Created on 2004-5-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;
import com.icss.oa.shortmessage.powerassign.handler.DXPowerHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DXShortaccessOrgPersonSearchDAO extends SearchDAO{
	Integer saId;
	String personid;
	String depid;
	String accessdepid;
	String orguuid;
	String cnname;
	String personuuid;
	String cnnameper;
	String orgidFromHandler=DXPowerHandler.toSerchdaoId;
	public Integer getSaId() {
		return saId;
	}
	public void setSaId(Integer _saId) {
		firePropertyChange("saId", saId, _saId);
		saId = _saId;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		personid = _personid;
	}
	public String getDepid() {
		return depid;
	}
	public void setDepid(String _depid) {
		firePropertyChange("depid", depid, _depid);
		depid = _depid;
	}
	public String getAccessdepid() {
		return accessdepid;
	}
	public void setAccessdepid(String _accessdepid) {
		firePropertyChange("accessdepid", accessdepid, _accessdepid);
		accessdepid = _accessdepid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}
	public String getCnnameper() {
		return cnnameper;
	}
	public void setCnnameper(String _cnnameper) {
		firePropertyChange("cnnameper", cnnameper, _cnnameper);
		cnnameper = _cnnameper;
	}
	protected void setupFields() throws DAOException {
		addField("saId", "DUANXIN_SHORTACCESS.SA_ID");
		addField("personid", "DUANXIN_SHORTACCESS.PERSONID");
		addField("depid", "DUANXIN_SHORTACCESS.DEPID");
		addField("accessdepid", "DUANXIN_SHORTACCESS.ACCESSDEPID");
		addField("orguuid", "SYS_ORG.ORGUUID");
		addField("cnname", "SYS_ORG.CNNAME");
		addField("personuuid", "SYS_PERSON.PERSONUUID");
		addField("cnnameper", "SYS_PERSON.CNNAME");
	}
	protected String getSearchSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("DUANXIN_SHORTACCESS.SA_ID,DUANXIN_SHORTACCESS.PERSONID,DUANXIN_SHORTACCESS.DEPID,DUANXIN_SHORTACCESS.ACCESSDEPID,SYS_ORG.ORGUUID,SYS_ORG.CNNAME,SYS_PERSON.PERSONUUID,SYS_PERSON.CNNAME ");
		sql.append("FROM ");
		sql.append("DUANXIN_SHORTACCESS,SYS_ORG,SYS_PERSON ");
		sql.append("WHERE ");
		sql.append("DUANXIN_SHORTACCESS.DEPID = SYS_ORG.ORGUUID ");
		sql.append("AND ");
		sql.append("DUANXIN_SHORTACCESS.PERSONID = SYS_PERSON.PERSONUUID ");
		sql.append("AND ");
		sql.append("DUANXIN_SHORTACCESS.ACCESSDEPID="+"'"+orgidFromHandler+"'");
		System.out.println("********************");
		System.out.println(orgidFromHandler);
		System.out.println("********************");
		return sql.toString();
	}
	public DXShortaccessOrgPersonSearchDAO() {
		super();
	}
}
