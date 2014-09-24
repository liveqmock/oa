/*
 * Created on 2004-12-11
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
public class FiletransferReadrecordVO extends ValueObject {
	String readpersonuuid;
	Integer mailid;
	String isread;
	public String getReadpersonuuid() {
		return readpersonuuid;
	}
	public void setReadpersonuuid(String _readpersonuuid) {
		readpersonuuid = _readpersonuuid;
	}
	public Integer getMailid() {
		return mailid;
	}
	public void setMailid(Integer _mailid) {
		mailid = _mailid;
	}
	public String getIsread() {
		return isread;
	}
	public void setIsread(String _isread) {
		isread = _isread;
	}
}