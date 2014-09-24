/*
 * Created on 2004-4-3
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
public class BbsUserinfoDAO extends DAO {

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
	private Long lasttime;
	private String lastip; 
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
	public Long getLasttime() {
		return lasttime;
	}
	public void setLasttime(Long _lasttime) {
		firePropertyChange("lasttime", lasttime, _lasttime);
		lasttime = _lasttime;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String _lastip) {
		firePropertyChange("lastip", lastip, _lastip);
		lastip = _lastip;
	}
	protected void setupFields() throws DAOException {
		addField("userid", "USERID");
		addField("onlineid", "ONLINEID");
		addField("bmId", "BM_ID");
		addField("bbaId", "BBA_ID");
		addField("oicq", "OICQ");
		addField("userpic", "USERPIC");
		addField("underwrite", "UNDERWRITE");
		addField("pubnum", "PUBNUM");
		addField("accessnum", "ACCESSNUM");
		addField("userlevel", "USERLEVEL");
		addField("regdate", "REGDATE");
		addField("onLine", "ON_LINE");
		addField("username", "USERNAME");
		addField("homepage", "HOMEPAGE");
		addField("mail", "MAIL");
		addField("exp", "EXP");
		addField("isleader", "ISLEADER");
		addField("truename", "TRUENAME");
		addField("lasttime", "LASTTIME");
		addField("lastip", "LASTIP");
		setTableName("BBS_USERINFO");
		addKey("USERID");
	}
	public BbsUserinfoDAO(Connection conn) {
		super(conn);
	}
	public BbsUserinfoDAO() {
		super();
	}
}
