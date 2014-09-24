/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PhoneInfoSysPersonSearchDAO extends SearchDAO {
	private String mobilephone;
	private String personuuid;
	private String cnname;
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String _mobilephone) {
		firePropertyChange("mobilephone", mobilephone, _mobilephone);
		mobilephone = _mobilephone;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	protected void setupFields() throws DAOException {
		addField("mobilephone", "PHONE_INFO.MOBILEPHONE");
		addField("personuuid", "SYS_PERSON.PERSONUUID");
		addField("cnname", "SYS_PERSON.CNNAME");
	}
	private String sql;
	public void setSearchSQL(String _sql){
		sql=_sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public PhoneInfoSysPersonSearchDAO() {
		super();
	}
}
