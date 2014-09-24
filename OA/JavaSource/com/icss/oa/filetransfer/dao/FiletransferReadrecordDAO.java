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
public class FiletransferReadrecordDAO extends DAO {

	String readpersonuuid;

	Integer mailid;

	String isread;

	public String getReadpersonuuid() {
		return readpersonuuid;
	}

	public void setReadpersonuuid(String _readpersonuuid) {
		firePropertyChange("readpersonuuid", readpersonuuid, _readpersonuuid);
		readpersonuuid = _readpersonuuid;
	}

	public Integer getMailid() {
		return mailid;
	}

	public void setMailid(Integer _mailid) {
		firePropertyChange("mailid", mailid, _mailid);
		mailid = _mailid;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String _isread) {
		firePropertyChange("isread", isread, _isread);
		isread = _isread;
	}

	protected void setupFields() throws DAOException {
		addField("readpersonuuid", "READPERSONUUID");
		addField("mailid", "MAILID");
		addField("isread", "ISREAD");
		setTableName("FILETRANSFER_READRECORD");
		addKey("READPERSONUUID");
	}

	public FiletransferReadrecordDAO(Connection conn) {
		super(conn);
	}

	public FiletransferReadrecordDAO() {
		super();
	}
}
