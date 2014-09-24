/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.votebbs.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;


/**
 * @author Administrator
 *
 * 统计上传文件信息，上传文件集合
 */
public class OptionUploadBean {
	private AttachFileBean attachfile;
	
	//private long totleFilesize;
	 
	 private String mainid;
	 private String opTitle;
	 private String opContext;
	 private String opOrder;
	 private	String	optionpic;
	    
	


	/**
	 * 
	 */
	private OptionUploadBean() {
		attachfile=new AttachFileBean();
		optionpic="";
		
		mainid="";
		opTitle="";
		opContext="";
		opOrder="";
		optionpic="";
		
		
	}


	
	/**
	 * 从session中取得实例
	 * @param request
	 * @return
	 */
	public static OptionUploadBean getInstanceFromSession(HttpSession session) {
		OptionUploadBean sendFileBean = (OptionUploadBean) session.getAttribute("optionUploads");
		if (sendFileBean == null) {
			sendFileBean = new OptionUploadBean();
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
		OptionUploadBean sendFileBean) {
		session.setAttribute("optionUploads", sendFileBean);
	}

	/**
	 * @param session
	 */
	public static void removeSession(HttpSession session) {
		session.removeAttribute("optionUploads");
	}

	public AttachFileBean getAttachFile() {
		return attachfile;
	}

	public void setAttachFile(AttachFileBean attachfile) {
		this.attachfile = attachfile;
	}

	
		
	/**
	 * @return Returns the optionpic.
	 */
	public String getOptionpic() {
		return optionpic;
	}



	/**
	 * @param optionpic The optionpic to set.
	 */
	public void setOptionpic(String optionpic) {
		this.optionpic = optionpic;
	}



	/**
	 * @return Returns the mainid.
	 */
	public String getMainid() {
		return mainid;
	}



	/**
	 * @param mainid The mainid to set.
	 */
	public void setMainid(String mainid) {
		this.mainid = mainid;
	}



	/**
	 * @return Returns the opContext.
	 */
	public String getOpContext() {
		return opContext;
	}



	/**
	 * @param opContext The opContext to set.
	 */
	public void setOpContext(String opContext) {
		this.opContext = opContext;
	}



	/**
	 * @return Returns the opOrder.
	 */
	public String getOpOrder() {
		return opOrder;
	}



	/**
	 * @param opOrder The opOrder to set.
	 */
	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}



	/**
	 * @return Returns the opTitle.
	 */
	public String getOpTitle() {
		return opTitle;
	}



	/**
	 * @param opTitle The opTitle to set.
	 */
	public void setOpTitle(String opTitle) {
		this.opTitle = opTitle;
	}



	



}
