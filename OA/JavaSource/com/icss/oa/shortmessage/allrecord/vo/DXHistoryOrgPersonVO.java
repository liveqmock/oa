/*
 * Created on 2004-5-19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.allrecord.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DXHistoryOrgPersonVO extends ValueObject {
	Integer shId;
	String senderId;
	String shContent;
	Long shDate;
	String depId;
	String receiverId;
	String senderCnname;
	String orguuid;
	String cnname;
	String personuuid;
	String mobile;
	public void setShId(Integer _shId) {
		shId = _shId;
	}
	public Integer getShId() {
		return shId;
	}
	public void setSenderId(String _senderId) {
		senderId = _senderId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setShContent(String _shContent) {
		shContent = _shContent;
	}
	public String getShContent() {
		return shContent;
	}
	public void setShDate(Long _shDate) {
		shDate = _shDate;
	}
	public Long getShDate() {
		return shDate;
	}
	public void setDepId(String _depId) {
		depId = _depId;
	}
	public String getDepId() {
		return depId;
	}
	public void setReceiverId(String _receiverId) {
		receiverId = _receiverId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setSenderCnname(String _senderCnname) {
		senderCnname = _senderCnname;
	}
	public String getSenderCnname() {
		return senderCnname;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	public String getCnname() {
		return cnname;
	}
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setMobile(String _mobile) {
		mobile = _mobile;
	}
	public String getMobile() {
		return mobile;
	}
}
