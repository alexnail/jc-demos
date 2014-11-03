package com.jevoncode.classloader.dynamic.urlclassloader;

public class Counter implements ICounter {
	private int counter;
	
	public String message() {
		return "version 1";
	}

	public int plusPlus() {
		return counter++;
	}

	public int getCounter() {
		return counter;
	}
	
}
