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
	//短信发送链接
	public static String MSG_URL_SEND;
	//短信接收链接
	public static String MSG_URL_RECEIVE;
	//短信特服号
	public static String MSG_NUM_SERVER;
	//短信应用标识号
	public static String MSG_NUM_APP;
	//短信标识位数
	public static int MSG_NUM_LENGTH;
	//短信发送标识参数名
	public static String MSG_SENDARGNAME;
	//短信接收标识参数名
	public static String MSG_RECEIVEARGNAME;
	//短信内容概要长度
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
	 * 读取默认的config.properties文件
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
