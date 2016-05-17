package com.jc.mq;

import java.util.Random;

public class Producer extends Thread {
	private MessageQueueFIFO queue;

	public Producer(MessageQueueFIFO queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		Random r = new Random();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Message message = new Message();
			message.setContent("content-" + i);
			queue.push(message);
			long millis = 1000* r.nextInt(10);
			try {
				Thread.sleep(millis );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
