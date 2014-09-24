/*
 * Created on 2004-12-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FiletransferCardrelationVO extends ValueObject {
	String sendpersonuuid;
	String recepersonuuid;
	Integer cardid;
	String sendtime;
	String isnew;
	public String getSendpersonuuid() {
		return sendpersonuuid;
	}
	public void setSendpersonuuid(String _sendpersonuuid) {
		sendpersonuuid = _sendpersonuuid;
	}
	public String getRecepersonuuid() {
		return recepersonuuid;
	}
	public void setRecepersonuuid(String _recepersonuuid) {
		recepersonuuid = _recepersonuuid;
	}
	public Integer getCardid() {
		return cardid;
	}
	public void setCardid(Integer _cardid) {
		cardid = _cardid;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String _sendtime) {
		sendtime = _sendtime;
	}
	public String getIsnew() {
		return isnew;
	}
	public void setIsnew(String _isnew) {
		isnew = _isnew;
	}
}