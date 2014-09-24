package com.icss.common.thread;

import java.util.LinkedList;
/**
 * �����̳߳صĹ�������
 * 2004-4-2 22:12:02
 * 


	//WorkQueue queue = new WorkQueue(10);
	WorkQueue queue = WorkQueue.getInstance();
	queue.execute(
		new Runnable(){
			public void run() {
				runMethod();
			}}
 	);
 	

 * 
 * @YANGYANG
 */
public class WorkQueue implements IWorkQueue {

    private static int DEFAULT_THREAD_NUM = 30;
    
	/**
	 * �߳�����
	 */
	private final int nThreads;
	
	/**
	 * �����߳�����
	 * ��ʼ����СΪnThreads 
	 */
	private final PoolWorker[] threads;
	
	/**
	 * �������
	 */
	private final LinkedList queue;

	/**
	 * 
	 * @param isDaemon
	 */
	public WorkQueue(boolean isDaemon){
		this(DEFAULT_THREAD_NUM,isDaemon);
	}
	
	public WorkQueue(){
		this(DEFAULT_THREAD_NUM,false);
	}
	
	/**
	 * ���췽��
	 * @param nThreads	ָ���̳߳س�ʼ������
	 * @param isDaemon	�Ƿ����ػ��߳�
	 */
	public WorkQueue(int nThreads,boolean isDaemon) {
		
	    //System.out.println("WorkQueue("+nThreads+","+isDaemon+")");
	    
		//�߳�����
		this.nThreads = nThreads;
		
		//�����������
		//��ʱΪ��������������û���κ��������
		queue = new LinkedList();
		
		//���칤���߳�����
		threads = new PoolWorker[nThreads];
		
		//ѭ���������е��߳�����
		//���칤���̶߳���
		//���������еĹ����߳�
		for (int i = 0; i < nThreads; i++) {
			
			//�����¹����̶߳���
			threads[i] = new PoolWorker();
			threads[i].setDaemon(isDaemon);
			
			//�����̵߳�run()����
			//�����̣߳�start()������������
			/**
			 * ÿ�������߳���ִ��ʱ
			 * 
			 * 1.��ͬ������ù�������ͬ������
			 *   ���ӹ���������ȡ��һ����������
			 * 
			 * 2.ִ�й��������run()����
			 * 
			 * 3.�����������Ϊ��ʱ
			 *   ��ǰ�̷߳���ͬ���������ȴ����У��ȴ��ٴα�����
			 * 
			 * ������nThreads���߳���ͬ���Ķ�ȡ���������е�����
			 * 
			 * 4.�ڹ��췽����ʼ��ʱ���������е�ֵΪ��
			 *   �������ɵ�10���̶߳��󶼽�ͨ������queue.wait();����ȴ�����
			 * 
			 * 5.�ȴ����н���WorkQueueʵ���״ε���execute()����ʱ������
			 *   ��ͨ������queue.notify();ʵ�֣�
			 */
			threads[i].start();
			
		}
	}
	
	
	/**
	 * ͬ������
	 * WorkQueueʵ�����ô˷���ʱ����һ���߳���Ϊ����
	 * ���������̵߳����ý����빤�����������̳߳��е��߳����ε���
	 * 
	 * @param r Ҫִ�е��߳�
	 */
	public void execute(Runnable r) {
		
		//��ǰ����ӵ������
		//LinkedList���������
		synchronized (queue) {
			
			//��ʵ����Runnable�ӿڵ��̶߳�����뵽�����ĩβ
			queue.addLast(r);
			
			//���ѵȴ��߳�
			/**
			 * 1.֪ͨ�ȴ�����������߳�
			 * 2.ԭ�����ڵȴ������е��̴߳ӵȴ������������������ready���У��ȴ����� 
			 * 3.�ȴ��߳��ڵ�ǰͬ��������������ʱ�Ծ����ķ�ʽ�ٴ�ȡ��ͬ������
			 */
			//����Ļ���һ���ȴ������е��̣߳�ʹ֮����ready����
			queue.notify();
			//����ȫ���ȴ��̣߳�ʹ���ǽ���ready���У����Ծ�����ʽȡ��ͬ������
			//queue.notifyAll();
			
		}//��������
	}
	
	
	/**
	 * �ڲ���
	 * �̳߳��еĹ����߳�
	 * 
	 * 1.����������������ʱ�������̻߳�Ӷ����׽ڵ���ȡ��һ����ִ�е��̣߳���ִ��
	 * 2.���������û�����񣬹����̻߳���queue.wait();��������ȴ�����
	 * 
	 * 2004-4-2 22:12:14
	 * @YANGYANG
	 */
	private class PoolWorker extends Thread {
	
		
		public void run() {
			
			Runnable runnable;
			
			while (true) {
				
				synchronized (queue) {
					
					//�������Ϊ����ʹ�߳̽���ȴ�״̬
					while (queue.isEmpty()) {
						
						try {
							
							//��ǰ�̷߳��� ��������ͬ������
							//��ǰ�߳̽���ȴ�����
							queue.wait();
							
						} catch (InterruptedException e) {
						    e.printStackTrace();
						}
					}
					
					//ȡ�������е��׸��ڵ�
					//���д����ʵ����Runnable�ӿڵ��߳�
					runnable = (Runnable) queue.removeFirst();
				}
				
				/**
				 * �������ȡ�쳣
				 * һ�������쳣��ᷢ���߳�й©
				 */
				try {
					
					//����ȡ�����̵߳�run()����
					//�Ӷ������߳�
				    runnable.run();
					
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				
				
			}
			
			
		}
	}
}
