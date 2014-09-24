package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

public class BbsNoticeVO extends ValueObject {
	private String title;
	private Long noticedate;
	private String content;
	private Integer noticeid;
	private Integer boardid;
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		title = _title;
	}
	public Long getNoticedate() {
		return noticedate;
	}
	public void setNoticedate(Long _noticedate) {
		noticedate = _noticedate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		content = _content;
	}
	public Integer getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(Integer _noticeid) {
		noticeid = _noticeid;
	}
	public Integer getBoardid() {
		return boardid;
	}
	public void setBoardid(Integer _boardid) {
		boardid = _boardid;
	}
}