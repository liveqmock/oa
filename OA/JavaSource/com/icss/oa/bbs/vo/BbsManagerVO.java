package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

public class BbsManagerVO extends ValueObject {
	private Integer bmId;
	private Integer boardid;
	private String userid;
	public Integer getBmId() {
		return bmId;
	}
	public void setBmId(Integer _bmId) {
		bmId = _bmId;
	}
	public Integer getBoardid() {
		return boardid;
	}
	public void setBoardid(Integer _boardid) {
		boardid = _boardid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
}