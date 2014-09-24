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
public class FiletransferWanttorecordVO extends ValueObject {
	Integer mailid;
	String recordid;
	public Integer getMailid() {
		return mailid;
	}
	public void setMailid(Integer _mailid) {
		mailid = _mailid;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String _recordid) {
		recordid = _recordid;
	}
}