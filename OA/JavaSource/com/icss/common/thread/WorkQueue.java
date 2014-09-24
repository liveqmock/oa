package com.icss.common.thread;

import java.util.LinkedList;
/**
 * 具有线程池的工作队列
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
	 * 线程数量
	 */
	private final int nThreads;
	
	/**
	 * 工作线程数组
	 * 初始化大小为nThreads 
	 */
	private final PoolWorker[] threads;
	
	/**
	 * 链表对象
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
	 * 构造方法
	 * @param nThreads	指定线程池初始化数量
	 * @param isDaemon	是否是守护线程
	 */
	public WorkQueue(int nThreads,boolean isDaemon) {
		
	    //System.out.println("WorkQueue("+nThreads+","+isDaemon+")");
	    
		//线程数量
		this.nThreads = nThreads;
		
		//构造链表对象
		//此时为空链表，即链表中没有任何任务存在
		queue = new LinkedList();
		
		//构造工作线程数组
		threads = new PoolWorker[nThreads];
		
		//循环遍历所有的线程数组
		//构造工作线程对象
		//并启动所有的工作线程
		for (int i = 0; i < nThreads; i++) {
			
			//构造新工作线程对象
			threads[i] = new PoolWorker();
			threads[i].setDaemon(isDaemon);
			
			//调用线程的run()方法
			//启动线程，start()方法立即返回
			/**
			 * 每个工作线程在执行时
			 * 
			 * 1.以同步方获得共享数据同步对象
			 *   即从工作队列中取得一个工作任务
			 * 
			 * 2.执行工作任务的run()方法
			 * 
			 * 3.如果工作队列为空时
			 *   当前线程放弃同步对象进入等待队列，等待再次被唤醒
			 * 
			 * （共有nThreads个线程在同步的读取工作队列中的任务）
			 * 
			 * 4.在构造方法初始化时工作队列中的值为空
			 *   所以生成的10个线程对象都将通过调用queue.wait();进入等待队列
			 * 
			 * 5.等待队列将在WorkQueue实例首次调用execute()方法时被唤醒
			 *   （通过调用queue.notify();实现）
			 */
			threads[i].start();
			
		}
	}
	
	
	/**
	 * 同步方法
	 * WorkQueue实例调用此方法时，以一个线程作为参数
	 * 传递来的线程的引用将进入工作队列中由线程池中的线程依次调用
	 * 
	 * @param r 要执行的线程
	 */
	public void execute(Runnable r) {
		
		//当前方法拥有锁定
		//LinkedList共享对象锁
		synchronized (queue) {
			
			//把实现了Runnable接口的线程对象加入到链表的末尾
			queue.addLast(r);
			
			//唤醒等待线程
			/**
			 * 1.通知等待队列里面的线程
			 * 2.原来处于等待队列中的线程从等待队列里面出来，进入ready队列，等待调度 
			 * 3.等待线程在当前同步数据锁定结束时以竞争的方式再次取得同步对象
			 */
			//随机的唤醒一个等待队列中的线程，使之进入ready队列
			queue.notify();
			//唤醒全部等待线程，使它们进入ready队列，再以竞争方式取得同步对象
			//queue.notifyAll();
			
		}//锁定结束
	}
	
	
	/**
	 * 内部类
	 * 线程池中的工作线程
	 * 
	 * 1.工作队列中有任务时，工作线程会从队列首节点中取出一个可执行的线程，并执行
	 * 2.如果队列中没有任务，工作线程会由queue.wait();方法进入等待队列
	 * 
	 * 2004-4-2 22:12:14
	 * @YANGYANG
	 */
	private class PoolWorker extends Thread {
	
		
		public void run() {
			
			Runnable runnable;
			
			while (true) {
				
				synchronized (queue) {
					
					//如果链表为空则使线程进入等待状态
					while (queue.isEmpty()) {
						
						try {
							
							//当前线程放弃 共享数据同步对象
							//当前线程进入等待队列
							queue.wait();
							
						} catch (InterruptedException e) {
						    e.printStackTrace();
						}
					}
					
					//取得链表中的首个节点
					//其中存放了实现了Runnable接口的线程
					runnable = (Runnable) queue.removeFirst();
				}
				
				/**
				 * 如果不获取异常
				 * 一旦产生异常则会发生线程泄漏
				 */
				try {
					
					//调用取出的线程的run()方法
					//从而启动线程
				    runnable.run();
					
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				
				
			}
			
			
		}
	}
}
