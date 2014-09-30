package com.jeovncode.scalabilityAndTreadSafety.coordinating;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTotalFileSize {
	class SubDirectoriesAndSize {
		final public long size;
		final public List<File> subDirectories;

		public SubDirectoriesAndSize(final long totalSize, final List<File> theSubDirs) {
			size = totalSize;
			subDirectories = theSubDirs;
		}
	}

	private SubDirectoriesAndSize getTotalAndSubDirs(final File file) {
		long total = 0;
		final List<File> subDirectories = new ArrayList<File>();
		if (file.isDirectory()) {
			final File[] children = file.listFiles();
			if (children != null)
				for (File child : children)
					if (child.isFile())
						total += child.length();
					else
						subDirectories.add(child);
		}
		return new SubDirectoriesAndSize(total, subDirectories);
	}

	private long getTotalSizeOfFilesInDir(final File file) {
		final ExecutorService executorService = Executors.newFixedThreadPool(100);
		try {
			long total = 0;
			final List<File> directories = new ArrayList<File>();
			directories.add(file);
			while (!directories.isEmpty()) {
				final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<Future<SubDirectoriesAndSize>>();
				for (final File directory : directories)
					partialResults.add(executorService.submit(new Callable<SubDirectoriesAndSize>() {
						public SubDirectoriesAndSize call() throws Exception {
							return getTotalAndSubDirs(directory);
						}
					}));
				directories.clear();
				for (final Future<SubDirectoriesAndSize> partialResultFuture : partialResults) {
					final SubDirectoriesAndSize subDirectoriesAndSize = partialResultFuture.get(100, TimeUnit.SECONDS);
					directories.addAll(subDirectoriesAndSize.subDirectories);
					total += subDirectoriesAndSize.size;
				}
			}
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
		return 0;
	}

	public static void main(String[] args) {

		final long start = System.nanoTime();
		final long total = new ConcurrencyTotalFileSize().getTotalSizeOfFilesInDir(new File(args[0]));
		final long end = System.nanoTime();
		System.out.println(args[0]+" Total size:" + total);
		System.out.println("Time taken:" + (end - start) / 1.0e9);
	}
}
