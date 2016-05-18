package com.jevoncode.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

/**
 * 试验下,如果队列的任务远远大于线程池及其处理速度会怎样?
 * 毫无意外:内存溢出 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * @author jevoncode
 * vm args:-Xms20m -Xmx20m
 */
public class RunOfThreadPoolDemo {
	public static Logger log = Logger.getLogger(RunOfThreadPoolDemo.class);
	public static void main(String[] args) throws InterruptedException {
//		Thread.sleep(10000);
//		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		int i=1;
		while(true){
//			if(i<10)
			Thread.sleep(1000);
			executorService.submit(new Task(i++));
			log.debug("线程队列大小 ："+executorService.getQueue().size());
		}
	}
}
