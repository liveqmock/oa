/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.log.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;


/**
 * @author Administrator
 *
 * 统计上传文件信息，上传文件集合
 */
public class SendFileBean {
	private ArrayList attachFile;
	//private long totleFilesize;
	private String logreason;
	private String logpheno;
	private String loganalyse;
	private String logdesc;
	private Integer sysid;
	


	/**
	 * 
	 */
	private SendFileBean() {
		attachFile = new ArrayList();
		logreason="";
		logpheno="";
		loganalyse="";
		logdesc="";
		
		
	}

	/**
	 * @param attachFileBean
	 */
	public void addAttach(AttachFileBean attachFileBean) {
		attachFile.add(attachFileBean);
		//totleFilesize += attachFileBean.getFilesize();
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
		//totleFilesize -= getAttachFile(index).getFilesize();
		attachFile.remove(index);
	}

	
	/**
	 * 从session中取得实例
	 * @param request
	 * @return
	 */
	public static SendFileBean getInstanceFromSession(HttpSession session) {
		SendFileBean sendFileBean = (SendFileBean) session.getAttribute("logattachFile");
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
		session.setAttribute("logattachFile", sendFileBean);
	}

	/**
	 * @param session
	 */
	public static void removeSession(HttpSession session) {
		session.removeAttribute("logattachFile");
	}

	public ArrayList getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(ArrayList attachFile) {
		this.attachFile = attachFile;
	}

	public String getLoganalyse() {
		return loganalyse;
	}

	public void setLoganalyse(String loganalyse) {
		this.loganalyse = loganalyse;
	}

	public String getLogdesc() {
		return logdesc;
	}

	public void setLogdesc(String logdesc) {
		this.logdesc = logdesc;
	}

	public String getLogpheno() {
		return logpheno;
	}

	public void setLogpheno(String logpheno) {
		this.logpheno = logpheno;
	}

	public String getLogreason() {
		return logreason;
	}

	public void setLogreason(String logreason) {
		this.logreason = logreason;
	}

	public Integer getSysid() {
		return sysid;
	}

	public void setSysid(Integer sysid) {
		this.sysid = sysid;
	}

	



}
