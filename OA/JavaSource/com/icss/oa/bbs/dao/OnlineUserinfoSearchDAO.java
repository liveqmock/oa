/*
 * Created on 2004-4-6
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
public class OnlineUserinfoSearchDAO extends SearchDAO {

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
	private String onlineid;
	private String userid;
	private Long begintime;
	private Long onlinetime;
	private Long lasttime;
	private String ip;
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
	public String getOnlineid() {
		return onlineid;
	}
	public void setOnlineid(String _onlineid) {
		firePropertyChange("onlineid", onlineid, _onlineid);
		onlineid = _onlineid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}
	public Long getBegintime() {
		return begintime;
	}
	public void setBegintime(Long _begintime) {
		firePropertyChange("begintime", begintime, _begintime);
		begintime = _begintime;
	}
	public Long getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(Long _onlinetime) {
		firePropertyChange("onlinetime", onlinetime, _onlinetime);
		onlinetime = _onlinetime;
	}
	public Long getLasttime() {
		return lasttime;
	}
	public void setLasttime(Long _lasttime) {
		firePropertyChange("lasttime", lasttime, _lasttime);
		lasttime = _lasttime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String _ip) {
		firePropertyChange("ip", ip, _ip);
		ip = _ip;
	}
	protected void setupFields() throws DAOException {
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
		addField("onlineid", "BBS_ONLINE.ONLINEID");
		addField("userid", "BBS_ONLINE.USERID");
		addField("begintime", "BBS_ONLINE.BEGINTIME");
		addField("onlinetime", "BBS_ONLINE.ONLINETIME");
		addField("lasttime", "BBS_ONLINE.LASTTIME");
		addField("ip", "BBS_ONLINE.IP");
	}
	protected String getSearchSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME,BBS_ONLINE.ONLINEID,BBS_ONLINE.USERID,BBS_ONLINE.BEGINTIME,BBS_ONLINE.ONLINETIME,BBS_ONLINE.LASTTIME,BBS_ONLINE.IP ");
		sql.append("FROM ");
		sql.append("BBS_ONLINE,BBS_USERINFO ");
		sql.append(" where BBS_USERINFO.userid = BBS_ONLINE.userid ");
		sql.append(" ");
		return sql.toString();
	}
	public OnlineUserinfoSearchDAO() {
		super();
	}
}
