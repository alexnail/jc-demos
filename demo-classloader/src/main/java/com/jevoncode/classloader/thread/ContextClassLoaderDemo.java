package com.jevoncode.classloader.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * vm args:-verbose:class 
 * 此demo只能在Eclipse下运行，不然就要修改代码
 * @author JevonCode
 * 
 */
public class ContextClassLoaderDemo {

	public static void main(String[] args) throws MalformedURLException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException, InterruptedException {
		System.out.println("ContextClassLoaderDemo -->"
				+ ContextClassLoaderDemo.class.getClassLoader()); // AppClassLoader
		System.out.println(Thread.currentThread().getName() + " -->"
				+ Thread.currentThread().getContextClassLoader());// AppClassLoader
		
		URL urls[] = new URL[] { new URL("file:/"
				+ System.getProperty("user.dir").replace("classloader", "context")+"/target/classes/") }; //注意最后是以“/”结尾，不然会ClassNotFoundException
//		URL urls[] = new URL[] { new URL(ContextClassLoaderDemo.class.getClassLoader().getResource(".").toString()) };
		// URL urls[] = new URL[] { new URL("file:/D:") };
		System.out.println(urls[0]);
		System.out.println(ContextClassLoaderDemo.class.getClassLoader().getResource(".").toString());
		MyClassLoader myClassLoader = new MyClassLoader(urls);
		Thread.currentThread().setContextClassLoader(myClassLoader);

		System.out.println(Thread.currentThread().getName() + " -->"
				+ Thread.currentThread().getContextClassLoader());// MyClassLoader  并不知道哪里使用上了

		System.out.println("ClassWhichStillLoadedByAppClassLoader -->"
				+ ClassWhichStillLoadedByAppClassLoader.class.getClassLoader());// AppClassLoader

		
		//调用demo-context项目下的ThreadA类，自己领悟：这样这个demo-classloader编译成字节码时，完全不需要知道demo-context项目的存在。
		Class<?> clazz = myClassLoader
				.loadClass("com.jevoncode.context.classloader.thread.ThreadA");  //这里因为明确使用MyClassLoader，所以ThreadA线程的ClassLoader也是MyClassLoader
		Method method = clazz.getMethod("start", null);
		method.invoke(clazz.newInstance(), null);
		
		Thread.sleep(2000);
		
		//使用自己的ThreadA类
		System.out.println("=============use own ThreadA ============");
		new ThreadA().start();
	}
}
