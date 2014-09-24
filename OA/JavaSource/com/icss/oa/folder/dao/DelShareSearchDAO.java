/*
 * Created on 2004-5-24
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
public class DelShareSearchDAO extends SearchDAO {
	private Integer fscId;
	public Integer getFscId() {
		return fscId;
	}
	public void setFscId(Integer _fscId) {
		firePropertyChange("fscId", fscId, _fscId);
		fscId = _fscId;
	}
	protected void setupFields() throws DAOException {
		addField("fscId", "FOLDER_SHAREACCESS.FSC_ID");
	}
	protected String getSearchSQL() {
		return sql;
	}
	public DelShareSearchDAO() {
		super();
	}
	private String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
}
