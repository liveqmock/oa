package com.icss.oa.workmeeting.vo;

import java.io.InputStream;

import com.icss.j2ee.vo.ValueObject;

public class Attachment1VO extends ValueObject { 
	private Integer attachmentid;  
	private String filename;
	private String fileextension;
	private String fullfilename;
	private Integer filesize;
	private String doctype;
	private String docno;
	private String attachmentstate;
	private String isinfolder;
	private Long uploadtime;
	private String uploadusername;
	private String converted;
	private Integer gzwjid;
	private InputStream attach;
	
	public Integer getAttachmentid() {
		return attachmentid;
	}
	public void setAttachmentid(Integer _attachmentid) {
		attachmentid = _attachmentid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String _filename) {
		filename = _filename;
	}
	public String getFileextension() {
		return fileextension;
	}
	public void setFileextension(String _fileextension) {
		fileextension = _fileextension;
	}
	public String getFullfilename() {
		return fullfilename;
	}
	public void setFullfilename(String _fullfilename) {
		fullfilename = _fullfilename;
	}
	public Integer getFilesize() {
		return filesize;
	}
	public void setFilesize(Integer _filesize) {
		filesize = _filesize;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String _doctype) {
		doctype = _doctype;
	}
	public String getDocno() {
		return docno;
	}
	public void setDocno(String _docno) {
		docno = _docno;
	}
	public String getAttachmentstate() {
		return attachmentstate;
	}
	public void setAttachmentstate(String _attachmentstate) {
		attachmentstate = _attachmentstate;
	}
	public String getIsinfolder() {
		return isinfolder;
	}
	public void setIsinfolder(String _isinfolder) {
		isinfolder = _isinfolder;
	}
	public Long getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Long _uploadtime) {
		uploadtime = _uploadtime;
	}
	public String getUploadusername() {
		return uploadusername;
	}
	public void setUploadusername(String _uploadusername) {
		uploadusername = _uploadusername;
	}
	public String getConverted() {
		return converted;
	}
	public void setConverted(String _converted) {
		converted = _converted;
	}
	public Integer getGzwjid() {
		return gzwjid;
	}
	public void setGzwjid(Integer _gzwjid) {
		gzwjid = _gzwjid;
	}
	public InputStream getAttach() {
		return attach;
	}
	public void setAttach(InputStream _attach) {
		attach = _attach;
	}
}