/*
 * 创建日期 2005-4-8
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
 * 文件日志类工厂
 * @author YANGYAMG
 */
public class FileLogFactory extends RunnableLogFactory {
	
	//默认的日志文件名
	private static String DEFAULT_LOG_NAME = "Server";
	//要把日志记录其中的log文件 
	private File file;
	//默认的日志文件名
	private String fileName;
	
	/*
	 * 20050701
	 * 关于在日志类中加入头信息的分析：
	 * 
	 * 1.加在日志工厂实现类的构造方法中
	 *   这种方式下在日志中加入消息头成为文件日志类特有的功能
	 *   其他的日志工厂实现类可以没有记录消息头的功能
	 * 
	 * 2.加在日志工厂接口中
	 *   在这种方式下所有的日志工厂都要实现加入消息头的方法，从而可以在各个日志工厂创建的日志类中传递消息头信息
	 *   这种方式实际上是对所有日志工厂实现类的一种约定或规定，要求所有的日志提供类必须有记录消息头的功能
	 *  
	 * 
	 * 目前采用的是加在日志工厂接口中
	 * 这样作可以强制所有的日志工厂及其日志实现类都提供加入消息头的功能
	 * 
	 */
	
	/**
	 * 构造方法1
	 */
	public FileLogFactory(){
		fileName = DEFAULT_LOG_NAME;
	}
	
	/**
	 * 构造方法2
	 * @param logFileName
	 */
	public FileLogFactory(String fileName){
		this.fileName = fileName;
	}

	/**
	 * 初始化
	 */
	private void initialize(){

		//创建logs文件夹
		String logFilePath = Globals.resourceOneHome + File.separator + "logs";
		File logDir = new File(logFilePath);
		if(!logDir.exists()){
			logDir.mkdirs();
		}

		//获取今天对应的日志文件
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(new Date());
		//以日期构造日志文件对象
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
	 * 实现日志工厂接口
	 * 实例化一个日志对象0 
	 * @see com.icss.common.log.LogFactory#newInstance(Class classe , String log_head)
	 */
	public Log newInstance(Class classe , String head) {	
		//初始化日志文件夹和每日一个的日志文件
		initialize();
		FileLog fileLog = new FileLog(getDefaultLog(classe),file,head);	//edit by yangyang 20050701
		return getRunnableLog(fileLog);
	}

	/**
	 * 实现日志工厂接口
	 * 实例化一个日志对象1 
	 * @see com.icss.common.log.LogFactory#newInstance(Class classe)
	 */
	public Log newInstance(Class classe) {	
		//初始化日志文件夹和每日一个的日志文件
		initialize();
		FileLog fileLog = new FileLog(getDefaultLog(classe),file);
		return getRunnableLog(fileLog);
	}

	/**
	 * 实现日志工厂接口
	 * 实例化一个日志对象2
	 * @see com.icss.common.log.LogFactory#newInstance(Log log)
	 */
	public Log newInstance(Log log) {
		//初始化日志文件夹和每日一个的日志文件		
		initialize();
		FileLog fileLog = new FileLog(log,file);
		return getRunnableLog(fileLog);
	}






	
	
}




