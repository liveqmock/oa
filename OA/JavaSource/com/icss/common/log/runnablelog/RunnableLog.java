/*
 * Created on 2005-4-9
 */
package com.icss.common.log.runnablelog;

import com.icss.common.log.Log;
import com.icss.common.thread.IWorkQueue;
import com.icss.common.thread.WorkQueueManager;


/**
 * @author YANGYANG
 * 2005-4-9 10:14:22
 */
public class RunnableLog implements Log {
	
	private IWorkQueue workQueue;
	private Log log;
	
	public RunnableLog(Log log){
		this.log = log;
		this.workQueue = WorkQueueManager.getInstance().getDaemonWorkQueue("LOG");
	}

	public void debug(final Object object) {
		workQueue.execute(new Runnable(){
			public void run() {
				log.debug(object);
			}}
		);
	}

	public void info(final Object object) {		
		workQueue.execute(new Runnable(){
			public void run() {
				log.info(object);
			}}
		);
	}

	public void warn(final Object object) {
		workQueue.execute(new Runnable(){
			public void run() {
				log.warn(object);
			}}
		);
	}

	public void message(final Object object) {
		workQueue.execute(new Runnable(){
			public void run() {
				log.message(object);
			}}
		);
	}

	public void error(final Object object, final Throwable throwable) {
		workQueue.execute(new Runnable(){
			public void run() {
				log.error(object,throwable);
			}}
		);	
	}

	public Class getObjectClass() {
		return log.getObjectClass();
	}
}
