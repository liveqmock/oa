/*
 * Created on 2004-4-14
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
public class IntendWorkConfig {
	/**
	 * ���칤��״̬
	 */
	public static final String WORKFLAG_ALL = "0"; //ȫ��
	public static final String WORKFLAG_HASDONE = "1"; //�����
	public static final String WORKFLAG_NOTDO = "2"; //δ���

	/**
	 * ���칤������ʱ��
	 */
	public static final String AWOKEATONCE = "0";//�������� 
	/**
	 * ���ճ̰��ż��뵽���칤����
	 */
	public static final String SCHEDULE_NOTIN_INTEND = "0";//��û�м��뵽������
	public static final String SCHEDULE_IN_INTEND = "1";//�Ѿ����뵽������
	
	/**
	 * ���TOP�й�����������
	 */
	public static final String COMM_LOCAL = "1";   //��ͨ�����ڱ�ҳˢ��
	public static final String IMPORT_LOCAL ="2";  //��Ҫ�����ڱ�ҳˢ��
	public static final String COMM_NEW = "11";    //��ͨ�������´��ڴ�
	public static final String IMPORT_NEW = "12";  //��Ҫ�������´��ڴ�
}
