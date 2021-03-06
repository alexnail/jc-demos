package com.jevoncode.scalabilityAndTreadSafety.coordinating;

import java.io.File;
import java.util.ArrayList;  
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NaivelyConcurrencyTotalFileSize {

	private long getTotalSizeOfFile(final ExecutorService service, File file) throws InterruptedException, ExecutionException, TimeoutException {
		if (file.isFile())
			return file.length();
		long total = 0;
		final File[] children = file.listFiles();

		if (children != null) {
			final List<Future<Long>> partialTotalFutures = new ArrayList<Future<Long>>();
			for (final File child : children)
				partialTotalFutures.add(service.submit(new Callable<Long>() {
					public Long call() throws Exception {
						return getTotalSizeOfFile(service, child);
					}
				}));

			for (final Future<Long> partialTotalFuture : partialTotalFutures)
				total += partialTotalFuture.get(10, TimeUnit.SECONDS);
		}

		return total;
	}

	private long getTotalSizeOfFile(final String fileName) {
		final ExecutorService service = Executors.newFixedThreadPool(100);
		try {
			return getTotalSizeOfFile(service, new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
		return 0;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(10000);
		final long start = System.nanoTime();
		final long total = new NaivelyConcurrencyTotalFileSize().getTotalSizeOfFile(args[0]);
		final long end = System.nanoTime();
		System.out.println("Total size:"+total);
		System.out.println(args[0]+" Total size:" + total);
		System.out.println("Time taken:"+(end-start)/1.0e9);
	}

}
