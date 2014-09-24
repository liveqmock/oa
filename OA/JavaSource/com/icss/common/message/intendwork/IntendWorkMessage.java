/*
 * �������� 2005-8-17
 */
package com.icss.common.message.intendwork;



import java.io.Serializable;

import com.icss.common.message.Message;

/**
 * ֪ͨ���ߵ���Ϣ����
 * 
 * @author YANGYANG
 * 2005-8-17 13:54:24
 */
public class IntendWorkMessage implements Message{
	
	//��Ϣ����
	private int type;
	//�����л��������ڱ�����Ϣ���ݣ���ѡ�ֻ������Ҫ����Ϣ���󴫵����ݵ�ʱ������ã�
	private Serializable serialiable;
	
	/**
	 * @param type	��Ϣ����
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
	 * ȡ����Ϣ���͵��ַ�������
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
	 * ������Ϣ����
	 * @see com.icss.common.message.Message#setContent(java.lang.Object)
	 */
	public void setContent(Serializable serialiable) {
		this.serialiable = serialiable;
	}

	/*
	 * ��ȡ��Ϣ����
	 * @see com.icss.common.message.Message#getContent()
	 */
	public Serializable getContent() {
		return this.serialiable;
	}


	
	
	
}
