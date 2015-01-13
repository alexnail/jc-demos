package com.jevoncode.proxy;

public class Connection {
	public static int count = 0;

	private String name;

	public Connection() {
		count++;
		name = "conn" + count;
	}

	public void close() {
		System.out.println(name+ " close connection " + Thread.currentThread().getName());
	}

	public void rollback() {
		System.out.println(name+ " rollback transaction " + Thread.currentThread().getName());
	}

	public void commit() {
		System.out.println(name+ " end transcation " + Thread.currentThread().getName());
	}

	public void setAutoCommit(boolean b) {
		System.out.println(name+ " start transcation " + Thread.currentThread().getName());
	}

}
