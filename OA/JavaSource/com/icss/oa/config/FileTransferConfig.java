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
	 * �ļ������ļ�����
	 */
	public static final String FILETYPE_ARCHIVES = "archives"; //����
	public static final String FILETYPE_PRIVATE = "private";    //˽��
	
	
	/**
	 * �������ʼ���С�����û��ʼ�ʣ��ռ��Сʱ���û�ѡ��Ĵ���ʽ
	 */
	public static final String TOINTEND = "1"; 
	public static final String ROLLDELETE = "2";
	
	
	/**
	 * �����ʼ�ʱ�Ƿ��ͻ��Ǳ��浽�ݸ��䣨������Ϊ��Ҫ��ִ��
	 * 
	 *  0��ʾ���浽�ݸ���
	 *	1��ʾ�����ʼ�
	 *	2��ʾ����ݸ��䣬������ִ���
	 *	3��ʾ�����ʼ�����Ҫ��ִ��
	 *	5��ʾ�����ʼ������淢���䣬
	 *	7��ʾ�����ʼ�����Ҫ��ִ��ͬʱ���浽������ 
	 *
	 *	�벻Ҫʹ�����������������,���ⷢ������ȷ�Ľ��
	 * 
	 */
	public static final int TO_DRAFT = 0;
	public static final int ONLY_SEND = 1;
	public static final int DRAFT_RE = 2;
	public static final int SEND_RE = 3;
	public static final int TO_SEND = 5;
	public static final int TO_SEND_RE = 7;
	
	
	/**
	 * �ʼ��ļ�����ʽ
	 */
	public static final String RECE_FLAG = "i";
	public static final String DRA_FLAG = "d";
	public static final String SENT_FLAG = "s";
	public static final String JUNK_FLAG = "t";
	public static final String PRIV_FLAG = "o";
	
	
	/**
	 * ������ʼ��Ƿ��ѱ������˶���
	 */
	public static final String READ = "1";
	public static final String NOREAD = "0";
	
	
	/**
	 * �ʼ��Ƿ��Ѷ�
	 */
	public static final String NEW_FLAG = "n";
	public static final String OLD_FLAG = "o";

	public static final String FILETRANSFER_PROPERTIES = "/WEB-INF/filetransfer.properties";//properties�ļ���ַ
	
}
