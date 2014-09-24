/*
 * Created on 2004-12-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FiletransferWanttorecordDAO extends DAO {

	Integer mailid;

	String recordid;

	public Integer getMailid() {
		return mailid;
	}

	public void setMailid(Integer _mailid) {
		firePropertyChange("mailid", mailid, _mailid);
		mailid = _mailid;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String _recordid) {
		firePropertyChange("recordid", recordid, _recordid);
		recordid = _recordid;
	}

	protected void setupFields() throws DAOException {
		addField("mailid", "MAILID");
		addField("recordid", "RECORDID");
		setTableName("FILETRANSFER_WANTTORECORD");
		addKey("MAILID");
		this.setAutoIncremented("MAILID");
	}

	public FiletransferWanttorecordDAO(Connection conn) {
		super(conn);
	}

	public FiletransferWanttorecordDAO() {
		super();
	}
}
