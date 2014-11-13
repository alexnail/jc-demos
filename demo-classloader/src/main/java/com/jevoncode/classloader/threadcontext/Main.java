package com.jevoncode.classloader.threadcontext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(Main.class.getResource("."));
		URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { new File("D:/").toURI().toURL() }, null);
		Thread.currentThread().setContextClassLoader(urlClassLoader);
//		Test.test();
//		new Test().test2();
		Class<?> clazz = (Class<?>) Class.forName("com.jevoncode.classloader.threadcontext.Test", true, urlClassLoader);
		ITest itest = ((ITest) clazz.newInstance());
	}
}
