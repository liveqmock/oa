
package com.icss.oa.filetransfer.vo;

import com.icss.j2ee.vo.ValueObject;


public class FiletransferReadrecordBakVO extends ValueObject {
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