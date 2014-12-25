package com.jevoncode.proxy;

import com.jevoncode.proxy.thread.ThreadTask;

public class JcServiceImpl implements JcService {

	public void doSomething() {
		System.out.println("do something");
		// int i=1/0; //rollback event
		Thread t = new ThreadTask();
		t.start();
	}

}
