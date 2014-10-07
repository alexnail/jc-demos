package com.jevoncode.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致的内存溢出溢出 VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * 常连池使属于方法区的一部分
 * @author JevonCode
 * 
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		// 使用List保持着常量池引用,避免Full GC回收常连池行为
		List<String> list = new ArrayList<String>();
		// 10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
