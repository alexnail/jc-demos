package com.jevoncode.javatutorial;

/**
 * writes by jevoncode to demonstrate the usage of Synchronized
 * Conclusion:In this example, object Demo(notice, not class Demo) has an intrinsic lock associated with it.
 * As long as a thread owns an intrinsic lock, no other thread can acquire the same lock.  
 * So when one Thread call method getName, other Thread will not allow to call any Synchronized method in the object Demo. But 
 * especially, normal method can be called in any time. 
 * 演示Synchronized的用法
 * 结论：在这个例子中，对象Demo（注意，不是类Demo），对象拥有一个内置锁
 * 只要一个线程获取这个锁，没有其他线程可以获得这个锁
 * 所以当一个线程调用getName方法时，其他线程将不允许调用任何该对象Demo中的同步方法。
 * 这里特别指出，没有同步的方法，任何时候都可以被调用。
 * @author Jevoncode
 *
 */
public class SynchronizedDemo {

	static class Demo {
		private String name;
		private int count;

		public Demo(String name, int count) {
			super();
			this.name = name;
			this.count = count;
		}

		public synchronized String getName() {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				System.out.println("Thread a: I am interrupted. :(");
			}
			return this.name;
		}

		public synchronized int getCount() {
			return this.count;
		}

		public String getObjectInfo() {
			return "Demo [name=" + name + ", count=" + count + "]";
		}
		
	}

	public static void main(String[] args) {
        long patience = 1000 * 10;
		final Demo demo = new Demo("JEVONCODE", 0);
        long startTime = System.currentTimeMillis();
		Thread a = new Thread(new Runnable() {
			public void run() {
				System.out.println("thread a: the name="+demo.getName());
			}
		});
		a.start();
		Thread b = new Thread(new Runnable() {
			public void run() {
				System.out.println("thread b: the count="+demo.getCount());
			}
		});
		b.start();
		Thread c = new Thread(new Runnable() {
			public void run() {
				System.out.println("thread c: "+demo.getObjectInfo());
			}
		});
		c.start();
		while(b.isAlive()){
			System.out.println("Thread b: I am waiting Thread a finish it's job.");
			try {
				a.join(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((System.currentTimeMillis()-startTime)>patience&&a.isAlive()){
				System.out.println("Thread b: I no long wait. I must stop it.");
				a.interrupt();
			}
		}
		System.out.println("main finish");
	}
}
