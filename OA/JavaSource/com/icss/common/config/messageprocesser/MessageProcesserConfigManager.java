/*
 * �������� 2005-8-17
 */
package com.icss.common.config.messageprocesser;

import java.io.File;
import java.io.IOException;
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
 * @author YANGYANG
 * 2005-8-17 17:04:58
 */
public class MessageProcesserConfigManager extends ConfigManager{
	
	private static MessageProcesserConfigManager configManager;

	public synchronized static MessageProcesserConfigManager getInstance() throws IOException {
		if (configManager == null) {
			configManager = new MessageProcesserConfigManager();
		}
		return configManager;
	}


	//config�ļ���·��
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//�����ļ����ļ���
	private String fileName = "MessageProcesserConfig.xml";
	
	//Message��������MessageProcesserEntity�����ӳ��
	private Map entityMap;

	private Log log;

	/**
	 * ���췽��
	 * @throws IOException
	 */
	private MessageProcesserConfigManager() throws IOException {

		//��־����
		LogFactory factory = new FileLogFactory("NotificationBus");
		//��־����
		this.log = factory.newInstance(this.getClass());
		
		try{
			//��ʼ��ӳ�����
			entityMap = new Hashtable();
	
			//����XML�ļ�����
			super.setXmlConfigFile(new File(configPath + File.separator + fileName));
			//�����ļ��Ƿ��޸�
			super.checkModified();
			
		} catch(IOException e){
			e.printStackTrace();
			log.error("MessageProcesserConfigManager��ȡMessageProcesserConfig.xml�����ļ���Ϣʱ�����쳣��" + e.getMessage(),e);
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
			entityMap.clear();
	
			List processerElements = super.getRoot().getChildren("MessageProcesser");
			
			//System.out.println("busElements.size() = " + processerElements.size());
			
			for (int i = 0; i < processerElements.size(); i++) {
	
				//��ȡxmlԪ���е�����
				Element processerElement = (Element) processerElements.get(i);
				
				//��Ϣ������
				String messageName = processerElement.getChildText("MessageName");
				//��Ϣ����������
				String messageProcesserName = processerElement.getChildText("MessageProcesserName");
				
				//System.out.println("messageName = " + messageName);
				//System.out.println("messageProcesserName = " + messageProcesserName);
				
				//������Ϣ������ƺ���Ϣ����������ƹ���һ��entity����
				MessageProcesserEntity entity = new MessageProcesserEntity();
				entity.setMessageName(messageName);
				entity.setMessageProcesserName(messageProcesserName);
				
				
				//��֪ͨ���ߵ����ƺ�֪ͨ���ߵ���Ŀ���󱣴浽ӳ����	
				entityMap.put(messageName,entity);
					
			}
			
		} catch(IOException e){
			e.printStackTrace();
			log.error("CacheConfigManager��ȡCacheConfig.xml�����ļ���Ϣʱ�����쳣��" + e.getMessage(),e);
			throw e;
		}

	}


	/**
	 * ��������ȡ����Ϣ��������Ŀ����
	 * @param name
	 * @return
	 */
	public MessageProcesserEntity getMessageProcesserEntity(String messageName) throws IOException{
		super.checkModified();
		return (MessageProcesserEntity)entityMap.get(messageName);
	}

	/**
	 * ����Ϣ��Ϣ�����봦�������Ƶ�ӳ��
	 * @param name
	 * @return
	 */
	public Map getMessageProcesserEntitys() throws IOException{
		super.checkModified();
		return entityMap;
	}


}



