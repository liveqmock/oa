package com.icss.oa.ftp;

public class SearchResultVO {
	private String filename;
	private String content;
	private String depuuid;
	private String createruuid;
	private String createtime;
	private String download;
	private String downloadhtml;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
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
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDownloadhtml() {
		return downloadhtml;
	}
	public void setDownloadhtml(String downloadhtml) {
		this.downloadhtml = downloadhtml;
	}
	
}
