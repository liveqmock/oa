/*
 * �������� 2005-8-17
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
 * ͨ����Ϣ������
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
		//��־����
		LogFactory factory = new FileLogFactory("NotificationBus");
		//��־����
		log = factory.newInstance(this.getClass());
	}
	
	
	/**
	 * ������Ϣ�ķ���
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
				
				//��Ϣ���ȫ�޶�����				
				String messageClassName = (String)itor.next();
				
				//��Ϣ�������ȫ�޶�����
				MessageProcesserEntity entity = (MessageProcesserEntity)map.get(messageClassName);
				//��Ϣ�������ȫ�޶�����
				String messageProcesserClassName = entity.getMessageProcesserName();				
				
				//log.debug("��Ϣ����������Ŀ��Ϣ��messageClassName = " + messageClassName);
				//log.debug("��Ϣ����������Ŀ��Ϣ��messageProcesserClassName = " + messageProcesserClassName);
				
				if(message.getClass().getName().equals(messageClassName)){
					//ȡ����Ϣ�����������
					try {
						//ȡ����Ϣ������������
						Class messageProcesserClass = Class.forName(messageProcesserClassName);
						
						if(MessageProcesser.class.isAssignableFrom(messageProcesserClass)){
							
							log.debug("��ȡ��Ϣ����ʵ���࣡" + messageProcesserClassName);
							
							//��ȡ��Ϣ�������
							MessageProcesser processer = (MessageProcesser)messageProcesserClass.newInstance();	
							//������Ϣ
							processer.processMessage(message);
							
						}
						
					} catch (ClassNotFoundException e) {
						//δ�ҵ����쳣
						e.printStackTrace();
						log.error("��Ϣ�����쳣��ClassNotFoundException" + e.getMessage(),e);
						
					} catch (InstantiationException e) {
						//��ʼ���쳣
						e.printStackTrace();
						log.error("��Ϣ�����쳣��InstantiationException" + e.getMessage(),e);
						
					} catch (IllegalAccessException e) {
						//�Ƿ������쳣
						e.printStackTrace();
						log.error("��Ϣ�����쳣��IllegalAccessException" + e.getMessage(),e);
						
					}
					
				}

				
			}
			

		} catch (IOException e) {
			e.printStackTrace();
			log.error("MessageProcesserConfigManager��ȡ�����ļ���Ϣʱ�����쳣��" + e.getMessage(),e);
		}
		
	}

}

