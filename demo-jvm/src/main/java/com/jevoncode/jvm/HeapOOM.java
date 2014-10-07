package com.jevoncode.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试内存溢出OutOfMemory VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @author Jevoncode
 * 
 */
public class HeapOOM {
	static class OOMObject {

	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}
}
