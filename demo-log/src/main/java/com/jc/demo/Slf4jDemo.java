package com.jc.demo;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo {

	public static void main(String[] args) {
		WorkerJobs jobs = new WorkerJobs();
		for (int i = 0; i < 10000; i++) {
			new Thread(new Worker(jobs)).start();
			;
		}
	}

}

class Worker implements Runnable {
	WorkerJobs jobs;

	public Worker(WorkerJobs jobs) {
		this.jobs = jobs;
	}

	@Override
	public void run() {
		while (true) {
			Method[] method = jobs.getClass().getDeclaredMethods();
			Random r = new Random();
			boolean flag = true;
			while (flag) {
				int i = r.nextInt(method.length);
				if (method[i].getName().equals("sleep"))
					continue;
				try {
					System.out.println(method[i].getName());
					method[i].invoke(jobs, new Object[] {});
					flag = false;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private double getDirSize(String string) {
		File file = new File(string);
		// 判断文件是否存在
		if (file .exists()) {
			// 如果是目录则递归计算其内容的总大小
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				double size = 0;
				for (File f : children)
					size += getDirSize(f.getAbsolutePath());
				return size;
			} else {// 如果是文件则直接返回其大小,以“G”为单位
				double size = (double) file.length() / 1024 / 1024 /1024;
				return size;
			}
		} else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！"+string);
			return 0.0;
		}
	}

}

class WorkerJobs {
	Logger logger = LoggerFactory.getLogger(WorkerJobs.class);

	public void work1() {
		Random r = new Random();
		logger.info("work1 begin");
		long sleepTime = r.nextInt(3600);
		int time = r.nextInt(10);
		for (int i = 0; i < time; i++)
			logger.info("work1..。");
		sleep(sleepTime);
		logger.info("work1 end");
	}

	private void sleep(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void work2() {
		Random r = new Random();
		logger.info("work2 begin");
		long sleepTime = r.nextInt(3600);
		int time = r.nextInt(10);
		for (int i = 0; i < time; i++)
			logger.info("work2..。");
		sleep(sleepTime);
		logger.info("work2 end");

	}

	public void work3() {

		Random r = new Random();
		logger.info("work3 begin");
		long sleepTime = r.nextInt(3600);
		sleep(sleepTime);
		logger.info("work3 end");
	}

	public void work4() {
		Random r = new Random();
		logger.info("work4 begin");
		long sleepTime = r.nextInt(3600);
		sleep(sleepTime);
		logger.info("work4 end");

	}

	public void work5() {
		Random r = new Random();
		logger.info("work5 begin");
		int time = r.nextInt(10);
		for (int i = 0; i < time; i++)
			logger.info("work5..。");
		logger.info("work5 end");
	}
}
