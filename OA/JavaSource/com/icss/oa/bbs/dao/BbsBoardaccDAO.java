/*
 * Created on 2004-4-1
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
public class BbsBoardaccDAO extends DAO {

	private Integer bbaId;
	private Integer boardid;
	private String userid;
	public Integer getBbaId() {
		return bbaId;
	}
	public void setBbaId(Integer _bbaId) {
		firePropertyChange("bbaId", bbaId, _bbaId);
		bbaId = _bbaId;
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
		addField("bbaId", "BBA_ID");
		addField("boardid", "BOARDID");
		addField("userid", "USERID");
		setTableName("BBS_BOARDACC");
		addKey("BBA_ID");
		setAutoIncremented("BBA_ID");
	}
	public BbsBoardaccDAO(Connection conn) {
		super(conn);
	}
	public BbsBoardaccDAO() {
		super();
	}
}
