package com.jevoncode.classloader.threadcontext;

public class Test implements ITest{
	public static void test() {
		System.out.println(Test.class.getClassLoader());
	}
	public void test2() {
		System.out.println(Test.class.getClassLoader());
	}
}
