package com.icss.oa.ftp;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FtpUserRightDAO extends DAO{
	private String uname;
	private String folder;
	private String right;
	private String org;
	
	protected void setupFields() throws DAOException {
		addField("uname", "UNAME");
		addField("folder", "FOLDER");
		addField("right", "RIGHT");
		addField("org", "ORG");
		setTableName("FTP_USERRIGHT");
	}

	public FtpUserRightDAO(Connection conn){
		super(conn);
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String _folder) {
		firePropertyChange("folder",folder, _folder);
		this.folder = _folder;
	}


	public String getOrg() {
		return org;
	}

	public void setOrg(String _org) {
		firePropertyChange("org",org, _org);
		this.org = _org;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String _right) {
		firePropertyChange("right",right, _right);
		this.right = _right;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String _uname) {
		firePropertyChange("uname",uname, _uname);
		this.uname = _uname;
	}

	




}
