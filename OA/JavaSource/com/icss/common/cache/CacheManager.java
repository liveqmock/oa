/*
 * 创建日期 2005-8-17
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
 * 缓存管理器
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

	
	//日志对象
	private Log log;
	
	//是否允许缓存
	private boolean isCacheEnabled;
	//通知总线的名称
	private String busName = "";
	
	//通知总线对象
	protected NotificationBus bus;	
	
	//缓存配置文件
	protected CacheConfigManager configManager;
	//当前服务器的IP地址
	protected String ipAddress;
	
	
	/**
	 * 
	 * @param name
	 * @throws IOException
	 */
	private CacheManager(String name) throws IOException{
		
		//日志工厂
		LogFactory factory = new FileLogFactory("NotificationBus");
		//日志对象
		this.log = factory.newInstance(this.getClass());
		
		
		//获取当前服务器的IP地址
		try {
			InetAddress address = InetAddress.getLocalHost();
			this.ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		//缓存配置文件管理器类
		try {
			this.configManager = CacheConfigManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		this.initNotificationBus(name);
		
	}


	/**
	 * 根据名称在配置文件中取得对应的配置文件，并构造通知总线对象
	 * @param name
	 */
	protected void initNotificationBus(String name) throws IOException{

		//取得
		NotificationBusEntity entity = configManager.getNotificationBusEntity(name);
		//通知总线名称
		this.busName = entity.getName();
		//属性
		String properties = entity.getChannelProperties();
		//是否启用这个缓存
		boolean isCacheEnabled = entity.isCacheEnabled();
		//设置是否需要进行缓存
		this.setCacheEnabled(isCacheEnabled);
		
		
		if(isCacheEnabled()){
			try {
				bus = new NotificationBus(busName, properties);
				//设置总线对象的客户端
				bus.setConsumer(new MessageConsumer());
				//启动一个总线
				bus.start();
				

				log.debug("成功启动"+this.getIpAddress()+"服务器上名称为“"+busName+"”的NotificationBus通知总线对象！");
				log.debug("总线名称：" + busName);
				log.debug("通道属性：" + properties);
				log.debug("启用缓存：" + isCacheEnabled());
				
				
				//设置线程钩子
				Runtime.getRuntime().addShutdownHook(new Thread(){
					public void run(){
						if(bus!=null){
							//停止通知总线
							bus.stop();
							log.debug("成功停止"+CacheManager.this.getIpAddress()+"服务器上名称为“"+busName+"”的NotificationBus通知总线对象！");
						}
					}
				});
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				//构造通知总线出现异常，缓存不可用
				setCacheEnabled(false);
				
				log.error("构造NotificationBus通知总线对象时出现异常！" + e.getMessage(),e);
				
			}
			
		} else {
			log.debug( "成功获取"+this.getIpAddress()+"服务器上名称为“"+busName+"”的NotificationBus通知总线配置信息！");
			log.debug("总线名称：" + busName);
			log.debug("通道属性：" + properties);
			log.debug("启用缓存：" + isCacheEnabled());			
			
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
	 * 发出通知
	 * @param message
	 */
	public void send(Message message){
		if(isCacheEnabled()){
			//启用缓存时才发送消息
			bus.sendNotification(message);
		}
	}



}
