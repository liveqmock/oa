/*
 * �������� 2005-8-17
 */
package com.icss.common.config.cache;

/**
 * ֪ͨ������Ŀ
 * 
 * @author YANGYANG
 * 2005-8-17 12:45:09
 */
public class NotificationBusEntity {
	
	//���������
	private String name;
	//���������
	private String channelProperties;
	//�Ƿ�������
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

