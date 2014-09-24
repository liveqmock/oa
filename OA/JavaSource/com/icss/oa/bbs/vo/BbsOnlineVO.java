package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

public class BbsOnlineVO extends ValueObject {
	private String onlineid;
	private String userid;
	private Long begintime;
	private Long onlinetime;
	private Long lasttime;
	private String ip;
	public String getOnlineid() {
		return onlineid;
	}
	public void setOnlineid(String _onlineid) {
		onlineid = _onlineid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public Long getBegintime() {
		return begintime;
	}
	public void setBegintime(Long _begintime) {
		begintime = _begintime;
	}
	public Long getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(Long _onlinetime) {
		onlinetime = _onlinetime;
	}
	public Long getLasttime() {
		return lasttime;
	}
	public void setLasttime(Long _lasttime) {
		lasttime = _lasttime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String _ip) {
		ip = _ip;
	}
}