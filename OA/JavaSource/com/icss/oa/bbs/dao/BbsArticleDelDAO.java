/*
 * Created on 2004-4-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.dao;

import java.sql.Connection;
import java.sql.Timestamp;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BbsArticleDelDAO extends DAO {

	private String delpersonuuid;
	private Timestamp deltime;
	private Integer articleid;
	private Integer boardid;
	private String userid;
	private Integer accid;
	private String articlename;
	private Long emittime;
	private String articlesize;
	private String ip;
	private String prime;
	private String top;
	private String topic;
	private String articlecontent;
	private String articlelock;
	private Integer reid;
	private String reuserid;
	private Long retime;
	private Integer renum;
	private Integer hitnum;
	private Long edittime;
	private String acctype;
	private String isview;
	private String face;
	private String reusername;
	private String tointend;
	
	public Timestamp getDeltime() {
		return deltime;
	}
	public void setDeltime(Timestamp _deltime) {
		firePropertyChange("deltime", deltime, _deltime);
		deltime = _deltime;
	}
	
	public String getDelpersonuuid() {
		return delpersonuuid;
	}
	public void setDelpersonuuid(String _delpersonuuid) {
		firePropertyChange("delpersonuuid", delpersonuuid, _delpersonuuid);
		delpersonuuid = _delpersonuuid;
	}
	
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer _articleid) {
		firePropertyChange("articleid", articleid, _articleid);
		articleid = _articleid;
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
	public Integer getAccid() {
		return accid;
	}
	public void setAccid(Integer _accid) {
		firePropertyChange("accid", accid, _accid);
		accid = _accid;
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
	public String getIsview() {
		return isview;
	}
	public void setIsview(String _isview) {
		firePropertyChange("isview", isview, _isview);
		isview = _isview;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String _face) {
		firePropertyChange("face", face, _face);
		face = _face;
	}
	public String getReusername() {
		return reusername;
	}
	public void setReusername(String _reusername) {
		firePropertyChange("reusername", reusername, _reusername);
		reusername = _reusername;
	}
	public String getTointend() {
		return tointend;
	}
	public void setTointend(String _tointend) {
		firePropertyChange("tointend", tointend, _tointend);
		tointend = _tointend;
	}
	protected void setupFields() throws DAOException {
		addField("delpersonuuid","DELPERSONUUID");
		addField("deltime","DELTIME");
		addField("articleid", "ARTICLEID");
		addField("boardid", "BOARDID");
		addField("userid", "USERID");
		addField("accid", "ACCID");
		addField("articlename", "ARTICLENAME");
		addField("emittime", "EMITTIME");
		addField("articlesize", "ARTICLESIZE");
		addField("ip", "IP");
		addField("prime", "PRIME");
		addField("top", "TOP");
		addField("topic", "TOPIC");
		addField("articlecontent", "ARTICLECONTENT");
		addField("articlelock", "ARTICLELOCK");
		addField("reid", "REID");
		addField("reuserid", "REUSERID");
		addField("retime", "RETIME");
		addField("renum", "RENUM");
		addField("hitnum", "HITNUM");
		addField("edittime", "EDITTIME");
		addField("acctype", "ACCTYPE");
		addField("isview", "ISVIEW");
		addField("face", "FACE");
		addField("reusername", "REUSERNAME");
		addField("tointend", "TOINTEND");
		setTableName("BBS_ARTICLE_DEL");
		//addKey("ARTICLEID");
		//setAutoIncremented("ARTICLEID");
	}
	public BbsArticleDelDAO(Connection conn) {
		super(conn);
	}
	public BbsArticleDelDAO() {
		super();
	}
	
	String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
}
