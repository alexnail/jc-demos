package com.jevoncode.jvm;

/**
 * 测试引用计算法,testGC()方法执行后,并没有因为这两个对象互相引用就不回收它们
 * VM Args:-XX:+PrintGCDetails
 * @author JevonCode
 * 
 */
public class ReferenceCountingGC {
	public Object instance = null;
	private static final int _1MB = 1024 * 1024;

	private byte[] bigSize = new byte[2 * _1MB];

	public static void testGC() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;
		objB = null;
		// 假设在这行发生GC,objA和objB是否能被回收?
		System.gc();
	}

	public static void main(String[] args) {
		testGC();
	}

}
