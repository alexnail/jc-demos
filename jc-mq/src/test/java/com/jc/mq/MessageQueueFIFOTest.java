package com.jc.mq;

public class MessageQueueFIFOTest {
	public static void main(String[] args) {
		MessageQueueFIFO queue = new MessageQueueFIFO();
		Producer producer = new Producer(queue);
		producer.start();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Consumer consumer = new Consumer(queue);
			consumer.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
