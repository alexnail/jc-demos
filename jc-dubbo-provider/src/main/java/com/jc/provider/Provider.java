package com.jc.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jc.spi.DemoService;

public class Provider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
		DemoService demo = (DemoService) context.getBean("demoService");
		while(true){}
	}
}
