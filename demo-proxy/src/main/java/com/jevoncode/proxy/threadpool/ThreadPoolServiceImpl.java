package com.jevoncode.proxy.threadpool;

public class ThreadPoolServiceImpl implements ThreadPoolService{

	public int threadDoSomeThing() {
		System.out.println("threadpool do some thing");
//		int i=1/0; //rollback event
		return 1;
	}

}
