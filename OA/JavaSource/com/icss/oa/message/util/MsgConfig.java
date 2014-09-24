/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.icss.oa.util.StringUtility;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MsgConfig {
	//���ŷ�������
	public static String MSG_URL_SEND;
	//���Ž�������
	public static String MSG_URL_RECEIVE;
	//�����ط���
	public static String MSG_NUM_SERVER;
	//����Ӧ�ñ�ʶ��
	public static String MSG_NUM_APP;
	//���ű�ʶλ��
	public static int MSG_NUM_LENGTH;
	//���ŷ��ͱ�ʶ������
	public static String MSG_SENDARGNAME;
	//���Ž��ձ�ʶ������
	public static String MSG_RECEIVEARGNAME;
	//�������ݸ�Ҫ����
	public static int MSG_CONTENTLENGTH;
	
	public static void Init(){
		MSG_URL_SEND=getString("MSG_URL_SEND");
		MSG_URL_RECEIVE=getString("MSG_URL_RECEIVE");
		MSG_NUM_SERVER=getString("MSG_NUM_SERVER");
		MSG_NUM_APP=getString("MSG_NUM_APP");
		try{
			MSG_CONTENTLENGTH=new Integer(getString("MSG_CONTENTLENGTH")).intValue();
		}
		catch(Exception e){
			e.printStackTrace();
			MSG_CONTENTLENGTH=15;
		}
		try{
			MSG_NUM_LENGTH=new Integer(getString("MSG_NUM_LENGTH")).intValue();
		}
		catch(Exception e){
			e.printStackTrace();
			MSG_NUM_LENGTH=4;
		}
		MSG_SENDARGNAME=getString("MSG_SENDARGNAME");
		MSG_RECEIVEARGNAME=getString("MSG_RECEIVEARGNAME");
	}
	private static final String CONFIG = "/WEB-INF/message";
	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(CONFIG);
	/**
	 * ��ȡĬ�ϵ�config.properties�ļ�
	 * @param key 
	 * @return value
	 */
	private static String getString(String key) {
		try {
			return StringUtility.toChinese(RESOURCE_BUNDLE.getString(key));
		} catch (MissingResourceException e) {
			return StringUtility.toChinese('!' + key + '!');
		}
	}
}
