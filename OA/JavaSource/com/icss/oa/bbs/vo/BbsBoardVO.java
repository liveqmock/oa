/*
 * Created on 2004-12-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BbsBoardVO extends ValueObject {
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
		boardid = _boardid;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer _areaid) {
		areaid = _areaid;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String _boardname) {
		boardname = _boardname;
	}
	public Long getCreattime() {
		return creattime;
	}
	public void setCreattime(Long _creattime) {
		creattime = _creattime;
	}
	public Integer getArticlenum() {
		return articlenum;
	}
	public void setArticlenum(Integer _articlenum) {
		articlenum = _articlenum;
	}
	public Integer getTopicnum() {
		return topicnum;
	}
	public void setTopicnum(Integer _topicnum) {
		topicnum = _topicnum;
	}
	public String getBoarddes() {
		return boarddes;
	}
	public void setBoarddes(String _boarddes) {
		boarddes = _boarddes;
	}
	public Long getLasttime() {
		return lasttime;
	}
	public void setLasttime(Long _lasttime) {
		lasttime = _lasttime;
	}
	public Integer getLastarticleid() {
		return lastarticleid;
	}
	public void setLastarticleid(Integer _lastarticleid) {
		lastarticleid = _lastarticleid;
	}
	public String getLastid() {
		return lastid;
	}
	public void setLastid(String _lastid) {
		lastid = _lastid;
	}
	public String getIslimited() {
		return islimited;
	}
	public void setIslimited(String _islimited) {
		islimited = _islimited;
	}
	public String getIsaudit() {
		return isaudit;
	}
	public void setIsaudit(String _isaudit) {
		isaudit = _isaudit;
	}
	public String getIswork() {
		return iswork;
	}
	public void setIswork(String _iswork) {
		iswork = _iswork;
	}
	public String getIsview() {
		return isview;
	}
	public void setIsview(String _isview) {
		isview = _isview;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String _permit) {
		permit = _permit;
	}
	public String getLastarticlename() {
		return lastarticlename;
	}
	public void setLastarticlename(String _lastarticlename) {
		lastarticlename = _lastarticlename;
	}
	public String getLastusername() {
		return lastusername;
	}
	public void setLastusername(String _lastusername) {
		lastusername = _lastusername;
	}
	public Integer getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(Integer _noticeid) {
		noticeid = _noticeid;
	}
	public String getApplyflag() {
		return applyflag;
	}
	public void setApplyflag(String _applyflag) {
		applyflag = _applyflag;
	}
	public String getApplypersonuuid() {
		return applypersonuuid;
	}
	public void setApplypersonuuid(String _applypersonuuid) {
		applypersonuuid = _applypersonuuid;
	}
}