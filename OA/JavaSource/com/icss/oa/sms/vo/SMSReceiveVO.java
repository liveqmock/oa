package com.icss.oa.sms.vo;

import com.icss.j2ee.vo.ValueObject;

public class SMSReceiveVO extends ValueObject {

	private Integer id;
	private String fromNo;
	private String toNo;
	private String receiver;
	private String cotent;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFromNo() {
		return fromNo;
	}
	public void setFromNo(String fromNo) {
		this.fromNo = fromNo;
	}
	public String getToNo() {
		return toNo;
	}
	public void setToNo(String toNo) {
		this.toNo = toNo;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCotent() {
		return cotent;
	}
	public void setCotent(String cotent) {
		this.cotent = cotent;
	}
	
}