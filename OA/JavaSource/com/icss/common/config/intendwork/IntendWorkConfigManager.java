/*
 * 创建日期 2005-8-17
 */
package com.icss.common.config.intendwork;

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
 
配置文件内容如下：

---------------------------------------------------------------------------------------- 
<?xml version="1.0" encoding="UTF-8"?>
<IntendWorkConfig>
	<IntendWork>
		<SysName>EXAMINE</SysName>
		<SysId>83</SysId>
		<ClassName>com.icss.common.intendwork.ExamineIntendWorkManager</ClassName>
	</IntendWork>
</IntendWorkConfig> 
 
----------------------------------------------------------------------------------------
 
 * @author YANGYANG
 * 2005-8-17 12:44:34
 */
public class IntendWorkConfigManager extends ConfigManager{
	
	private static IntendWorkConfigManager configManager;

	public synchronized static IntendWorkConfigManager getInstance() throws IOException {
		if (configManager == null) {
			configManager = new IntendWorkConfigManager();
		}
		return configManager;
	}


	//config文件夹路径
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//配置文件的文件名
	private String fileName = "IntendWorkConfig.xml";
	
	//IntendWorkEntity对象的集合
	private List entitys;

	/**
	 * 构造方法
	 * @throws IOException
	 */
	private IntendWorkConfigManager() throws IOException {
		
		try{
			//初始化映射对象
			entitys = new ArrayList();
	
			//设置XML文件引用
			super.setXmlConfigFile(new File(configPath + File.separator + fileName));
			//检验文件是否被修改
			super.checkModified();
		
		} catch(IOException e){
			e.printStackTrace();
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
			entitys.clear();
	
			List intendWorkElements = super.getRoot().getChildren("IntendWork");
			
			for (int i = 0; i < intendWorkElements.size(); i++) {
	
				//获取xml元素中的数据
				Element intendWorkElement = (Element) intendWorkElements.get(i);
				
				//子系统名称
				String sysName = intendWorkElement.getChildText("SysName");

				String sysId = intendWorkElement.getChildText("SysId");
				
				String className = intendWorkElement.getChildText("ClassName");
				
				
				//根据接口名称和实现类的名称构造一个entity对象
				IntendWorkEntity entity = new IntendWorkEntity();
				entity.setSysName(sysName);
				entity.setSysId(sysId);
				entity.setClassName(className);
				
				//把IntendWorkEntity对象加入到集合中	
				entitys.add(entity);
				
				
			}
			
		} catch(IOException e){
			e.printStackTrace();
			throw e;
		}
		

	}
	
	public List getIntendWorkEntitys() throws IOException{
		super.checkModified();
		return this.entitys;
	}




}

