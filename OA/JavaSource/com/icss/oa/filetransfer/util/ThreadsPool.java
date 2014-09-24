package com.icss.oa.filetransfer.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadsPool {

	private static ThreadsPool _tp = null;
	ThreadPoolExecutor _executor = null;

	private ThreadsPool() {
		_executor = new ThreadPoolExecutor(10, 30, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(200),
				new ThreadPoolExecutor.DiscardOldestPolicy());
	}

	public static ThreadsPool getInstance() {
		if (_tp == null) {
			_tp = new ThreadsPool();
		}
		return _tp;
	}

	public void putTask(Thread t) {
		_executor.execute(t);
	}

}
