/*
 * �������� 2005-8-17
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
 * ͨ��֪ͨ���ߵ���Ϣ������
 * 
 * @author YANGYANG
 * 2005-8-17 13:21:54
 */
public class MessageConsumer implements NotificationBus.Consumer{
	
	private Log log;
	
	private String ipAddress = "";
	
	public MessageConsumer(){
		//��־����
		LogFactory factory = new FileLogFactory("NotificationBus");
		//��־����
		this.log = factory.newInstance(this.getClass());

		//��ȡ��ǰ��������IP��ַ
		try {
			InetAddress address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
	
	
	/* 
	 * ����֪ͨ���߷����Ŀ����л�����
	 * @see org.jgroups.blocks.NotificationBus.Consumer#handleNotification(java.io.Serializable)
	 */
	public void handleNotification(Serializable message) {
		
		if(message instanceof Message ){
			
			log.debug("[" + ipAddress + "]�������յ�Message��Ϣ��");
			
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
		
		log.debug( "[" + ipAddress + "]��������Ա���� Address = " + address);
	}

	/*
	 * @see org.jgroups.blocks.NotificationBus.Consumer#memberLeft(org.jgroups.Address)
	 */
	public void memberLeft(Address address) {
		
		log.debug( "[" + ipAddress + "]��������Ա�˳� Address = " + address);
		
	}


}




