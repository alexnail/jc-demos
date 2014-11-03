package com.jevoncode.classloader.dynamic.urlclassloader;
/**
 * use URLClassLoader can load class dynamically.
 * @author jevoncode
 *
 */
public class Main {
	private static ICounter counter2;
	private static ICounter counter1;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		counter1 = CounterFacotry.newInstance();
		while (true) {
			counter2 = CounterFacotry.newInstance();
			System.out.println("1) " + counter1.message() + " = " + counter1.plusPlus());
			System.out.println("2) " + counter2.message() + " = " + counter2.plusPlus());
			System.out.println();
			Thread.sleep(3000);
		}
	}
}
