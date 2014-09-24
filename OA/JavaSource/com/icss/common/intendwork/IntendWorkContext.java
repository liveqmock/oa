/*
 * 创建日期 2005-8-19
 */
package com.icss.common.intendwork;

import java.util.Hashtable;
import java.util.Map;

/**
 * 待办工作上下文对象
 * @author YANGYANG
 * 2005-8-19 11:38:37
 */
public class IntendWorkContext {
	
	//上下文属性名：用户UUID
	public static final String PERSONUUID = "PERSONUUID";
	//上下文属性名：子系统ID
	public static final String SYSID = "SYSID";
	
	private Map map;
	
	public IntendWorkContext(){
		map = new Hashtable();
	}
	
	/**
	 * 设置属性值
	 * @param attributeName	属性名称
	 * @param value			属性值
	 */
	public void setAttribute(String attributeName, Object value){
		map.put(attributeName,value);
	}
	
	/**
	 * 获取属性值
	 * @param attributeName	属性名称
	 * @return
	 */
	public Object getAttribute(String attributeName){
		return map.get(attributeName);
	}
	
}
