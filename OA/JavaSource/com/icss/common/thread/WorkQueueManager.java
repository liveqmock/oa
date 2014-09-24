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
	 * ȡ��Ĭ�ϵķ��ػ��̹߳�������
	 * @return
	 */
	public IWorkQueue getWorkQueue(){
		return getWorkQueue(DEFAULT_WORKQUEUE);
	}

	/**
	 * ȡ��ָ�����Ƶķ��ػ��̹߳�������
	 * @param queueName	ָ���Ĺ�����������
	 * @return
	 */
	public IWorkQueue getWorkQueue(String queueName){
		synchronized(workQueueMap){
			if(!workQueueMap.containsKey(queueName)){
				//������ػ��̹߳�������
				WorkQueue queue = new WorkQueue();
				workQueueMap.put(queueName,queue);
			}
			return (WorkQueue)workQueueMap.get(queueName);
		}
	}
	
	
	/**
	 * ȡ��Ĭ�ϵ��ػ��̹߳�������
	 * @return
	 */
	public IWorkQueue getDaemonWorkQueue(){
		return getDaemonWorkQueue(DEFAULT_WORKQUEUE);
	}
	
	/**
	 * ȡ���ػ��̹߳�������
	 * @param queueName
	 * @return
	 */
	public IWorkQueue getDaemonWorkQueue(String queueName){
		synchronized(daemonWorkQueueMap){
			if(!daemonWorkQueueMap.containsKey(queueName)){
				//�����ػ��̹߳�������
				WorkQueue queue = new WorkQueue(true);
				daemonWorkQueueMap.put(queueName,queue);
			}
			return (WorkQueue)daemonWorkQueueMap.get(queueName);
		}
	}
	
}



