/*
 * 创建日期 2005-8-17
 */
package com.icss.common.message;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.icss.common.config.messageprocesser.MessageProcesserConfigManager;
import com.icss.common.config.messageprocesser.MessageProcesserEntity;
import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;


/**
 * 通用消息处理类
 * 
 * @author YANGYANG
 * 2005-8-17 13:48:53
 */
public class MessageProcesser {
	
	private static MessageProcesser processer;
	
	public synchronized static MessageProcesser getInstance(){
		if(processer==null){
			processer = new MessageProcesser();
		}
		return processer;
	}

	
	private Log log;
	
	public MessageProcesser(){
		//日志工厂
		LogFactory factory = new FileLogFactory("NotificationBus");
		//日志对象
		log = factory.newInstance(this.getClass());
	}
	
	
	/**
	 * 处理消息的方法
	 * @param message
	 */
	public void processMessage(Message message){
		
		if(message==null){
			return;
		}
		
		try {
			Map map = MessageProcesserConfigManager.getInstance().getMessageProcesserEntitys();
			
			Iterator itor = map.keySet().iterator();
			while(itor.hasNext()){
				
				//消息类的全限定名称				
				String messageClassName = (String)itor.next();
				
				//消息处理类的全限定名称
				MessageProcesserEntity entity = (MessageProcesserEntity)map.get(messageClassName);
				//消息处理类的全限定名称
				String messageProcesserClassName = entity.getMessageProcesserName();				
				
				//log.debug("消息处理配置条目信息：messageClassName = " + messageClassName);
				//log.debug("消息处理配置条目信息：messageProcesserClassName = " + messageProcesserClassName);
				
				if(message.getClass().getName().equals(messageClassName)){
					//取得消息处理类的名称
					try {
						//取得消息处理类的类对象
						Class messageProcesserClass = Class.forName(messageProcesserClassName);
						
						if(MessageProcesser.class.isAssignableFrom(messageProcesserClass)){
							
							log.debug("获取消息处理实现类！" + messageProcesserClassName);
							
							//获取消息处理对象
							MessageProcesser processer = (MessageProcesser)messageProcesserClass.newInstance();	
							//处理消息
							processer.processMessage(message);
							
						}
						
					} catch (ClassNotFoundException e) {
						//未找到类异常
						e.printStackTrace();
						log.error("消息处理异常！ClassNotFoundException" + e.getMessage(),e);
						
					} catch (InstantiationException e) {
						//初始化异常
						e.printStackTrace();
						log.error("消息处理异常！InstantiationException" + e.getMessage(),e);
						
					} catch (IllegalAccessException e) {
						//非法访问异常
						e.printStackTrace();
						log.error("消息处理异常！IllegalAccessException" + e.getMessage(),e);
						
					}
					
				}

				
			}
			

		} catch (IOException e) {
			e.printStackTrace();
			log.error("MessageProcesserConfigManager获取配置文件信息时出现异常！" + e.getMessage(),e);
		}
		
	}

}

