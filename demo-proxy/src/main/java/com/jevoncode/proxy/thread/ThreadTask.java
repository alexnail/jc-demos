package com.jevoncode.proxy.thread;

import java.lang.reflect.Proxy;

import com.jevoncode.proxy.TransactionWrapper;

public class ThreadTask extends Thread{

	@Override
	public void run() {
		System.out.println("running threadTask, sleeping 3 seconds");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ThreadService threadService = (ThreadService) TransactionWrapper.decorate(new ThreadServiceImpl()); // with XA
		ThreadService threadService = new ThreadServiceImpl(); //no XA
		threadService.threadDoSomeThing();
	}

	
}
