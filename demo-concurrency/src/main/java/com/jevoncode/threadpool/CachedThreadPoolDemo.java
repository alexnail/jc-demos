package com.jevoncode.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

public class CachedThreadPoolDemo {
	public static Logger log = Logger.getLogger(RunOfThreadPoolDemo.class);
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		int i=1;
		while(true){
			if(i>100)
			Thread.sleep(1000);
			executorService.submit(new Task(i++));
			log.debug("线程队列大小 ："+executorService.getQueue().size());
			System.out.println("当前正在工作线程数："+executorService.getActiveCount());
			System.out.println("当前线程池里的线程数："+executorService.getPoolSize());
		}
	}
}
