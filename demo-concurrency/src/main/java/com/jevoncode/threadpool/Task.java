package com.jevoncode.threadpool;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 使用线程池执行该任务
 * @author jevoncode
 *
 */
public class Task implements Runnable{

	public static Logger log = Logger.getLogger(Task.class);
	public int i;
	public Task(int i) {
		super();
		this.i = i;
	}
	public void run() {
		log.debug(Thread.currentThread().getName()+" is running!---Task"+i);
		
		//占10M内存,奇怪,加了这句,执行该任务的线程就去跑去执行队列里的其他任务,这是为什么呢???????
		//不加则执行该任务的线程就会继续sleep,不会理会队列里的任务,直至内存溢出
		byte[] block = new byte[1024*1024*10]; 
		
//		byte[] block = new byte[1024*1024*1]; //但如果加这句,1M内存的，不会造成10M那条语句的问题。
		Random r = new Random();
		long sleep = r.nextInt(20)*1000;
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
