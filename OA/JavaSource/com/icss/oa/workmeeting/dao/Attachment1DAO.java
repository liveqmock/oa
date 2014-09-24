/*
 * Created on 2004-12-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.workmeeting.dao;    

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Attachment1DAO extends DAO {

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
		firePropertyChange("attachmentid", attachmentid, _attachmentid);
		attachmentid = _attachmentid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String _filename) {
		firePropertyChange("filename", filename, _filename);
		filename = _filename;
	}
	public String getFileextension() {
		return fileextension;
	}
	public void setFileextension(String _fileextension) {
		firePropertyChange("fileextension", fileextension, _fileextension);
		fileextension = _fileextension;
	}
	public String getFullfilename() {
		return fullfilename;
	}
	public void setFullfilename(String _fullfilename) {
		firePropertyChange("fullfilename", fullfilename, _fullfilename);
		fullfilename = _fullfilename;
	}
	public Integer getFilesize() {
		return filesize;
	}
	public void setFilesize(Integer _filesize) {
		firePropertyChange("filesize", filesize, _filesize);
		filesize = _filesize;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String _doctype) {
		firePropertyChange("doctype", doctype, _doctype);
		doctype = _doctype;
	}
	public String getDocno() {
		return docno;
	}
	public void setDocno(String _docno) {
		firePropertyChange("docno", docno, _docno);
		docno = _docno;
	}
	public String getAttachmentstate() {
		return attachmentstate;
	}
	public void setAttachmentstate(String _attachmentstate) {
		firePropertyChange(
			"attachmentstate",
			attachmentstate,
			_attachmentstate);
		attachmentstate = _attachmentstate;
	}
	public String getIsinfolder() {
		return isinfolder;
	}
	public void setIsinfolder(String _isinfolder) {
		firePropertyChange("isinfolder", isinfolder, _isinfolder);
		isinfolder = _isinfolder;
	}
	public Long getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Long _uploadtime) {
		firePropertyChange("uploadtime", uploadtime, _uploadtime);
		uploadtime = _uploadtime;
	}
	public String getUploadusername() {
		return uploadusername;
	}
	public void setUploadusername(String _uploadusername) {
		firePropertyChange("uploadusername", uploadusername, _uploadusername);
		uploadusername = _uploadusername;
	}
	public String getConverted() {
		return converted;
	}
	public void setConverted(String _converted) {
		firePropertyChange("converted", converted, _converted);
		converted = _converted;
	}
	public Integer getGzwjid() {
		return gzwjid;
	}
	public void setGzwjid(Integer _gzwjid) {
		firePropertyChange("gzwjid", gzwjid, _gzwjid);
		gzwjid = _gzwjid;
	}
	public InputStream getAttach() {
		return attach;
	}
	public void setAttach(InputStream _attach) {
		firePropertyChange("attach", attach, _attach);
		attach = _attach;
	}
	protected void setupFields() throws DAOException {
		addField("attachmentid", "ATTACHMENTID");
		addField("filename", "FILENAME");
		addField("fileextension", "FILEEXTENSION");
		addField("fullfilename", "FULLFILENAME");
		addField("filesize", "FILESIZE");
		addField("doctype", "DOCTYPE");
		addField("docno", "DOCNO");
		addField("attachmentstate", "ATTACHMENTSTATE");
		addField("isinfolder", "ISINFOLDER");
		addField("uploadtime", "UPLOADTIME");
		addField("uploadusername", "UPLOADUSERNAME");
		addField("converted", "CONVERTED");
		addField("gzwjid", "GZWJID");
		addField("attach", "ATTACH");
		setTableName("ATTACHMENT1");
	}
	public Attachment1DAO(Connection conn) {
		super(conn);
	}
	public Attachment1DAO() {
		super();
	}
}
