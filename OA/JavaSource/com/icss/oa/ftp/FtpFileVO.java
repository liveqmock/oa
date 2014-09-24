package com.icss.oa.ftp;

import com.icss.j2ee.vo.ValueObject;

public class FtpFileVO extends ValueObject{
	private Integer id;
	private String filename;
	private String folder;
	private String depuuid;
	private String createruuid;
	private String createtime;
	private String downloadpath;
	
	public String getCreateruuid() {
		return createruuid;
	}
	public void setCreateruuid(String createruuid) {
		this.createruuid = createruuid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getDepuuid() {
		return depuuid;
	}
	public void setDepuuid(String depuuid) {
		this.depuuid = depuuid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDownload() {
		return downloadpath;
	}
	public void setDownload(String downloadpath) {
		this.downloadpath = downloadpath;
	}
}
