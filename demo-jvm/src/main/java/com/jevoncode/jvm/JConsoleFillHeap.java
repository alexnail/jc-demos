package com.jevoncode.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args:-Xms100m -Xmx100m -XX:+UseSerialGC -Dcom.sun.management.jmxremote
 * 
 * @author jevoncode
 * 
 */
public class JConsoleFillHeap {
	/**
	 * 内存占位符对象,一个OOMObject大约占64KB
	 * 
	 */
	static class OOMObject {
		public byte[] placeholder = new byte[64 * 1024];
	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i = 0; i < num; i++) {
			// 稍作延迟,令监视曲线的变化更加明显
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(30000);
		fillHeap(10000);
	}
}
