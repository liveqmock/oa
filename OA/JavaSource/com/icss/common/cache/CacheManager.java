/*
 * �������� 2005-8-17
 */
package com.icss.common.cache;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Map;

import org.jgroups.blocks.NotificationBus;

import com.icss.common.config.cache.CacheConfigManager;
import com.icss.common.config.cache.NotificationBusEntity;
import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;
import com.icss.common.message.Message;
import com.icss.common.message.MessageConsumer;

/**
 * ���������
 * 
 * @author YANGYANG
 * 2005-8-17 18:42:22
 */
public class CacheManager{
	
	private static Map map = new Hashtable();
	
	public static synchronized CacheManager getInstance(String name) throws IOException{
	
		CacheManager cacheManager = null;
		synchronized(map){
			if(map.containsKey(name)){
				cacheManager = (CacheManager)map.get(name);
			}else{
				cacheManager = new CacheManager(name);
				map.put(name,cacheManager);
			}
		}
		return cacheManager;
	}

	
	//��־����
	private Log log;
	
	//�Ƿ�������
	private boolean isCacheEnabled;
	//֪ͨ���ߵ�����
	private String busName = "";
	
	//֪ͨ���߶���
	protected NotificationBus bus;	
	
	//���������ļ�
	protected CacheConfigManager configManager;
	//��ǰ��������IP��ַ
	protected String ipAddress;
	
	
	/**
	 * 
	 * @param name
	 * @throws IOException
	 */
	private CacheManager(String name) throws IOException{
		
		//��־����
		LogFactory factory = new FileLogFactory("NotificationBus");
		//��־����
		this.log = factory.newInstance(this.getClass());
		
		
		//��ȡ��ǰ��������IP��ַ
		try {
			InetAddress address = InetAddress.getLocalHost();
			this.ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		//���������ļ���������
		try {
			this.configManager = CacheConfigManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		this.initNotificationBus(name);
		
	}


	/**
	 * ���������������ļ���ȡ�ö�Ӧ�������ļ���������֪ͨ���߶���
	 * @param name
	 */
	protected void initNotificationBus(String name) throws IOException{

		//ȡ��
		NotificationBusEntity entity = configManager.getNotificationBusEntity(name);
		//֪ͨ��������
		this.busName = entity.getName();
		//����
		String properties = entity.getChannelProperties();
		//�Ƿ������������
		boolean isCacheEnabled = entity.isCacheEnabled();
		//�����Ƿ���Ҫ���л���
		this.setCacheEnabled(isCacheEnabled);
		
		
		if(isCacheEnabled()){
			try {
				bus = new NotificationBus(busName, properties);
				//�������߶���Ŀͻ���
				bus.setConsumer(new MessageConsumer());
				//����һ������
				bus.start();
				

				log.debug("�ɹ�����"+this.getIpAddress()+"������������Ϊ��"+busName+"����NotificationBus֪ͨ���߶���");
				log.debug("�������ƣ�" + busName);
				log.debug("ͨ�����ԣ�" + properties);
				log.debug("���û��棺" + isCacheEnabled());
				
				
				//�����̹߳���
				Runtime.getRuntime().addShutdownHook(new Thread(){
					public void run(){
						if(bus!=null){
							//ֹ֪ͣͨ����
							bus.stop();
							log.debug("�ɹ�ֹͣ"+CacheManager.this.getIpAddress()+"������������Ϊ��"+busName+"����NotificationBus֪ͨ���߶���");
						}
					}
				});
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				//����֪ͨ���߳����쳣�����治����
				setCacheEnabled(false);
				
				log.error("����NotificationBus֪ͨ���߶���ʱ�����쳣��" + e.getMessage(),e);
				
			}
			
		} else {
			log.debug( "�ɹ���ȡ"+this.getIpAddress()+"������������Ϊ��"+busName+"����NotificationBus֪ͨ����������Ϣ��");
			log.debug("�������ƣ�" + busName);
			log.debug("ͨ�����ԣ�" + properties);
			log.debug("���û��棺" + isCacheEnabled());			
			
		}

		
	}



	/**
	 * @return
	 */
	public boolean isCacheEnabled() {
		return isCacheEnabled;
	}

	/**
	 * @param b
	 */
	public void setCacheEnabled(boolean b) {
		isCacheEnabled = b;
	}


	/**
	 * @return
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param string
	 */
	public void setIpAddress(String string) {
		ipAddress = string;
	}

	
	/**
	 * @return
	 */
	public CacheConfigManager getConfigManager() {
		return configManager;
	}

	/**
	 * @param manager
	 */
	public void setConfigManager(CacheConfigManager manager) {
		configManager = manager;
	}

	/**
	 * ����֪ͨ
	 * @param message
	 */
	public void send(Message message){
		if(isCacheEnabled()){
			//���û���ʱ�ŷ�����Ϣ
			bus.sendNotification(message);
		}
	}



}
