/*
 * �������� 2005-8-17
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
 * ֪ͨ������Ϣ����ʵ����
 * 
 * @author YANGYANG
 * 2005-8-17 13:53:23
 */
public class IntendWorkMessageProcesser extends MessageProcesser {
	
	private Log log;
	
	private String ipAddress = "";
	
	public IntendWorkMessageProcesser(){
		//��־����
		LogFactory factory = new FileLogFactory("NotificationBus");
		//��־����
		log = factory.newInstance(this.getClass());
		//��ȡ��ǰ��������IP��ַ
		try {
			InetAddress address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �����첽��Ϣ
	 */
	public void processMessage(Message message) {

		if (message instanceof IntendWorkMessage) {
			
			log.debug( "[" + ipAddress + "]�������յ�IntendWorkMessage��Ϣ����Ϣ����Ϊ��" + message.getTypeName() + " ("+message.getType()+")" );
			
			//ȡ����Ϣ����
			int type = message.getType();
			
			if( Message.INSERT == type || Message.UPDATE == type || Message.DELETE == type ){
				
				//��ִ���½����޸ġ�ɾ��ʱ֪ͨ��������������������
				IntendWorkCache.getInstance().clearCache();
				
			}
			
		}
		

	}
	

}


