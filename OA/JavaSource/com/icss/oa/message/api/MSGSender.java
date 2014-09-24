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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MSGSender {
	private static String sendURL;
	private static String num_server;
	private static String num_app;
	private static String send_arg_name;
	private static boolean bInit=false;
	
	private static Hashtable xmls;
	
	public static void init(String _sendURL,String _num_server,String _num_app,String _send_arg_name){
		sendURL=_sendURL;
		num_server=_num_server;
		num_app=_num_app;
		send_arg_name=_send_arg_name;
		xmls=new Hashtable();
		bInit=true;
	}
	public static Long send(String Content,List phoneList,String msgnum){
		Long currentTime=new Long(System.currentTimeMillis());
		if(!bInit){
			return new Long("-1");
		}
		else{
			InsertXml(createXml(Content,phoneList,currentTime,msgnum),msgnum);
			try{
				URL url=new URL(sendURL+"?"+getSendArgName()+"="+msgnum);
				InputStream in=url.openConnection().getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				StringBuffer resStr=new StringBuffer();
				String str;
				while((str = br.readLine())!=null){
					resStr.append(str);  
				}
				if(getResult(resStr.toString())){
					return currentTime;
				}
				else{
					return new Long("-1");
				}
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("无法发送短信："+getXml(msgnum));
				return new Long("-1");
			}
		}
	}
	public static String getXml(String key){
		return (String)xmls.remove(key);
	}
	public static String getSendArgName(){
		return send_arg_name;
	}
	//TODO 根据参数内容生成短信xml并保存在String中返回
	private static String createXml(String Content,List phoneList,Long sendTime,String msgnum){
		StringBuffer xml=new StringBuffer();
		xml.append("Content="+Content+"\n");
		xml.append("SendCode="+num_server+num_app+num_app+msgnum+"\n");
		xml.append("sendTime="+sendTime+"\n");
		if(phoneList==null){
			return null;
		}
		else{
			Iterator it=phoneList.iterator();
			while(it.hasNext()){
				xml.append((String)it.next()+"\n");
			}
			xml.append("end");
			System.out.println(xml.toString());
			return xml.toString();
		}
	}
	private static void InsertXml(String xml,String key){
		xmls.put(key,xml);
	}
	//TODO 解析resStr中的xml获得短信发送结果
	private static boolean getResult(String resStr){
		return true;
	}
}
