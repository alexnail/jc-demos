package com.jevoncode.javatutorial.pcjc;

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
//		System.out.println(Thread.currentThread().getName()+" enter the take");
		if(empty)
			return "empty";
		// Toggle status.
		empty = true;
		// Notify producer that
		// status has changed.
		return message;
	}

	public synchronized void put(String message) {
		System.out.println(Thread.currentThread().getName()+" enter the put");
		if(!empty)
			return ;
		// Toggle status.
		empty = false;
		// Store message.
		this.message = message;
		// Notify consumer that status
		// has changed.
		System.out.println(Thread.currentThread().getName()+" put the message");
	}
}
