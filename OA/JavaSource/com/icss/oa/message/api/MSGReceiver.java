/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.icss.oa.message.handler.MsgHandler;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MSGReceiver {
	private static String receiveURL;
	private static String receive_arg_name;
	private static boolean bInit=false;
	
	public static void init(String _receiveURL,String _receive_arg_name){
		receiveURL=_receiveURL;
		receive_arg_name=_receive_arg_name;
		bInit=true;
	}
	public static MsgContent receive(String argvalue){
		if(!bInit){
			return null;
		}
		else{
			try{
				URL url=new URL(receiveURL+"?"+getReceiveArgName()+"="+argvalue);
				InputStream in=url.openConnection().getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				StringBuffer resStr=new StringBuffer();
				String str;
				while((str = br.readLine())!=null){
					resStr.append(str);  
				}
				return getMsgContent(resStr.toString());
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}
	public static String getReceiveArgName(){
		return receive_arg_name;
	}
	//TODO 生成收取短信结果的xml
	public static String createResXml(boolean bRes){
		if(bRes) return "true";
		else return "false";
	}
	//TODO 解析收到的短信xml获得短信内容
	private static MsgContent getMsgContent(String xml){
		return new MsgContent("13988888888",new Integer("6"),"收到了",new Long(System.currentTimeMillis()),MsgHandler.SEND_RETURN);
	}
}
