/*
 * �������� 2005-8-17
 */
package com.icss.common.config.cache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.icss.common.config.ConfigManager;
import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;
import com.icss.j2ee.util.Globals;

/**
 * ���������ļ�������
 * 
 * @author YANGYANG
 * 2005-8-17 12:44:34
 */
public class CacheConfigManager extends ConfigManager{
	
	private static CacheConfigManager configManager;

	public synchronized static CacheConfigManager getInstance() throws IOException {
		if (configManager == null) {
			configManager = new CacheConfigManager();
		}
		return configManager;
	}


	//config�ļ���·��
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//�����ļ����ļ���
	private String fileName = "CacheConfig.xml";
	
	//Handler�ӿ�������DispatchEntity�����ӳ��
	private Map entitysMap;
	
	//��־����
	private Log log;

	/**
	 * ���췽��
	 * @throws IOException
	 */
	private CacheConfigManager() throws IOException {

		//��־����
		LogFactory factory = new FileLogFactory("NotificationBus");
		//��־����
		log = factory.newInstance(this.getClass());
		
		try{
			//��ʼ��ӳ�����
			entitysMap = new Hashtable();
	
			//����XML�ļ�����
			super.setXmlConfigFile(new File(configPath + File.separator + fileName));
			//�����ļ��Ƿ��޸�
			super.checkModified();
		
		} catch(IOException e){
			e.printStackTrace();
			log.error("CacheConfigManager��ȡCacheConfig.xml�����ļ���Ϣʱ�����쳣��" + e.getMessage(),e);
			throw e;
		}
		
	}


	/**
	 * �������������ļ��е�����
	 */
	public void reload() throws IOException {
		
		try{
			super.reload();
	
			//���ӳ���е�����
			entitysMap.clear();
	
			List busElements = super.getRoot().getChildren("NotificationBus");
			
			for (int i = 0; i < busElements.size(); i++) {
	
				//��ȡxmlԪ���е�����
				Element busElement = (Element) busElements.get(i);
				
				//����
				String name = busElement.getChildText("Name");
				//ͨ������
				Element channelPropertiesElement = busElement.getChild("ChannelProperties");
				String channelProperties = super.getCDATA(channelPropertiesElement);
				//�Ƿ�������
				String cacheEnabled = busElement.getChildText("CacheEnabled");
				
				cacheEnabled = cacheEnabled==null?"false":cacheEnabled;
				
				
				//���ݽӿ����ƺ�ʵ��������ƹ���һ��entity����
				NotificationBusEntity entity = new NotificationBusEntity();
				entity.setName(name);
				entity.setChannelProperties(channelProperties);
				entity.setCacheEnabled(new Boolean(cacheEnabled).booleanValue());
				
				
				//��֪ͨ���ߵ����ƺ�֪ͨ���ߵ���Ŀ���󱣴浽ӳ����	
				entitysMap.put(name,entity);
					
			}
			
		} catch(IOException e){
			e.printStackTrace();
			log.error("CacheConfigManager��ȡCacheConfig.xml�����ļ���Ϣʱ�����쳣��" + e.getMessage(),e);
			throw e;
		}
		

	}


	/**
	 * ��������ȡ��֪ͨ������Ŀ����
	 * @param name
	 * @return
	 */
	public NotificationBusEntity getNotificationBusEntity(String name) throws IOException{
		super.checkModified();
		return (NotificationBusEntity)entitysMap.get(name);
	}




}

