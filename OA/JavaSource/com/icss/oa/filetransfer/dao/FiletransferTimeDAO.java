package com.icss.oa.filetransfer.dao;

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FiletransferTimeDAO extends DAO {

	private Integer id;
	private String senduserid;
	private String receiveuserid;
	private long time;
	
	public FiletransferTimeDAO(Connection conn) {
		super(conn);
	}
	
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("senduserid", "SENDUSERID");
		addField("receiveuserid", "RECEIVEUSERID");
		addField("time", "TIME");
		setTableName("FILETRANSFER_TIME");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReceiveuserid() {
		return receiveuserid;
	}
	public void setReceiveuserid(String receiveuserid) {
		this.receiveuserid = receiveuserid;
	}
	public String getSenduserid() {
		return senduserid;
	}
	public void setSenduserid(String senduserid) {
		this.senduserid = senduserid;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	

}
