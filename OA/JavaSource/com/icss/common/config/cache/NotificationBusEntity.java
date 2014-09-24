/*
 * 创建日期 2005-8-17
 */
package com.icss.common.config.cache;

/**
 * 通知总线条目
 * 
 * @author YANGYANG
 * 2005-8-17 12:45:09
 */
public class NotificationBusEntity {
	
	//缓存的名称
	private String name;
	//缓存的属性
	private String channelProperties;
	//是否允许缓存
	private boolean cacheEnabled;
	
	
	
	/**
	 * @return
	 */
	public boolean isCacheEnabled() {
		return cacheEnabled;
	}

	/**
	 * @return
	 */
	public String getChannelProperties() {
		return channelProperties;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param b
	 */
	public void setCacheEnabled(boolean b) {
		cacheEnabled = b;
	}

	/**
	 * @param string
	 */
	public void setChannelProperties(String string) {
		channelProperties = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

}

