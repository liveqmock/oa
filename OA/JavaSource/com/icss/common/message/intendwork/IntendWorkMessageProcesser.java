/*
 * 创建日期 2005-8-17
 */
package com.icss.common.message.intendwork;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.icss.common.cache.intendwork.IntendWorkCache;
import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;
import com.icss.common.message.Message;
import com.icss.common.message.MessageProcesser;

/**
 * 通知总线消息处理实现类
 * 
 * @author YANGYANG
 * 2005-8-17 13:53:23
 */
public class IntendWorkMessageProcesser extends MessageProcesser {
	
	private Log log;
	
	private String ipAddress = "";
	
	public IntendWorkMessageProcesser(){
		//日志工厂
		LogFactory factory = new FileLogFactory("NotificationBus");
		//日志对象
		log = factory.newInstance(this.getClass());
		//获取当前服务器的IP地址
		try {
			InetAddress address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 处理异步消息
	 */
	public void processMessage(Message message) {

		if (message instanceof IntendWorkMessage) {
			
			log.debug( "[" + ipAddress + "]服务器收到IntendWorkMessage消息！消息类型为：" + message.getTypeName() + " ("+message.getType()+")" );
			
			//取得消息类型
			int type = message.getType();
			
			if( Message.INSERT == type || Message.UPDATE == type || Message.DELETE == type ){
				
				//在执行新建、修改、删除时通知缓存管理器清除缓存数据
				IntendWorkCache.getInstance().clearCache();
				
			}
			
		}
		

	}
	

}


