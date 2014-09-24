package com.icss.oa.workmeeting.vo;

import com.icss.j2ee.vo.ValueObject;

public class GongzuowjVO extends ValueObject {
	private Integer wrNo;
	private String title;
	private Integer qs;
	private Integer totalQs;  
	private String publicDate;
	private String edName;
	private String issueName;
	private Long inTime;
	private Integer attchId;
	private String scope1;
	private Integer classid;
	private Integer cflag;
	private Integer seqno;
	private String orguuid;
	public Integer getWrNo() {
		return wrNo;
	}
	public void setWrNo(Integer _wrNo) {
		wrNo = _wrNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		title = _title;
	}
	public Integer getQs() {
		return qs;
	}
	public void setQs(Integer _qs) {
		qs = _qs;
	}
	public Integer getTotalQs() {
		return totalQs;
	}
	public void setTotalQs(Integer _totalQs) {
		totalQs = _totalQs;
	}
	public String getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(String _publicDate) {
		publicDate = _publicDate;
	}
	public String getEdName() {
		return edName;
	}
	public void setEdName(String _edName) {
		edName = _edName;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String _issueName) {
		issueName = _issueName;
	}
	public Long getInTime() {
		return inTime;
	}
	public void setInTime(Long _inTime) {
		inTime = _inTime;
	}
	public Integer getAttchId() {
		return attchId;
	}
	public void setAttchId(Integer _attchId) {
		attchId = _attchId;
	}
	public String getScope1() {
		return scope1;
	}
	public void setScope1(String _scope1) {
		scope1 = _scope1;
	}
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer _classid) {
		classid = _classid;
	}
	public Integer getCflag() {
		return cflag;
	}
	public void setCflag(Integer _cflag) {
		cflag = _cflag;
	}
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer _seqno) {
		seqno = _seqno;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
}