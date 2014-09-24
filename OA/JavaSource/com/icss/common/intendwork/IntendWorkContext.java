/*
 * �������� 2005-8-19
 */
package com.icss.common.intendwork;

import java.util.Hashtable;
import java.util.Map;

/**
 * ���칤�������Ķ���
 * @author YANGYANG
 * 2005-8-19 11:38:37
 */
public class IntendWorkContext {
	
	//���������������û�UUID
	public static final String PERSONUUID = "PERSONUUID";
	//����������������ϵͳID
	public static final String SYSID = "SYSID";
	
	private Map map;
	
	public IntendWorkContext(){
		map = new Hashtable();
	}
	
	/**
	 * ��������ֵ
	 * @param attributeName	��������
	 * @param value			����ֵ
	 */
	public void setAttribute(String attributeName, Object value){
		map.put(attributeName,value);
	}
	
	/**
	 * ��ȡ����ֵ
	 * @param attributeName	��������
	 * @return
	 */
	public Object getAttribute(String attributeName){
		return map.get(attributeName);
	}
	
}
