/*
 * 创建日期 2005-8-17
 */
package com.icss.common.message;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jgroups.Address;
import org.jgroups.blocks.NotificationBus;

import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;

/**
 * 通用通知总线的消息接收类
 * 
 * @author YANGYANG
 * 2005-8-17 13:21:54
 */
public class MessageConsumer implements NotificationBus.Consumer{
	
	private Log log;
	
	private String ipAddress = "";
	
	public MessageConsumer(){
		//日志工厂
		LogFactory factory = new FileLogFactory("NotificationBus");
		//日志对象
		this.log = factory.newInstance(this.getClass());

		//获取当前服务器的IP地址
		try {
			InetAddress address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
	
	
	/* 
	 * 处理通知总线发出的可序列化对象
	 * @see org.jgroups.blocks.NotificationBus.Consumer#handleNotification(java.io.Serializable)
	 */
	public void handleNotification(Serializable message) {
		
		if(message instanceof Message ){
			
			log.debug("[" + ipAddress + "]服务器收到Message消息！");
			
			MessageProcesser.getInstance().processMessage((Message)message);
		}
		
	}


	/* 
	 * @see org.jgroups.blocks.NotificationBus.Consumer#getCache()
	 */
	public Serializable getCache() {
		return null;
	}

	/* 
	 * @see org.jgroups.blocks.NotificationBus.Consumer#memberJoined(org.jgroups.Address)
	 */
	public void memberJoined(Address address) {
		
		log.debug( "[" + ipAddress + "]服务器成员加入 Address = " + address);
	}

	/*
	 * @see org.jgroups.blocks.NotificationBus.Consumer#memberLeft(org.jgroups.Address)
	 */
	public void memberLeft(Address address) {
		
		log.debug( "[" + ipAddress + "]服务器成员退出 Address = " + address);
		
	}


}




