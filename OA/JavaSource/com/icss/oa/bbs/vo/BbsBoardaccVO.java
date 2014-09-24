package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

public class BbsBoardaccVO extends ValueObject {
	private Integer bbaId;
	private Integer boardid;
	private String userid;
	public Integer getBbaId() {
		return bbaId;
	}
	public void setBbaId(Integer _bbaId) {
		bbaId = _bbaId;
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