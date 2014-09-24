/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.mail.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.icss.oa.config.FileTransferConfig;

/**
 * @author Administrator
 *
 * 统计上传文件信息，上传文件集合
 */
public class SendFileBean {
	private ArrayList attachFile;
	private long totleFilesize;
	private String sendto;
	private String sendcc;
	private String sendbcc;
	private String topic;
	private String content;
	private String type;
	private String mailName;
	private String folder;
	private String mailtype;
	private String isRe;     //是否回执
	private String isSent;   //是否保存到发件箱


	/**
	 * 
	 */
	private SendFileBean() {
		attachFile = new ArrayList();
		totleFilesize = 0;
		sendto = "";
		sendcc = "";
		sendbcc = "";
		topic = "";
		content = "";
		type = FileTransferConfig.FILETYPE_ARCHIVES;
		mailName = "";
		
	}

	/**
	 * @param attachFileBean
	 */
	public void addAttach(AttachFileBean attachFileBean) {
		attachFile.add(attachFileBean);
		totleFilesize += attachFileBean.getFilesize();
	}

	/**
	 * @return
	 */
	public int filenumber() {
		return attachFile.size();
	}

	/**
	 * @param index
	 * @return
	 */
	public AttachFileBean getAttachFile(int index) {
		return (AttachFileBean) attachFile.get(index);
	}

	/**
	 * @param index
	 */
	public void removeAttach(int index) {
		totleFilesize -= getAttachFile(index).getFilesize();
		attachFile.remove(index);
	}

	/**
	 * @return
	 */
	public long getTotleFilesize() {
		return totleFilesize;
	}

	/**
	 * @param f
	 */
	public void setTotleFilesize(long f) {
		totleFilesize = f;
	}
	/**
	 * 从session中取得实例
	 * @param request
	 * @return
	 */
	public static SendFileBean getInstanceFromSession(HttpSession session) {
		SendFileBean sendFileBean = (SendFileBean) session.getAttribute("attachFile");
		if (sendFileBean == null) {
			sendFileBean = new SendFileBean();
		}
		return sendFileBean;
	}
	/**
	 * 存入session
	 * @param session
	 * @param attachFile
	 */
	public static void saveToSession(
		HttpSession session,
		SendFileBean sendFileBean) {
		session.setAttribute("attachFile", sendFileBean);
	}

	/**
	 * @param session
	 */
	public static void removeSession(HttpSession session) {
		session.removeAttribute("attachFile");
	}
	/**
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return
	 */
	public String getSendto() {
		return sendto;
	}
	
	/**
	 * @return
	 */
	public String getSendcc() {
		return sendcc;
	}
	
	/**
	 * @return
	 */
	public String getSendbcc() {
		return sendbcc;
	}

	/**
	 * @return
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param string
	 */
	public void setContent(String string) {
		content = string;
	}

	/**
	 * @param string
	 */
	public void setSendto(String string) {
		sendto = string;
	}
	
	/**
	 * @param string
	 */
	public void setSendcc(String string) {
		sendcc = string;
	}
	
	/**
	 * @param string
	 */
	public void setSendbcc(String string) {
		sendbcc = string;
	}

	/**
	 * @param string
	 */
	public void setTopic(String string) {
		topic = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	/**
	 * @return
	 */
	public String getMailName() {
		return mailName;
	}

	/**
	 * @param string
	 */
	public void setMailName(String string) {
		mailName = string;
	}
	
	/**
	 * @return
	 */
	public String getfolder() {
		return folder;
	}

	/**
	 * @param string
	 */
	public void setfolder(String string) {
		folder = string;
	}
	
	/**
	 * @return
	 */
	public String getisRe() {
		return isRe;
	}

	/**
	 * @param string
	 */
	public void setisRe(String string) {
		isRe = string;
	}
	
	/**
	 * @return
	 */
	public String getisSent() {
		return isSent;
	}

	/**
	 * @param string
	 */
	public void setisSent(String string) {
		isSent = string;
	}
	
	/**
	 * @return
	 */
	public String getmailtype() {
		return mailtype;
	}

	/**
	 * @param string
	 */
	public void setmailtype(String string) {
		mailtype = string;
	}

}
