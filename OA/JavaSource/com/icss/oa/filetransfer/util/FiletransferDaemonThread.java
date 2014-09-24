/*
 * 创建日期 2005-12-28
 */
package com.icss.oa.filetransfer.util;

/**
 * 文件传递的后台线程
 * 用于清除收发邮件时创建的临时文件夹中超时的文件夹
 * 2005-12-28 12:37:37
 */
public class FiletransferDaemonThread extends Thread{
	
	//守护线程睡眠时间
	private long sleeptime;
	//是否继续运行的标志位
	private boolean running = false;
	
	public FiletransferDaemonThread(long sleeptime){
		//设置为守护线程，使主线程退出时守护线程也退出
		this.setDaemon(true);
		//传递守护线程的睡眠时间
		this.sleeptime = sleeptime;
	}
	
	public void run(){
		System.out.println("FiletransferDaemonThread.run()");
		while(running){
			//执行清除临时文件的操作
			FiletransferUtil.clearFiletransferTempDir();
			try {
				//守护线程睡眠指定的时间
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("FiletransferDaemonThread stopped.");
	}
	
	/**
	 * 守护线程开始运行
	 */
	public void startRunning(){
		//设置运行标志位
		this.running = true;
		//启动线程
		this.start();
	}
	
	/**
	 * 守护线程结束运行
	 */
	public void stopRunning(){
		//设置运行标志位
		this.running = false;
	}
	
	
}







