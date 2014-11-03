package com.jevoncode.classloader.appclassloader;

/**
 * using defualt classloader AppClassLoader is static, it loaded class just once.
 * @author jevoncode
 *
 */
public class A {

	public void doSomething() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// the statement B b = new B() is semantically equivalent
		// B b = A.class.getClassLoader().loaderClass("B").newInstance()
		// B b = new B();
		System.out.println("Using ClassLoader="+ A.class.getClassLoader()+", and it's parent="+A.class.getClassLoader().getParent());
		B b = (B) A.class.getClassLoader().loadClass("com.jevoncode.classloader.appclassloader.B").newInstance();
		b.doSomethingElse();
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		A a = new A();
		while (true) {
			a.doSomething();
			Thread.sleep(3000);
		}
	}
}
