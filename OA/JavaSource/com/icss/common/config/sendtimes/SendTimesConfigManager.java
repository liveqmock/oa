/*
 * �������� 2005-6-29
 */
package com.icss.common.config.sendtimes;

import java.io.File;
import java.io.IOException;

import com.icss.common.config.ConfigManager;
import com.icss.j2ee.util.Globals;

/**
 * ��ʾ�����ϵ�˸���������
 * 
 * sendtimes.xml�ļ�����������ʾ�����ϵ�˸���
 * SendTimesConfigManager��sendtimes.xml�е����ݻ�������
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

	//config�ļ���·��
	private String configPath = Globals.resourceOneHome + File.separator + Globals.resourceOneCoreConfigPath;
	//�����ļ����ļ���
	private String fileName = "sendtimes.xml";
	private Integer sendTimes;

	/**
	 * ����ģʽ���췽��
	 */
	public SendTimesConfigManager() throws IOException {

		//sendTimes = new Integer(10);//Ĭ����ʾǰ10λ

		//����XML�ļ�����
		super.setXmlConfigFile(new File(configPath + File.separator + fileName));
		//�����ļ��Ƿ��޸�
		super.checkModified();
	}

	/**
	 * �������������ļ��е�����
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
