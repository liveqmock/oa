/*
 * Created on 2004-5-23
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.folder.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SharePersonSearchDAO extends SearchDAO {
	private String fscAccessright;
	private String cnname;
	private String personuuid;
	public String getFscAccessright() {
		return fscAccessright;
	}
	public void setFscAccessright(String _fscAccessright) {
		firePropertyChange("fscAccessright", fscAccessright, _fscAccessright);
		fscAccessright = _fscAccessright;
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
	protected void setupFields() throws DAOException {
		addField("fscAccessright", "FOLDER_SHAREACCESS.FSC_ACCESSRIGHT");
		addField("cnname", "SYS_PERSON.CNNAME");
		addField("personuuid", "SYS_PERSON.PERSONUUID");
	}
	protected String getSearchSQL() {
		return sql;
	}
	public SharePersonSearchDAO() {
		super();
	}
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	private String sql;
}
