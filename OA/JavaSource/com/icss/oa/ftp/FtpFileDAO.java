package com.icss.oa.ftp;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FtpFileDAO extends DAO{
	private Integer id;
	private String filename;
	private String folder;
	private String depuuid;
	private String createruuid;
	private String createtime;
	private String downloadpath;
	
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("filename", "FILENAME");
		addField("folder", "FOLDER");
		addField("depuuid", "DEPUUID");
		addField("createruuid","CREATERUUID");
		addField("createtime","CREATETIME");
		addField("downloadpath","DOWNLOADPATH");
		setTableName("FTP_FILE");
		addKey("ID");
		setAutoIncremented("ID");
	}
	
	public FtpFileDAO(Connection conn){
		super(conn);
	}

	public String getCreateruuid() {
		return createruuid;
	}

	public void setCreateruuid(String _createruuid) {
		firePropertyChange("createruuid",createruuid, _createruuid);
		createruuid = _createruuid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String _createtime) {
		firePropertyChange("createtime",createtime, _createtime);
		createtime = _createtime;
	}

	public String getDepuuid() {
		return depuuid;
	}

	public void setDepuuid(String _depuuid) {
		firePropertyChange("depuuid",depuuid, _depuuid);
		depuuid = _depuuid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String _filename) {
		firePropertyChange("filename",filename, _filename);
		filename = _filename;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String _folder) {
		firePropertyChange("folder",folder, _folder);
		folder = _folder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id",id, _id);
		id = _id;
	}

	public String getDownloadpath() {
		return downloadpath;
	}

	public void setDownloadpath(String _downloadpath) {
		firePropertyChange("downloadpath",downloadpath, _downloadpath);
		downloadpath = _downloadpath;
	}


	
	
}
