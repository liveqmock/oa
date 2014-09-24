package com.icss.oa.ftp;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FtpFolderDAO extends DAO{
	private Integer id;
	private String name;
	private String pid;
	private String right;
	private String father;
	private String fid;
	
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("name", "NAME");
		addField("pid", "PID");
		addField("right", "RIGHT");
		addField("father","FATHER");
		addField("fid","FID");
		setTableName("FTP_FOLDER");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public FtpFolderDAO(Connection conn){
		super(conn);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id",id, _id);
		id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		firePropertyChange("name",name, _name);
		name = _name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String _pid) {
		firePropertyChange("pid",pid, _pid);
		pid = _pid;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String _right) {
		firePropertyChange("right",right, _right);
		right = _right;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String _father) {
		firePropertyChange("father",father, _father);
		father = _father;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String _fid) {
		firePropertyChange("fid",fid, _fid);
		this.fid = _fid;
	}
	

}
