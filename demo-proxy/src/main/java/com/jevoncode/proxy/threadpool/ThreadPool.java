package com.jevoncode.proxy.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {
	public static ThreadPoolExecutor threadPool = null;
	static {
		threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
	}

	public static Future submit(Callable task) {
		return threadPool.submit(task);
	}
	
	//所有线程执行完毕后,需手动执行shutdown,不然主进程是不会退出的
	public static void shutdown(){
		threadPool.shutdown();
	}
}
