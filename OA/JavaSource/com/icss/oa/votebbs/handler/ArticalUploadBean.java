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
public class ArticalUploadBean {
	private ArrayList attachFile;
	//private long totleFilesize;
	 private String title;
	    private String context;
	    private String mOrder;
	    private String uploader;
	    private String uploaduuid;
	    private String publishtime;
	    private String modifytime;
	    private String type;
	    private String status;
	


	/**
	 * 
	 */
	private ArticalUploadBean() {
		attachFile = new ArrayList();
	    context="";
	    mOrder="";
	    uploader="";
	    uploaduuid="";
	    publishtime="";
	    modifytime="";
	    type="";
	    status="";
		
		
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
	public static ArticalUploadBean getInstanceFromSession(HttpSession session) {
		ArticalUploadBean sendFileBean = (ArticalUploadBean) session.getAttribute("logattachFile");
		if (sendFileBean == null) {
			sendFileBean = new ArticalUploadBean();
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
		ArticalUploadBean sendFileBean) {
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

	/**
	 * @return Returns the context.
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context The context to set.
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return Returns the modifytime.
	 */
	public String getModifytime() {
		return modifytime;
	}

	/**
	 * @param modifytime The modifytime to set.
	 */
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	/**
	 * @return Returns the mOrder.
	 */
	public String getMOrder() {
		return mOrder;
	}

	/**
	 * @param order The mOrder to set.
	 */
	public void setMOrder(String order) {
		mOrder = order;
	}

	/**
	 * @return Returns the publishtime.
	 */
	public String getPublishtime() {
		return publishtime;
	}

	/**
	 * @param publishtime The publishtime to set.
	 */
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the uploader.
	 */
	public String getUploader() {
		return uploader;
	}

	/**
	 * @param uploader The uploader to set.
	 */
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	/**
	 * @return Returns the uploaduuid.
	 */
	public String getUploaduuid() {
		return uploaduuid;
	}

	/**
	 * @param uploaduuid The uploaduuid to set.
	 */
	public void setUploaduuid(String uploaduuid) {
		this.uploaduuid = uploaduuid;
	}



	



}
