package com.jevoncode.proxy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.jevoncode.proxy.thread.ThreadTask;
import com.jevoncode.proxy.threadpool.ThreadPool;
import com.jevoncode.proxy.threadpool.ThreadPoolTask;

public class JcServiceImpl implements JcService {

	public void doSomething() throws InterruptedException, ExecutionException {
		System.out.println("do something");
		// int i=1/0; //rollback event
		//普通线程不影响主事务主事务的执行提交
//		Thread t = new ThreadTask();
//		t.start();
		
		//线程池线程,在不使用Future的get方法获取返回值时,是不会影响主事务的执行提交
//		ThreadPoolTask task = new ThreadPoolTask();
//		Future  f = ThreadPool.submit(task);
		
		//线程池线程,使用了Future的get方法获取返回值时,影响了主事务的执行
		//只有当该线程执行成功,并获取返回值后,主事务才会提交
		//这样就有一个问题,两个事务是开启
		ThreadPoolTask task = new ThreadPoolTask();
		Future  f=  ThreadPool.submit(task);
		System.out.println("thread pool result="+f.get());
		 
	}

}
