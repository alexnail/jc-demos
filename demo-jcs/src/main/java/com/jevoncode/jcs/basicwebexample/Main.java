package com.jevoncode.jcs.basicwebexample;

public class Main {
	public static void main(String[] args) {
		BookVObjManager bookVObjManager = BookVObjManager.getInstance();
		int i= 0;
		while(true){
			bookVObjManager.getBookVObj(i++);
		}
	}
}
