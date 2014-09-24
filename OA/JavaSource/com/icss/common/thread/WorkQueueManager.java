/*
 * Created on 2005-4-9
 */
package com.icss.common.thread;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author YANGYANG
 * 2005-4-9 10:21:43
 */
public class WorkQueueManager {
	
	public static final String DEFAULT_WORKQUEUE = "DEFAULT_WORKQUEUE";
	
	private static WorkQueueManager workQueueManagerRef;
	public synchronized static WorkQueueManager getInstance(){
		if(workQueueManagerRef==null){
			workQueueManagerRef = new WorkQueueManager();
		}
		return workQueueManagerRef;
	}
	
	private Map workQueueMap;
	private Map daemonWorkQueueMap;
	
	private WorkQueueManager(){
		workQueueMap = new Hashtable();
		daemonWorkQueueMap = new Hashtable();
	}
	
	/**
	 * 取得默认的非守护线程工作队列
	 * @return
	 */
	public IWorkQueue getWorkQueue(){
		return getWorkQueue(DEFAULT_WORKQUEUE);
	}

	/**
	 * 取得指定名称的非守护线程工作队列
	 * @param queueName	指定的工作队列名称
	 * @return
	 */
	public IWorkQueue getWorkQueue(String queueName){
		synchronized(workQueueMap){
			if(!workQueueMap.containsKey(queueName)){
				//构造非守护线程工作队列
				WorkQueue queue = new WorkQueue();
				workQueueMap.put(queueName,queue);
			}
			return (WorkQueue)workQueueMap.get(queueName);
		}
	}
	
	
	/**
	 * 取得默认的守护线程工作队列
	 * @return
	 */
	public IWorkQueue getDaemonWorkQueue(){
		return getDaemonWorkQueue(DEFAULT_WORKQUEUE);
	}
	
	/**
	 * 取得守护线程工作队列
	 * @param queueName
	 * @return
	 */
	public IWorkQueue getDaemonWorkQueue(String queueName){
		synchronized(daemonWorkQueueMap){
			if(!daemonWorkQueueMap.containsKey(queueName)){
				//构造守护线程工作队列
				WorkQueue queue = new WorkQueue(true);
				daemonWorkQueueMap.put(queueName,queue);
			}
			return (WorkQueue)daemonWorkQueueMap.get(queueName);
		}
	}
	
}



