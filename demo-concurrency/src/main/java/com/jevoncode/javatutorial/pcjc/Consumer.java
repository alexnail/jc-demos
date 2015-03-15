package com.jevoncode.javatutorial.pcjc;

import java.util.Random;

public class Consumer implements Runnable {
	private Drop drop;

	public Consumer(Drop drop) {
		this.drop = drop;
	}

	public void run() {
		Random random = new Random();
		for (String message = drop.take(); !message.equals("DONE"); message = drop
				.take()) {
			try {
				Thread.sleep(random.nextInt(100));
			} catch (InterruptedException e) {
			}
			if ("empty".equals(message))
				continue;
			System.out.format(Thread.currentThread().getName()
					+ ":MESSAGE RECEIVED: %s%n", message);
		}
	}
}
