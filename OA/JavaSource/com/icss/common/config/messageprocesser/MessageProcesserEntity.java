/*
 * 创建日期 2005-8-17
 */
package com.icss.common.config.messageprocesser;

/**
 * @author YANGYANG
 * 2005-8-17 17:05:12
 */
public class MessageProcesserEntity {
	
	//消息类全限定名
	private String messageName;
	//消息处理类全限定名
	private String messageProcesserName;
	
	


	/**
	 * @return
	 */
	public String getMessageName() {
		return messageName;
	}

	/**
	 * @return
	 */
	public String getMessageProcesserName() {
		return messageProcesserName;
	}

	/**
	 * @param string
	 */
	public void setMessageName(String string) {
		messageName = string;
	}

	/**
	 * @param string
	 */
	public void setMessageProcesserName(String string) {
		messageProcesserName = string;
	}

}



