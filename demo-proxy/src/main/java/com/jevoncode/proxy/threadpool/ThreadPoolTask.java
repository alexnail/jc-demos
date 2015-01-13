package com.jevoncode.proxy.threadpool;

import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

import com.jevoncode.proxy.TransactionWrapper;

public class ThreadPoolTask implements Callable {
 

	public Object call() throws Exception {
		System.out.println("running threadTask, sleeping 3 seconds");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ThreadPoolService threadService = (ThreadPoolService) TransactionWrapper.decorate(new ThreadPoolServiceImpl()); // with
																														// XA
		// ThreadService threadService = new ThreadServiceImpl(); //no XA
		return threadService.threadDoSomeThing();
	}

}
