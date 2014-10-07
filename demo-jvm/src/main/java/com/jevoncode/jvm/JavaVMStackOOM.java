package com.jevoncode.jvm;

/**
 * 测试虚拟机栈和本地方法栈内存溢出 VM Args: -Xss2M
 * 
 * @author JevonCode
 * 
 */
public class JavaVMStackOOM {
	private void dontStop() {
		while (true) {

		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(10000);
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
