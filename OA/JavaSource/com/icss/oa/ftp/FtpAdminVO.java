package com.icss.oa.ftp;

import com.icss.j2ee.vo.ValueObject;

public class FtpAdminVO extends ValueObject{
	private Integer id;
	private String folder_name;
	private String folder_path;
	private String owener;
	private String org;
	
	public String getFolder_name() {
		return folder_name;
	}
	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}
	public String getFolder_path() {
		return folder_path;
	}
	public void setFolder_path(String folder_path) {
		this.folder_path = folder_path;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOwener() {
		return owener;
	}
	public void setOwener(String owener) {
		this.owener = owener;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	
	
}
