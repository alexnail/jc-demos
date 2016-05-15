package com.jc.customer;

import java.util.Random;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jc.spi.DemoService;

public class Customer {

	public static void main(String[] args) throws InterruptedException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("customer.xml");
		for (int i = 1; i < 10000; i++) {
			// Thread th1 = new Thread(new Task(context));
			DemoService demo = (DemoService) context.getBean("demoService");
			int r = new Random().nextInt(10);
			System.out.println("sleep "+r+"s");
			Thread.sleep(1000*r);
			System.out.println(demo.sayHello("jevoncode"));
		}
	}

}

class Task implements Runnable {
	private AbstractApplicationContext context;

	public Task(ClassPathXmlApplicationContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		DemoService demo = (DemoService) context.getBean("demoService");
		while (true)
			System.out.println(demo.sayHello("jevoncode"));
	}
}
