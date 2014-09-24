/*
 * Created on 2004-8-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhoneCustomsqlDAO extends DAO {

	private Integer sqlId;
	private String userid;
	private String orguuid;
	private String sqlContent;
	private String sqlTitle;
	public Integer getSqlId() {
		return sqlId;
	}
	public void setSqlId(Integer _sqlId) {
		firePropertyChange("sqlId", sqlId, _sqlId);
		sqlId = _sqlId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}
	public String getSqlContent() {
		return sqlContent;
	}
	public void setSqlContent(String _sqlContent) {
		firePropertyChange("sqlContent", sqlContent, _sqlContent);
		sqlContent = _sqlContent;
	}
	public String getSqlTitle() {
		return sqlTitle;
	}
	public void setSqlTitle(String _sqlTitle) {
		firePropertyChange("sqlTitle", sqlTitle, _sqlTitle);
		sqlTitle = _sqlTitle;
	}
	protected void setupFields() throws DAOException {
		addField("sqlId", "SQL_ID");
		addField("userid", "USERID");
		addField("orguuid", "ORGUUID");
		addField("sqlContent", "SQL_CONTENT");
		addField("sqlTitle", "SQL_TITLE");
		setTableName("PHONE_CUSTOMSQL");
		addKey("SQL_ID");
		setAutoIncremented("SQL_ID");
	}
	public PhoneCustomsqlDAO(Connection conn) {
		super(conn);
	}
	public PhoneCustomsqlDAO() {
		super();
	}
}
