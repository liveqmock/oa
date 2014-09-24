/*
 * 创建日期 2005-8-24
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.oftenperson.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class FiletransferStatDAO extends DAO {

	private Integer statid;
	private String senderuserid;
	private String receiveruserid;
	private Integer times;
	public Integer getStatid() {
		return statid;
	}
	public void setStatid(Integer _statid) {
		firePropertyChange("statid", statid, _statid);
		statid = _statid;
	}
	public String getSenderuserid() {
		return senderuserid;
	}
	public void setSenderuserid(String _senderuserid) {
		firePropertyChange("senderuserid", senderuserid, _senderuserid);
		senderuserid = _senderuserid;
	}
	public String getReceiveruserid() {
		return receiveruserid;
	}
	public void setReceiveruserid(String _receiveruserid) {
		firePropertyChange("receiveruserid", receiveruserid, _receiveruserid);
		receiveruserid = _receiveruserid;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer _times) {
		firePropertyChange("times", times, _times);
		times = _times;
	}
	protected void setupFields() throws DAOException {
		addField("statid", "STATID");
		addField("senderuserid", "SENDERUSERID");
		addField("receiveruserid", "RECEIVERUSERID");
		addField("times", "TIMES");
		setTableName("FILETRANSFER_STAT");
		addKey("STATID");
		setAutoIncremented("STATID");
	}
	public FiletransferStatDAO(Connection conn) {
		super(conn);
	}
	public FiletransferStatDAO() {
		super();
	}
}
