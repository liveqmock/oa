/*
 * Created on 2004-12-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BbsArticleBbsBoardaccSearchDAO extends SearchDAO {

	Integer articleid;
    String personuuid;
	String articlename;
	Long emittime;
	String articlesize;
	String ip;
	String prime;
	String top;
	String topic;
	String articlecontent;
	String articlelock;
	Integer reid;
	String reuserid;
	Long retime;
	Integer renum;
	Integer hitnum;
	Long edittime;
	String acctype;
	String face;
	String isview;
	String reusername;
	Integer accid;
	String tointend;
	Integer bbaId;
	Integer boardid;
	String accpersonuuid;

	public Integer getArticleid() {
		return articleid;
	}

	public void setArticleid(Integer _articleid) {
		firePropertyChange("articleid", articleid, _articleid);
		articleid = _articleid;
	}
	
	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public String getArticlename() {
		return articlename;
	}

	public void setArticlename(String _articlename) {
		firePropertyChange("articlename", articlename, _articlename);
		articlename = _articlename;
	}

	public Long getEmittime() {
		return emittime;
	}

	public void setEmittime(Long _emittime) {
		firePropertyChange("emittime", emittime, _emittime);
		emittime = _emittime;
	}

	public String getArticlesize() {
		return articlesize;
	}

	public void setArticlesize(String _articlesize) {
		firePropertyChange("articlesize", articlesize, _articlesize);
		articlesize = _articlesize;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String _ip) {
		firePropertyChange("ip", ip, _ip);
		ip = _ip;
	}

	public String getPrime() {
		return prime;
	}

	public void setPrime(String _prime) {
		firePropertyChange("prime", prime, _prime);
		prime = _prime;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String _top) {
		firePropertyChange("top", top, _top);
		top = _top;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String _topic) {
		firePropertyChange("topic", topic, _topic);
		topic = _topic;
	}

	public String getArticlecontent() {
		return articlecontent;
	}

	public void setArticlecontent(String _articlecontent) {
		firePropertyChange("articlecontent", articlecontent, _articlecontent);
		articlecontent = _articlecontent;
	}

	public String getArticlelock() {
		return articlelock;
	}

	public void setArticlelock(String _articlelock) {
		firePropertyChange("articlelock", articlelock, _articlelock);
		articlelock = _articlelock;
	}

	public Integer getReid() {
		return reid;
	}

	public void setReid(Integer _reid) {
		firePropertyChange("reid", reid, _reid);
		reid = _reid;
	}

	public String getReuserid() {
		return reuserid;
	}

	public void setReuserid(String _reuserid) {
		firePropertyChange("reuserid", reuserid, _reuserid);
		reuserid = _reuserid;
	}

	public Long getRetime() {
		return retime;
	}

	public void setRetime(Long _retime) {
		firePropertyChange("retime", retime, _retime);
		retime = _retime;
	}

	public Integer getRenum() {
		return renum;
	}

	public void setRenum(Integer _renum) {
		firePropertyChange("renum", renum, _renum);
		renum = _renum;
	}

	public Integer getHitnum() {
		return hitnum;
	}

	public void setHitnum(Integer _hitnum) {
		firePropertyChange("hitnum", hitnum, _hitnum);
		hitnum = _hitnum;
	}

	public Long getEdittime() {
		return edittime;
	}

	public void setEdittime(Long _edittime) {
		firePropertyChange("edittime", edittime, _edittime);
		edittime = _edittime;
	}

	public String getAcctype() {
		return acctype;
	}

	public void setAcctype(String _acctype) {
		firePropertyChange("acctype", acctype, _acctype);
		acctype = _acctype;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String _face) {
		firePropertyChange("face", face, _face);
		face = _face;
	}

	public String getIsview() {
		return isview;
	}

	public void setIsview(String _isview) {
		firePropertyChange("isview", isview, _isview);
		isview = _isview;
	}

	public String getReusername() {
		return reusername;
	}

	public void setReusername(String _reusername) {
		firePropertyChange("reusername", reusername, _reusername);
		reusername = _reusername;
	}

	public Integer getAccid() {
		return accid;
	}

	public void setAccid(Integer _accid) {
		firePropertyChange("accid", accid, _accid);
		accid = _accid;
	}

	public String getTointend() {
		return tointend;
	}

	public void setTointend(String _tointend) {
		firePropertyChange("tointend", tointend, _tointend);
		tointend = _tointend;
	}

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

	public String getAccpersonuuid() {
		return accpersonuuid;
	}

	public void setAccpersonuuid(String _accpersonuuid) {
		firePropertyChange("accpersonuuid", accpersonuuid, _accpersonuuid);
		accpersonuuid = _accpersonuuid;
	}

	protected void setupFields() throws DAOException {
		addField("articleid", "BBS_ARTICLE.ARTICLEID");
		addField("personuuid", "PERSONUUID");
		addField("boardid", "BBS_ARTICLE.BOARDID");
		addField("articlename", "BBS_ARTICLE.ARTICLENAME");
		addField("emittime", "BBS_ARTICLE.EMITTIME");
		addField("articlesize", "BBS_ARTICLE.ARTICLESIZE");
		addField("ip", "BBS_ARTICLE.IP");
		addField("prime", "BBS_ARTICLE.PRIME");
		addField("top", "BBS_ARTICLE.TOP");
		addField("topic", "BBS_ARTICLE.TOPIC");
		addField("articlecontent", "BBS_ARTICLE.ARTICLECONTENT");
		addField("articlelock", "BBS_ARTICLE.ARTICLELOCK");
		addField("reid", "BBS_ARTICLE.REID");
		addField("reuserid", "BBS_ARTICLE.REUSERID");
		addField("retime", "BBS_ARTICLE.RETIME");
		addField("renum", "BBS_ARTICLE.RENUM");
		addField("hitnum", "BBS_ARTICLE.HITNUM");
		addField("edittime", "BBS_ARTICLE.EDITTIME");
		addField("acctype", "BBS_ARTICLE.ACCTYPE");
		addField("face", "BBS_ARTICLE.FACE");
		addField("isview", "BBS_ARTICLE.ISVIEW");
		addField("reusername", "BBS_ARTICLE.REUSERNAME");
		addField("accid", "BBS_ARTICLE.ACCID");
		addField("tointend", "BBS_ARTICLE.TOINTEND");
		addField("bbaId", "BBS_BOARDACC.BBA_ID");
		addField("accpersonuuid", "ACCPERSONUUID");
	}

	String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public BbsArticleBbsBoardaccSearchDAO() {
		super();
	}
}
