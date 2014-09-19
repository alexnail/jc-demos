package com.jevoncode.introduction;

/**
 * 当程序以server模式运行时，即使主线程done变量的值设为true，第二线程也无法看到该变量的变化，这种现象是由于Java server JIT编译器优化所导致的。
 * 解决此编译问题可以将变量done标记为volatile
 * 另外关联知识点：
 * 	内存模型 内存栅栏（Memory Barrier） 可参阅Brian Goetz的著作《Java Concurrency in Practice》
 * @author Jevoncode
 *
 */
public class RaceCondition {
	private static volatile boolean done;
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			
			public void run() {
				int i =0 ;
				while(!done){i++;}
				System.out.println("Done!");
			}
		}).start();
		System.out.println("OS:"+System.getProperty("os.name"));
		Thread.sleep(2000);
		done =true;
		System.out.println("flag done set to true");
	}
}
