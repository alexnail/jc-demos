package com.jevoncode.tools;

public class JVMTool {
	public static void memoryStatus() {
		Runtime run = Runtime.getRuntime();
		long max = run.maxMemory();
		long total = run.totalMemory();
		long free = run.freeMemory();
		long usable = max - total + free;
		System.out.println("最大内存 = " + max / 1024 + "KB");
		System.out.println("已分配内存 = " + total / 1024 + "KB");
		System.out.println("已分配内存中的剩余空间 = " + free / 1024 + "KB");
		System.out.println("最大可用内存 = " + usable / 1024 + "KB");
	}

	public static String memoryStatusPlain() {
		Runtime run = Runtime.getRuntime();
		long max = run.maxMemory();
		long total = run.totalMemory();
		long free = run.freeMemory();
		long usable = max - total + free;
		StringBuffer sb = new StringBuffer();
		sb.append("最大内存 = " + max / 1024 + "KB\n");
		sb.append("已分配内存 = " + total / 1024 + "KB\n");
		sb.append("已分配内存中的剩余空间 = " + free / 1024 + "KB\n");
		sb.append("最大可用内存 = " + usable / 1024 + "KB\n");
		return sb.toString();
	}
}
