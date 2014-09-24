/*
 * Created on 2004-5-8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ArticleUserinfoSearchDAO extends SearchDAO {

	private Integer articleid;
	private Integer boardid;
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
	private String face;
	private String isview;
	private String reusername;
	private Integer accid;
	private String userid;
	private String onlineid;
	private Integer bmId;
	private Integer bbaId;
	private String oicq;
	private String userpic;
	private String underwrite;
	private Integer pubnum;
	private Integer accessnum;
	private String userlevel;
	private Long regdate;
	private String onLine;
	private String username;
	private String homepage;
	private String mail;
	private Integer exp;
	private String isleader;
	private String truename;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public String getOnlineid() {
		return onlineid;
	}
	public void setOnlineid(String _onlineid) {
		firePropertyChange("onlineid", onlineid, _onlineid);
		onlineid = _onlineid;
	}
	public Integer getBmId() {
		return bmId;
	}
	public void setBmId(Integer _bmId) {
		firePropertyChange("bmId", bmId, _bmId);
		bmId = _bmId;
	}
	public Integer getBbaId() {
		return bbaId;
	}
	public void setBbaId(Integer _bbaId) {
		firePropertyChange("bbaId", bbaId, _bbaId);
		bbaId = _bbaId;
	}
	public String getOicq() {
		return oicq;
	}
	public void setOicq(String _oicq) {
		firePropertyChange("oicq", oicq, _oicq);
		oicq = _oicq;
	}
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String _userpic) {
		firePropertyChange("userpic", userpic, _userpic);
		userpic = _userpic;
	}
	public String getUnderwrite() {
		return underwrite;
	}
	public void setUnderwrite(String _underwrite) {
		firePropertyChange("underwrite", underwrite, _underwrite);
		underwrite = _underwrite;
	}
	public Integer getPubnum() {
		return pubnum;
	}
	public void setPubnum(Integer _pubnum) {
		firePropertyChange("pubnum", pubnum, _pubnum);
		pubnum = _pubnum;
	}
	public Integer getAccessnum() {
		return accessnum;
	}
	public void setAccessnum(Integer _accessnum) {
		firePropertyChange("accessnum", accessnum, _accessnum);
		accessnum = _accessnum;
	}
	public String getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(String _userlevel) {
		firePropertyChange("userlevel", userlevel, _userlevel);
		userlevel = _userlevel;
	}
	public Long getRegdate() {
		return regdate;
	}
	public void setRegdate(Long _regdate) {
		firePropertyChange("regdate", regdate, _regdate);
		regdate = _regdate;
	}
	public String getOnLine() {
		return onLine;
	}
	public void setOnLine(String _onLine) {
		firePropertyChange("onLine", onLine, _onLine);
		onLine = _onLine;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String _username) {
		firePropertyChange("username", username, _username);
		username = _username;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String _homepage) {
		firePropertyChange("homepage", homepage, _homepage);
		homepage = _homepage;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String _mail) {
		firePropertyChange("mail", mail, _mail);
		mail = _mail;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer _exp) {
		firePropertyChange("exp", exp, _exp);
		exp = _exp;
	}
	public String getIsleader() {
		return isleader;
	}
	public void setIsleader(String _isleader) {
		firePropertyChange("isleader", isleader, _isleader);
		isleader = _isleader;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String _truename) {
		firePropertyChange("truename", truename, _truename);
		truename = _truename;
	}
	protected void setupFields() throws DAOException {
		addField("articleid", "BBS_ARTICLE.ARTICLEID");
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
		addField("userid", "BBS_USERINFO.USERID");
		addField("onlineid", "BBS_USERINFO.ONLINEID");
		addField("bmId", "BBS_USERINFO.BM_ID");
		addField("bbaId", "BBS_USERINFO.BBA_ID");
		addField("oicq", "BBS_USERINFO.OICQ");
		addField("userpic", "BBS_USERINFO.USERPIC");
		addField("underwrite", "BBS_USERINFO.UNDERWRITE");
		addField("pubnum", "BBS_USERINFO.PUBNUM");
		addField("accessnum", "BBS_USERINFO.ACCESSNUM");
		addField("userlevel", "BBS_USERINFO.USERLEVEL");
		addField("regdate", "BBS_USERINFO.REGDATE");
		addField("onLine", "BBS_USERINFO.ON_LINE");
		addField("username", "BBS_USERINFO.USERNAME");
		addField("homepage", "BBS_USERINFO.HOMEPAGE");
		addField("mail", "BBS_USERINFO.MAIL");
		addField("exp", "BBS_USERINFO.EXP");
		addField("isleader", "BBS_USERINFO.ISLEADER");
		addField("truename", "BBS_USERINFO.TRUENAME");
	}
	String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public ArticleUserinfoSearchDAO() {
		super();
	}
}
