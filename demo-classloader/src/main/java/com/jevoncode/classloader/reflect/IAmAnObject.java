package com.jevoncode.classloader.reflect;

public class IAmAnObject {
	public void thisIsMethod(String value) {
		System.out.println(this.getClass().getName() + ":yeah~, it calls me!");
	}
}
