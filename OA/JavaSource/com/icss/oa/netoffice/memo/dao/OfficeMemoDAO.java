/*
 * Created on 2004-5-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeMemoDAO extends DAO {
	Integer memoId;
	String memoContent;
	Long memoTime;
	String memoHeadline;
	String memoOwnerid;
	public Integer getMemoId() {
		return memoId;
	}
	public void setMemoId(Integer _memoId) {
		firePropertyChange("memoId", memoId, _memoId);
		memoId = _memoId;
	}
	public String getMemoContent() {
		return memoContent;
	}
	public void setMemoContent(String _memoContent) {
		firePropertyChange("memoContent", memoContent, _memoContent);
		memoContent = _memoContent;
	}
	public Long getMemoTime() {
		return memoTime;
	}
	public void setMemoTime(Long _memoTime) {
		firePropertyChange("memoTime", memoTime, _memoTime);
		memoTime = _memoTime;
	}
	public String getMemoHeadline() {
		return memoHeadline;
	}
	public void setMemoHeadline(String _memoHeadline) {
		firePropertyChange("memoHeadline", memoHeadline, _memoHeadline);
		memoHeadline = _memoHeadline;
	}
	public String getMemoOwnerid() {
		return memoOwnerid;
	}
	public void setMemoOwnerid(String _memoOwnerid) {
		firePropertyChange("memoOwnerid", memoOwnerid, _memoOwnerid);
		memoOwnerid = _memoOwnerid;
	}
	protected void setupFields() throws DAOException {
		addField("memoId", "MEMO_ID");
		addField("memoContent", "MEMO_CONTENT");
		addField("memoTime", "MEMO_TIME");
		addField("memoHeadline", "MEMO_HEADLINE");
		addField("memoOwnerid", "MEMO_OWNERID");
		setTableName("OFFICE_MEMO");
		addKey("MEMO_ID");
		this.setAutoIncremented("MEMO_ID");
	}
	public OfficeMemoDAO(Connection conn) {
		super(conn);
	}
	public OfficeMemoDAO() {
		super();
	}
}
