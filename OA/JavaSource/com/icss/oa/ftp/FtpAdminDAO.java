package com.icss.oa.ftp;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FtpAdminDAO extends DAO {
	
	private Integer id;
	private String folder_name;
	private String folder_path;
	private String owener;
	private String org;

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("folder_name", "FOLDER_NAME");
		addField("folder_path", "FOLDER_PATH");
		addField("owener", "OWENER");
		addField("org","ORG");
		setTableName("FTP_ADMIN");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public FtpAdminDAO(Connection conn){
		super(conn);
	}
	
	public String getFolder_name() {
		return folder_name;
	}

	public void setFolder_name(String _folder_name) {
		firePropertyChange("folder_name",folder_name, _folder_name);
		folder_name = _folder_name;
	}

	public String getFolder_path() {
		return folder_path;
	}

	public void setFolder_path(String _folder_path) {
		firePropertyChange("folder_path", folder_path, _folder_path);
		folder_path = _folder_path;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public String getOwener() {
		return owener;
	}

	public void setOwener(String owener) {
		this.owener = owener;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String _org) {
		firePropertyChange("org", org, _org);
		org = _org;
	}

}
