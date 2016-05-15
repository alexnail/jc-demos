package com.jc.provider;

import com.jc.spi.DemoService;

public class DemoServiceImpl implements DemoService {
	int i = 0;

	@Override
	public String sayHello(String name) {
		System.out.println("call " + (i++) + " times");
		return "Hello " + name;
	}

}
