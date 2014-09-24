package com.icss.oa.sms.vo;

import com.icss.j2ee.vo.ValueObject;

public class SMSHistoryVO extends ValueObject {
	private Integer id;
	private String senderuuid;
	private String sendername;
	private String time;
	private String content;
	private String receivername;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public String getSenderuuid() {
		return senderuuid;
	}
	public void setSenderuuid(String _senderuuid) {
		senderuuid = _senderuuid;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String _sendername) {
		sendername = _sendername;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String _time) {
		time = _time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		content = _content;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String _receivername) {
		receivername = _receivername;
	}
	
}