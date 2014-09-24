/*
 * Created on 2004-12-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BbsBoardDAO extends DAO {

	Integer boardid;

	Integer areaid;

	String boardname;

	Long creattime;

	Integer articlenum;

	Integer topicnum;

	String boarddes;

	Long lasttime;

	Integer lastarticleid;

	String lastid;

	String islimited;

	String isaudit;

	String iswork;

	String isview;

	String permit;

	String lastarticlename;

	String lastusername;

	Integer noticeid;

	String applyflag;

	String applypersonuuid;

	public Integer getBoardid() {
		return boardid;
	}

	public void setBoardid(Integer _boardid) {
		firePropertyChange("boardid", boardid, _boardid);
		boardid = _boardid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer _areaid) {
		firePropertyChange("areaid", areaid, _areaid);
		areaid = _areaid;
	}

	public String getBoardname() {
		return boardname;
	}

	public void setBoardname(String _boardname) {
		firePropertyChange("boardname", boardname, _boardname);
		boardname = _boardname;
	}

	public Long getCreattime() {
		return creattime;
	}

	public void setCreattime(Long _creattime) {
		firePropertyChange("creattime", creattime, _creattime);
		creattime = _creattime;
	}

	public Integer getArticlenum() {
		return articlenum;
	}

	public void setArticlenum(Integer _articlenum) {
		firePropertyChange("articlenum", articlenum, _articlenum);
		articlenum = _articlenum;
	}

	public Integer getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(Integer _topicnum) {
		firePropertyChange("topicnum", topicnum, _topicnum);
		topicnum = _topicnum;
	}

	public String getBoarddes() {
		return boarddes;
	}

	public void setBoarddes(String _boarddes) {
		firePropertyChange("boarddes", boarddes, _boarddes);
		boarddes = _boarddes;
	}

	public Long getLasttime() {
		return lasttime;
	}

	public void setLasttime(Long _lasttime) {
		firePropertyChange("lasttime", lasttime, _lasttime);
		lasttime = _lasttime;
	}

	public Integer getLastarticleid() {
		return lastarticleid;
	}

	public void setLastarticleid(Integer _lastarticleid) {
		firePropertyChange("lastarticleid", lastarticleid, _lastarticleid);
		lastarticleid = _lastarticleid;
	}

	public String getLastid() {
		return lastid;
	}

	public void setLastid(String _lastid) {
		firePropertyChange("lastid", lastid, _lastid);
		lastid = _lastid;
	}

	public String getIslimited() {
		return islimited;
	}

	public void setIslimited(String _islimited) {
		firePropertyChange("islimited", islimited, _islimited);
		islimited = _islimited;
	}

	public String getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(String _isaudit) {
		firePropertyChange("isaudit", isaudit, _isaudit);
		isaudit = _isaudit;
	}

	public String getIswork() {
		return iswork;
	}

	public void setIswork(String _iswork) {
		firePropertyChange("iswork", iswork, _iswork);
		iswork = _iswork;
	}

	public String getIsview() {
		return isview;
	}

	public void setIsview(String _isview) {
		firePropertyChange("isview", isview, _isview);
		isview = _isview;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String _permit) {
		firePropertyChange("permit", permit, _permit);
		permit = _permit;
	}

	public String getLastarticlename() {
		return lastarticlename;
	}

	public void setLastarticlename(String _lastarticlename) {
		firePropertyChange("lastarticlename", lastarticlename, _lastarticlename);
		lastarticlename = _lastarticlename;
	}

	public String getLastusername() {
		return lastusername;
	}

	public void setLastusername(String _lastusername) {
		firePropertyChange("lastusername", lastusername, _lastusername);
		lastusername = _lastusername;
	}

	public Integer getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(Integer _noticeid) {
		firePropertyChange("noticeid", noticeid, _noticeid);
		noticeid = _noticeid;
	}

	public String getApplyflag() {
		return applyflag;
	}

	public void setApplyflag(String _applyflag) {
		firePropertyChange("applyflag", applyflag, _applyflag);
		applyflag = _applyflag;
	}

	public String getApplypersonuuid() {
		return applypersonuuid;
	}

	public void setApplypersonuuid(String _applypersonuuid) {
		firePropertyChange("applypersonuuid", applypersonuuid, _applypersonuuid);
		applypersonuuid = _applypersonuuid;
	}

	protected void setupFields() throws DAOException {
		addField("boardid", "BOARDID");
		addField("areaid", "AREAID");
		addField("boardname", "BOARDNAME");
		addField("creattime", "CREATTIME");
		addField("articlenum", "ARTICLENUM");
		addField("topicnum", "TOPICNUM");
		addField("boarddes", "BOARDDES");
		addField("lasttime", "LASTTIME");
		addField("lastarticleid", "LASTARTICLEID");
		addField("lastid", "LASTID");
		addField("islimited", "ISLIMITED");
		addField("isaudit", "ISAUDIT");
		addField("iswork", "ISWORK");
		addField("isview", "ISVIEW");
		addField("permit", "PERMIT");
		addField("lastarticlename", "LASTARTICLENAME");
		addField("lastusername", "LASTUSERNAME");
		addField("noticeid", "NOTICEID");
		addField("applyflag", "APPLYFLAG");
		addField("applypersonuuid", "APPLYPERSONUUID");
		setTableName("BBS_BOARD");
		addKey("BOARDID");
		this.setAutoIncremented("BOARDID");
	}

	public BbsBoardDAO(Connection conn) {
		super(conn);
	}

	public BbsBoardDAO() {
		super();
	}
}
