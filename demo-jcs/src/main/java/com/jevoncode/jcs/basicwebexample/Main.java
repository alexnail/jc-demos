package com.jevoncode.jcs.basicwebexample;
/**
 * VM args:-Dcom.sun.management.jmxremote
 * @author jevoncode
 *
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(15000);
		BookVObjManager bookVObjManager = BookVObjManager.getInstance();
//		BookVObjManagerDefault bookVObjManager = BookVObjManagerDefault.getInstance();
		int i= 0;
		while(true){
			bookVObjManager.getBookVObj(i++);
		}
	}
}
