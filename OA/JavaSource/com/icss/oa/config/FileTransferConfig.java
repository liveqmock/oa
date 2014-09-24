/*
 * Created on 2004-4-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.config;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FileTransferConfig {
	
	
	/**
	 * 文件传递文件类型
	 */
	public static final String FILETYPE_ARCHIVES = "archives"; //公文
	public static final String FILETYPE_PRIVATE = "private";    //私文
	
	
	/**
	 * 当所发邮件大小大于用户邮件剩余空间大小时，用户选择的处理方式
	 */
	public static final String TOINTEND = "1"; 
	public static final String ROLLDELETE = "2";
	
	
	/**
	 * 发送邮件时是发送还是保存到草稿箱（后两种为需要回执）
	 * 
	 *  0表示保存到草稿箱
	 *	1表示发送邮件
	 *	2表示保存草稿箱，并做回执标记
	 *	3表示发送邮件并需要回执，
	 *	5表示发送邮件并保存发件箱，
	 *	7表示发送邮件，需要回执，同时保存到发件箱 
	 *
	 *	请不要使用这以外的其它数字,以免发生不正确的结果
	 * 
	 */
	public static final int TO_DRAFT = 0;
	public static final int ONLY_SEND = 1;
	public static final int DRAFT_RE = 2;
	public static final int SEND_RE = 3;
	public static final int TO_SEND = 5;
	public static final int TO_SEND_RE = 7;
	
	
	/**
	 * 邮件文件名格式
	 */
	public static final String RECE_FLAG = "i";
	public static final String DRA_FLAG = "d";
	public static final String SENT_FLAG = "s";
	public static final String JUNK_FLAG = "t";
	public static final String PRIV_FLAG = "o";
	
	
	/**
	 * 保存的邮件是否已被接收人读过
	 */
	public static final String READ = "1";
	public static final String NOREAD = "0";
	
	
	/**
	 * 邮件是否已读
	 */
	public static final String NEW_FLAG = "n";
	public static final String OLD_FLAG = "o";

	public static final String FILETRANSFER_PROPERTIES = "/WEB-INF/filetransfer.properties";//properties文件地址
	
}
