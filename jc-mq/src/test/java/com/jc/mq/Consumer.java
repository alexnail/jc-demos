package com.jc.mq;

import java.util.Random;

public class Consumer extends Thread {

	private MessageQueueFIFO queue;

	public Consumer(MessageQueueFIFO queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		Random r = new Random();
		long millis = 1000 * r.nextInt(10);
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message message = queue.pop();
		System.out.println("消费者取出" + message.toString());
	}

}
