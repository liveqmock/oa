/*
 * Created on 2004-3-29
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
public class BbsNoticeDAO extends DAO {

	private String title;
	private Long noticedate;
	private String content;
	private Integer noticeid;
	private Integer boardid;
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		title = _title;
	}
	public Long getNoticedate() {
		return noticedate;
	}
	public void setNoticedate(Long _noticedate) {
		firePropertyChange("noticedate", noticedate, _noticedate);
		noticedate = _noticedate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		firePropertyChange("content", content, _content);
		content = _content;
	}
	public Integer getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(Integer _noticeid) {
		firePropertyChange("noticeid", noticeid, _noticeid);
		noticeid = _noticeid;
	}
	public Integer getBoardid() {
		return boardid;
	}
	public void setBoardid(Integer _boardid) {
		firePropertyChange("boardid", boardid, _boardid);
		boardid = _boardid;
	}
	protected void setupFields() throws DAOException {
		addField("title", "TITLE");
		addField("noticedate", "NOTICEDATE");
		addField("content", "CONTENT");
		addField("noticeid", "NOTICEID");
		addField("boardid", "BOARDID");
		setTableName("BBS_NOTICE");
		addKey("NOTICEID");
		setAutoIncremented("NOTICEID");
	}
	public BbsNoticeDAO(Connection conn) {
		super(conn);
	}
	public BbsNoticeDAO() {
		super();
	}
}
