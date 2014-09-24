/*
 * 创建日期 2005-8-17
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


	//config文件夹路径
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//配置文件的文件名
	private String fileName = "MessageProcesserConfig.xml";
	
	//Message类名称与MessageProcesserEntity对象的映射
	private Map entityMap;

	private Log log;

	/**
	 * 构造方法
	 * @throws IOException
	 */
	private MessageProcesserConfigManager() throws IOException {

		//日志工厂
		LogFactory factory = new FileLogFactory("NotificationBus");
		//日志对象
		this.log = factory.newInstance(this.getClass());
		
		try{
			//初始化映射对象
			entityMap = new Hashtable();
	
			//设置XML文件引用
			super.setXmlConfigFile(new File(configPath + File.separator + fileName));
			//检验文件是否被修改
			super.checkModified();
			
		} catch(IOException e){
			e.printStackTrace();
			log.error("MessageProcesserConfigManager获取MessageProcesserConfig.xml配置文件信息时出现异常！" + e.getMessage(),e);
			throw e;
		}
	}


	/**
	 * 重新载入配置文件中的内容
	 */
	public void reload() throws IOException {
		
		try{
			super.reload();
	
			//清除映射中的数据
			entityMap.clear();
	
			List processerElements = super.getRoot().getChildren("MessageProcesser");
			
			//System.out.println("busElements.size() = " + processerElements.size());
			
			for (int i = 0; i < processerElements.size(); i++) {
	
				//获取xml元素中的数据
				Element processerElement = (Element) processerElements.get(i);
				
				//消息类名称
				String messageName = processerElement.getChildText("MessageName");
				//消息处理类名称
				String messageProcesserName = processerElement.getChildText("MessageProcesserName");
				
				//System.out.println("messageName = " + messageName);
				//System.out.println("messageProcesserName = " + messageProcesserName);
				
				//根据消息类的名称和消息处理类的名称构造一个entity对象
				MessageProcesserEntity entity = new MessageProcesserEntity();
				entity.setMessageName(messageName);
				entity.setMessageProcesserName(messageProcesserName);
				
				
				//把通知总线的名称和通知总线的条目对象保存到映射中	
				entityMap.put(messageName,entity);
					
			}
			
		} catch(IOException e){
			e.printStackTrace();
			log.error("CacheConfigManager获取CacheConfig.xml配置文件信息时出现异常！" + e.getMessage(),e);
			throw e;
		}

	}


	/**
	 * 根据名称取得消息处理器条目对象
	 * @param name
	 * @return
	 */
	public MessageProcesserEntity getMessageProcesserEntity(String messageName) throws IOException{
		super.checkModified();
		return (MessageProcesserEntity)entityMap.get(messageName);
	}

	/**
	 * 得消息消息名称与处理器名称的映射
	 * @param name
	 * @return
	 */
	public Map getMessageProcesserEntitys() throws IOException{
		super.checkModified();
		return entityMap;
	}


}



