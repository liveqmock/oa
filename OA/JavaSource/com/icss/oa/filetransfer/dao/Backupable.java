package com.icss.oa.filetransfer.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAOException;

public interface Backupable {
	
	public String getReadpersonuuid() ;

	public void setReadpersonuuid(String _readpersonuuid);

	public Integer getMailid();
	
	public void setMailid(Integer _mailid);
	
	public String getIsread();

	public void setIsread(String _isread);

}
