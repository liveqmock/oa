package com.icss.oa.ftp;

import com.icss.j2ee.vo.ValueObject;

public class FtpUserRightVO extends ValueObject{
	private String uname;
	private String folder;
	private String right;
	private String org;
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

}
