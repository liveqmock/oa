/*
 * 创建日期 2005-8-17
 */
package com.icss.common.message.intendwork;



import java.io.Serializable;

import com.icss.common.message.Message;

/**
 * 通知总线的消息对象
 * 
 * @author YANGYANG
 * 2005-8-17 13:54:24
 */
public class IntendWorkMessage implements Message{
	
	//消息类型
	private int type;
	//可序列化对象，用于保存消息内容（可选项，只有在需要由消息对象传递数据的时候才设置）
	private Serializable serialiable;
	
	/**
	 * @param type	消息类型
	 */
	public IntendWorkMessage(int type){
		this.type = type;
	}

	/*
	 * @see com.icss.common.message.Message#setType(int)
	 */
	public void setType(int type) {
		this.type = type;
	}

	/* 
	 * @see com.icss.common.message.Message#getType()
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * 取得消息类型的字符串名称
	 */
	public String getTypeName(){
		
		String name = "";
		
		if( (Message.SELECT & type) == type){
			name += "SELECT|";
		}
		if( (Message.INSERT & type) == type ){
			name += "INSERT|";
		}
		if( (Message.UPDATE & type) == type){
			name += "UPDATE|";
		}
		if( (Message.DELETE & type) == type ){
			name += "DELETE|";
		}		
		
		if(name.indexOf("|")>0){
			name = name.substring(0,name.lastIndexOf("|"));
		}
		
		return name;

	}


	/*
	 * 设置消息内容
	 * @see com.icss.common.message.Message#setContent(java.lang.Object)
	 */
	public void setContent(Serializable serialiable) {
		this.serialiable = serialiable;
	}

	/*
	 * 获取消息内容
	 * @see com.icss.common.message.Message#getContent()
	 */
	public Serializable getContent() {
		return this.serialiable;
	}


	
	
	
}
