/*
 * 创建日期 2005-4-8
 */
package com.icss.common.log.filelog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.icss.common.log.Log;

/**
 * 附加到文件中的日志类
 * @author YANGYAMG
 */
public class FileLog implements Log{
	
	private Log log;
	private File file;
	private PrintWriter output;
	private SimpleDateFormat format;
	
	private String head = "";
	
	public Class getObjectClass() {
		return log.getObjectClass();
	}
	
	/**
	 * 构造方法1
	 * @param log
	 * @param logFile
	 */
	public FileLog(Log log, File file){
		this(log, file ,"");	//edit by yangyang 20050701
	}
	
	/**
	 * 构造方法2
	 * @param log
	 * @param file
	 * @param head
	 */
	public FileLog(Log log, File file, String head){
		//传递引用
		this.log = log;
		this.file = file;
		try {
			//jdk1.4支持
			//output = new PrintWriter(new FileWriter(logFile,true));
			//jdk1.3支持
			output = new PrintWriter(new FileWriter(file.getCanonicalPath(),true));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//消息头
		this.head = head;	//edit by yangyang 20050701
	}
	

	public void debug(Object object) {
		if(Log.DEBUG){
			log(Log.DEBUG_LEVEL,object);
		}
		log.debug(object);
	}

	public void info(Object object) {
		if(Log.INFO){
			log(Log.INFO_LEVEL,object);	
		}
		log.info(object);
	}

	public void warn(Object object) {
		if(Log.WARNING){
			log(Log.WARNING_LEVEL,object);
		}
		log.warn(object);
	}

	public void message(Object object) {
		if(Log.MESSAGE){
			log(Log.MESSAGE_LEVEL,object);	
		}
		log.message(object);
	}

	public void error(Object object, Throwable throwable) {
		log(Log.ERROR_LEVEL,object,throwable);
		log.error(object,throwable);		
	}
	
	

	/**
	 * 把日志信息写入到文件中
	 * @param level		日志级别
	 * @param object	日志内容
	 * @param throwable	可抛出的异常
	 */
	private void log(int level,Object object, Throwable throwable) {
		log(level,object);
		log(level,throwable);
	}
	
	
	/**
	 * 把日志信息写入到文件中
	 * @param level		日志级别
	 * @param throwable	可抛出的异常
	 */
	private void log(int level, Throwable throwable){
		output.write(this.head);	//edit by yangyang 20050701
		throwable.printStackTrace(output);
		output.flush();
	}
	
	
	/**
	 * 把日志信息写入到文件中
	 * @param level		日志级别
	 * @param object	日志内容
	 */
	private void log(int level,Object object){
		
		StringBuffer sb = new StringBuffer();
//		sb.append(format.format(new Date()));
//		sb.append(" ");
//		sb.append(log.getObjectClass().getName());
//		sb.append(" ");		
//		sb.append(Log.LEVELS[level]);
//		sb.append(" -   ");
//		sb.append(object);		

		//edit by yangyang 20050701
		sb.append(format.format(new Date())).append(" ").append(log.getObjectClass().getName()).append(" ").append(Log.LEVELS[level]).append(" -   " + this.head ).append(object);

		output.write(sb.toString());
		output.write(line);
		output.flush();
		
		
	}





}






