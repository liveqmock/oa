/*
 * 创建日期 2005-8-17
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
 * 缓存配置文件管理器
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


	//config文件夹路径
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//配置文件的文件名
	private String fileName = "CacheConfig.xml";
	
	//Handler接口名称与DispatchEntity对象的映射
	private Map entitysMap;
	
	//日志对象
	private Log log;

	/**
	 * 构造方法
	 * @throws IOException
	 */
	private CacheConfigManager() throws IOException {

		//日志工厂
		LogFactory factory = new FileLogFactory("NotificationBus");
		//日志对象
		log = factory.newInstance(this.getClass());
		
		try{
			//初始化映射对象
			entitysMap = new Hashtable();
	
			//设置XML文件引用
			super.setXmlConfigFile(new File(configPath + File.separator + fileName));
			//检验文件是否被修改
			super.checkModified();
		
		} catch(IOException e){
			e.printStackTrace();
			log.error("CacheConfigManager获取CacheConfig.xml配置文件信息时出现异常！" + e.getMessage(),e);
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
			entitysMap.clear();
	
			List busElements = super.getRoot().getChildren("NotificationBus");
			
			for (int i = 0; i < busElements.size(); i++) {
	
				//获取xml元素中的数据
				Element busElement = (Element) busElements.get(i);
				
				//名称
				String name = busElement.getChildText("Name");
				//通道属性
				Element channelPropertiesElement = busElement.getChild("ChannelProperties");
				String channelProperties = super.getCDATA(channelPropertiesElement);
				//是否允许缓存
				String cacheEnabled = busElement.getChildText("CacheEnabled");
				
				cacheEnabled = cacheEnabled==null?"false":cacheEnabled;
				
				
				//根据接口名称和实现类的名称构造一个entity对象
				NotificationBusEntity entity = new NotificationBusEntity();
				entity.setName(name);
				entity.setChannelProperties(channelProperties);
				entity.setCacheEnabled(new Boolean(cacheEnabled).booleanValue());
				
				
				//把通知总线的名称和通知总线的条目对象保存到映射中	
				entitysMap.put(name,entity);
					
			}
			
		} catch(IOException e){
			e.printStackTrace();
			log.error("CacheConfigManager获取CacheConfig.xml配置文件信息时出现异常！" + e.getMessage(),e);
			throw e;
		}
		

	}


	/**
	 * 根据名称取得通知总线条目对象
	 * @param name
	 * @return
	 */
	public NotificationBusEntity getNotificationBusEntity(String name) throws IOException{
		super.checkModified();
		return (NotificationBusEntity)entitysMap.get(name);
	}




}

