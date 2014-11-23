package com.jevoncode.context.classloader.thread;

import java.net.MalformedURLException;
import java.net.URL;



/**
 *  被demo-classloader所调用
 * @author JevonCode
 *
 */
public class ThreadA extends Thread{

 
	@Override
	public void run() {
		try {
			System.out.println("==============in new Thread which load by MyClassLoader============");
		System.out.println(Thread.currentThread().getName()+" -->"+Thread.currentThread().getContextClassLoader()); //MyClassLoader
		URL urls[];
			urls = new URL[] { new URL("file:/"+ System.getProperty("user.dir").replace("classloader", "context")+"/target/classes/") };//注意最后是以“/”结尾，不然会ClassNotFoundException
		System.out.println(urls[0]);
		MyOwnClassLoader myOwnClassLoader = new MyOwnClassLoader(urls);
		Thread.currentThread().setContextClassLoader(myOwnClassLoader); //事实证明设置了ContextClassLoader, 该线程加载类时，默认还是加载该线程的ClassLoader
		Class<?> clazz = Class.forName("com.jevoncode.context.classloader.thread.ClassWhichLoadedByURLClassloader",true,myOwnClassLoader);
		System.out.println("ClassWhichLoadedByURLClassloader -->"+clazz.getClassLoader()); //MyOwnClassLoader 这是因为明确指定ClassLoader, so,跟线程的setContextClassLoader没半毛钱关系
		System.out.println("ClassWhichLoadedByURLClassloader -->"+ClassWhichLoadedByURLClassloader.class.getClassLoader()); //MyClassLoader
		System.out.println(clazz.newInstance() instanceof ClassWhichLoadedByURLClassloader);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
