package com.jevoncode.classloader.thread;

import java.net.URLClassLoader;


/**
 * 为了测试ClassWhichShouldBeLoadedByURLClassloader这个类是否被urlClassLoader加载
 * 但实践结果，还是都是AppClassLoader去加载<br/>
 * 原因是双亲委派模型的工作结果,其过程下：如果一个类加载器收到了类加载器的请求，它首先不会自己去尝试加载这个类，
 * 而是<strong>把这个请求委派给父类加载去完成</strong>，每一个层次的类加载器都是如此。因此所有的加载请求
 * 最终都应该传送到顶层的启动类加载器中，只有当父加载器反馈自己无法完成这个加载请求<strong>（它的搜索范围内中没有找到所需的类）</strong>，
 * 子加载器才会尝试自己去加载。
 * @author JevonCode
 *
 */
public class ThreadA extends Thread{

 
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+" -->"+Thread.currentThread().getContextClassLoader());//MyClassLoader 子线程继承了父线程的ContextClassLoader
			System.out.println("ClassWhichShouldBeLoadedByURLClassloader -->"+ClassWhichShouldBeLoadedByMyClassloader.class.getClassLoader()); //AppClassLoader,并未使用上主线程设置的ContextClassLoader
			Class<?> clazz = Class.forName("com.jevoncode.context.classloader.thread.ClassWhichLoadedByURLClassloader",true,Thread.currentThread().getContextClassLoader()); 
			System.out.println(clazz.getClassLoader());//MyClassLoader  还是需要明确指示ClassLoader方能使用该ClassLoader
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
