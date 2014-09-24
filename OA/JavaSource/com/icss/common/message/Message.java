/*
 * 创建日期 2005-8-17
 */
package com.icss.common.message;

import java.io.Serializable;

/**
 * @author YANGYANG
 * 2005-8-17 13:19:42
 */
public interface Message extends Serializable{
	
	//消息类型
	public static final int SELECT = 1;
	public static final int INSERT = 2;
	public static final int UPDATE = 4;
	public static final int DELETE = 8;
	
	//设置消息类型
	public void setType(int type);
	//获取消息类型
	public int getType();
	//取得消息类型的名称
	public String getTypeName();
	
	//设置可序列化的消息内容
	public void setContent(Serializable serialiable);
	//取得可序列化的消息内容
	public Serializable getContent();

	
}



