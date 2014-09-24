/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.votebbs.handler;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;

//import java.io.UnsupportedEncodingException;
//import javax.mail.internet.MimeUtility;

/**
 * @author Administrator
 *
 * 记录上传的附件的信息
 */
public class OptionPicBean {
	private String fileOriginName;
	private String uploadUrl;
	private String downloadUrl;
	private String fileMimetype;
	private long filesize;

	/**
	 * @return
	 */
	public String getFileMimetype() {
		return fileMimetype;
	}

	/**
	 * @return
	 */
	public String getFileOriginName() {
		return fileOriginName;
	}

	/**
	 * @return
	 */
	public long getFilesize() {
		return filesize;
	}

	/**
	 * @return
	 */
	public String getUploadUrl() {
		return uploadUrl;
	}
	
	/**
	 * @return
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}	

	/**
	 * @param string
	 */
	public void setFileMimetype(String string) {
		try {
			fileMimetype = MimeUtility.decodeText(string);
			fileMimetype = string;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param string
	 */
	public void setFileOriginName(String string) {
		fileOriginName = string;
	}

	/**
	 * @param string
	 */
	public void setFilesize(long string) {
		filesize = string;
	}

	/**
	 * @param string
	 */
	public void setUploadUrl(String string) {
		uploadUrl = string;
	}
	
	/**
	 * @param string
	 */
	public void setDownloadUrl(String string) {
		downloadUrl = string;
	}	

}
