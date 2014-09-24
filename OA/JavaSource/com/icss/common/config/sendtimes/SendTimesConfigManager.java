/*
 * 创建日期 2005-6-29
 */
package com.icss.common.config.sendtimes;

import java.io.File;
import java.io.IOException;

import com.icss.common.config.ConfigManager;
import com.icss.j2ee.util.Globals;

/**
 * 显示最近联系人个数管理类
 * 
 * sendtimes.xml文件中设置了显示最近联系人个数
 * SendTimesConfigManager把sendtimes.xml中的数据缓存起来
 * 
 * 
 * @author WANGJIANG
 */
public class SendTimesConfigManager extends ConfigManager {

	public static SendTimesConfigManager handlerFactoryManagerRef;
	public synchronized static SendTimesConfigManager getInstance() throws IOException {
		if (handlerFactoryManagerRef == null) {
			handlerFactoryManagerRef = new SendTimesConfigManager();
		}
		return handlerFactoryManagerRef;
	}

	//config文件夹路径
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//配置文件的文件名
	private String fileName = "sendtimes.xml";
	private Integer sendTimes;

	/**
	 * 单例模式构造方法
	 */
	public SendTimesConfigManager() throws IOException {

		//sendTimes = new Integer(10);//默认显示前10位

		//设置XML文件引用
		super.setXmlConfigFile(new File(configPath + File.separator + fileName));
		//检验文件是否被修改
		super.checkModified();
	}

	/**
	 * 重新载入配置文件中的内容
	 */
	public void reload() throws IOException {

		super.reload();
		String times = getRoot().getChildText("Times");
		System.out.println("wangjiang::reload-->times = "+times);
		try{
			this.sendTimes = new Integer(times);
		}catch(NumberFormatException e){
			this.sendTimes = new Integer(5);
		}
	}

	public Integer getSendTimes() throws IOException {
		super.checkModified();
		return this.sendTimes;
	}


}
