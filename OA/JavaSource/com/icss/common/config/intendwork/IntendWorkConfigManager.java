/*
 * �������� 2005-8-17
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
 * ���������ļ�������
 
�����ļ��������£�

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


	//config�ļ���·��
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//�����ļ����ļ���
	private String fileName = "IntendWorkConfig.xml";
	
	//IntendWorkEntity����ļ���
	private List entitys;

	/**
	 * ���췽��
	 * @throws IOException
	 */
	private IntendWorkConfigManager() throws IOException {
		
		try{
			//��ʼ��ӳ�����
			entitys = new ArrayList();
	
			//����XML�ļ�����
			super.setXmlConfigFile(new File(configPath + File.separator + fileName));
			//�����ļ��Ƿ��޸�
			super.checkModified();
		
		} catch(IOException e){
			e.printStackTrace();
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
			entitys.clear();
	
			List intendWorkElements = super.getRoot().getChildren("IntendWork");
			
			for (int i = 0; i < intendWorkElements.size(); i++) {
	
				//��ȡxmlԪ���е�����
				Element intendWorkElement = (Element) intendWorkElements.get(i);
				
				//��ϵͳ����
				String sysName = intendWorkElement.getChildText("SysName");

				String sysId = intendWorkElement.getChildText("SysId");
				
				String className = intendWorkElement.getChildText("ClassName");
				
				
				//���ݽӿ����ƺ�ʵ��������ƹ���һ��entity����
				IntendWorkEntity entity = new IntendWorkEntity();
				entity.setSysName(sysName);
				entity.setSysId(sysId);
				entity.setClassName(className);
				
				//��IntendWorkEntity������뵽������	
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

