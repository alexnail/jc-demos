package com.jevoncode.tools;

public class ConcurrencyTool {
	public static int availableProcessors(){
		return Runtime.getRuntime().availableProcessors();
	}
	public static void main(String[] args) {
		System.out.println("Available Processors is "+ availableProcessors());
	}
}
