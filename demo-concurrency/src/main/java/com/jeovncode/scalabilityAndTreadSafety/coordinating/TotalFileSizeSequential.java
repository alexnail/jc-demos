package com.jeovncode.scalabilityAndTreadSafety.coordinating;

import java.io.File;

public class TotalFileSizeSequential {
	private long getTotalSizeOfFilesInDir(final File file) {
		if (file.isFile())
			return file.length();
		final File[] children = file.listFiles();
		long total = 0;
		if (children != null)
			for (final File child : children)
				total += getTotalSizeOfFilesInDir(child);
		return total;
	}

	public static void main(String[] args) {
		final long start = System.nanoTime();
		final long total = new TotalFileSizeSequential().getTotalSizeOfFilesInDir(new File(args[0]));	
		final long end = System.nanoTime();
		System.out.println("Total size:"+total);
		System.out.println(args[0]+" Total size:" + total);
		System.out.println("Time taken:"+(end-start)/1.0e9);
	}
}
