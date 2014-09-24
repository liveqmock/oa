/*
 * �������� 2005-8-17
 */
package com.icss.common.message;

import java.io.Serializable;

/**
 * @author YANGYANG
 * 2005-8-17 13:19:42
 */
public interface Message extends Serializable{
	
	//��Ϣ����
	public static final int SELECT = 1;
	public static final int INSERT = 2;
	public static final int UPDATE = 4;
	public static final int DELETE = 8;
	
	//������Ϣ����
	public void setType(int type);
	//��ȡ��Ϣ����
	public int getType();
	//ȡ����Ϣ���͵�����
	public String getTypeName();
	
	//���ÿ����л�����Ϣ����
	public void setContent(Serializable serialiable);
	//ȡ�ÿ����л�����Ϣ����
	public Serializable getContent();

	
}



