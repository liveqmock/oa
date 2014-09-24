/*
 * �������� 2005-4-8
 */
package com.icss.common.log.filelog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.icss.common.log.Log;
import com.icss.common.log.runnablelog.RunnableLogFactory;
import com.icss.j2ee.util.Globals;

/**
 * �ļ���־�๤��
 * @author YANGYAMG
 */
public class FileLogFactory extends RunnableLogFactory {
	
	//Ĭ�ϵ���־�ļ���
	private static String DEFAULT_LOG_NAME = "Server";
	//Ҫ����־��¼���е�log�ļ� 
	private File file;
	//Ĭ�ϵ���־�ļ���
	private String fileName;
	
	/*
	 * 20050701
	 * ��������־���м���ͷ��Ϣ�ķ�����
	 * 
	 * 1.������־����ʵ����Ĺ��췽����
	 *   ���ַ�ʽ������־�м�����Ϣͷ��Ϊ�ļ���־�����еĹ���
	 *   ��������־����ʵ�������û�м�¼��Ϣͷ�Ĺ���
	 * 
	 * 2.������־�����ӿ���
	 *   �����ַ�ʽ�����е���־������Ҫʵ�ּ�����Ϣͷ�ķ������Ӷ������ڸ�����־������������־���д�����Ϣͷ��Ϣ
	 *   ���ַ�ʽʵ�����Ƕ�������־����ʵ�����һ��Լ����涨��Ҫ�����е���־�ṩ������м�¼��Ϣͷ�Ĺ���
	 *  
	 * 
	 * Ŀǰ���õ��Ǽ�����־�����ӿ���
	 * ����������ǿ�����е���־����������־ʵ���඼�ṩ������Ϣͷ�Ĺ���
	 * 
	 */
	
	/**
	 * ���췽��1
	 */
	public FileLogFactory(){
		fileName = DEFAULT_LOG_NAME;
	}
	
	/**
	 * ���췽��2
	 * @param logFileName
	 */
	public FileLogFactory(String fileName){
		this.fileName = fileName;
	}

	/**
	 * ��ʼ��
	 */
	private void initialize(){

		//����logs�ļ���
		String logFilePath = Globals.resourceOneHome + File.separator + "logs";
		File logDir = new File(logFilePath);
		if(!logDir.exists()){
			logDir.mkdirs();
		}

		//��ȡ�����Ӧ����־�ļ�
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(new Date());
		//�����ڹ�����־�ļ�����
		String logFileName = Globals.resourceOneHome + File.separator + "logs" + File.separator + fileName + "_" + today + ".log";
		file = new File(logFileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}






	/**
	 * ʵ����־�����ӿ�
	 * ʵ����һ����־����0 
	 * @see com.icss.common.log.LogFactory#newInstance(Class classe , String log_head)
	 */
	public Log newInstance(Class classe , String head) {	
		//��ʼ����־�ļ��к�ÿ��һ������־�ļ�
		initialize();
		FileLog fileLog = new FileLog(getDefaultLog(classe),file,head);	//edit by yangyang 20050701
		return getRunnableLog(fileLog);
	}

	/**
	 * ʵ����־�����ӿ�
	 * ʵ����һ����־����1 
	 * @see com.icss.common.log.LogFactory#newInstance(Class classe)
	 */
	public Log newInstance(Class classe) {	
		//��ʼ����־�ļ��к�ÿ��һ������־�ļ�
		initialize();
		FileLog fileLog = new FileLog(getDefaultLog(classe),file);
		return getRunnableLog(fileLog);
	}

	/**
	 * ʵ����־�����ӿ�
	 * ʵ����һ����־����2
	 * @see com.icss.common.log.LogFactory#newInstance(Log log)
	 */
	public Log newInstance(Log log) {
		//��ʼ����־�ļ��к�ÿ��һ������־�ļ�		
		initialize();
		FileLog fileLog = new FileLog(log,file);
		return getRunnableLog(fileLog);
	}






	
	
}




