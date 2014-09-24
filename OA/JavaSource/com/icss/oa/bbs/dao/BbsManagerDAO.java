/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BbsManagerDAO extends DAO {

	private Integer bmId;
	private Integer boardid;
	private String userid;
	public Integer getBmId() {
		return bmId;
	}
	public void setBmId(Integer _bmId) {
		firePropertyChange("bmId", bmId, _bmId);
		bmId = _bmId;
	}
	public Integer getBoardid() {
		return boardid;
	}
	public void setBoardid(Integer _boardid) {
		firePropertyChange("boardid", boardid, _boardid);
		boardid = _boardid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	protected void setupFields() throws DAOException {
		addField("bmId", "BM_ID");
		addField("boardid", "BOARDID");
		addField("userid", "USERID");
		setTableName("BBS_MANAGER");
		addKey("BM_ID");
		setAutoIncremented("BM_ID");
	}
	public BbsManagerDAO(Connection conn) {
		super(conn);
	}
	public BbsManagerDAO() {
		super();
	}
}
