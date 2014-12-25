package com.jevoncode.proxy.thread;

public class ThreadServiceImpl implements ThreadService{

	public void threadDoSomeThing() {
		System.out.println("thread do some thing");
		//int i=1/0; //rollback event
	}

}
