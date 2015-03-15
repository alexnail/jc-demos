package com.jevoncode.javatutorial.pc;

public class Drop {
	// Message sent from producer
	// to consumer.
	private String message;
	// True if consumer should wait
	// for producer to send message,
	// false if producer should wait for
	// consumer to retrieve message.
	private boolean empty = true;

	public  synchronized String take() {
		System.out.println(Thread.currentThread().getName()+" enter the take");
		// Wait until message is
		// available.
		while (empty) {
			try {
				System.out.println(Thread.currentThread().getName()+" wait...");
				wait();
				System.out.println(Thread.currentThread().getName()+" wake up!");
			} catch (InterruptedException e) {
			}
		}
		// Toggle status.
		empty = true;
		// Notify producer that
		// status has changed.
		notifyAll();
		return message;
	}

	public synchronized void put(String message) {
		System.out.println(Thread.currentThread().getName()+" enter the put");
		// Wait until message has
		// been retrieved.
		while (!empty) {
			try {
				System.out.println(Thread.currentThread().getName()+" wait...");
				wait();
				System.out.println(Thread.currentThread().getName()+" wake up!");
			} catch (InterruptedException e) {
			}
		}
		// Toggle status.
		empty = false;
		// Store message.
		this.message = message;
		// Notify consumer that status
		// has changed.
		notifyAll();
		System.out.println(Thread.currentThread().getName()+" put the message");
	}
}
