package com.jevoncode.classloader.thread;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {

	public MyClassLoader(URL[] urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}

	// 在《深入理解Java虚拟机》这本书中，P233提到说JDK1.2之后已不提倡用户再覆盖loadClass()方法,
	// 而应当把自己的类加载逻辑写到findClass方法中
	// 在loadClass方法的逻辑里如果父类加载失败, 则会调用自己的findClass()方法加载,
	// 这样就可以保证信息出来的类加载器是符合双亲委派规则的
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// 复写这个loadClass方法就是为了破坏双亲委派规则，由URLClassLoader来加载
		if (name.startsWith("com.jevoncode"))
			return findClass(name);
		return super.loadClass(name);
	}
}
