package com.jevoncode.classloader.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallMethodUsingByNullObjectDemo {
	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		System.out.println(System.getProperty("user.dir")); //project dir

		Class<?> paramTypes[] = new Class[1]; // from now, we just define an
												// object array, but we didn't
												// assign any values, so
												// paramTypes[0] is null;
		paramTypes[0] = String.class;

		Object paramValues[] = new Object[1];
		paramValues[0] = null; // NOTE it's null!!!!

		Class<?> clazz = Class
				.forName("com.jevoncode.classloader.reflect.IAmAnObject");
		Method method = clazz.getMethod("thisIsMethod", paramTypes);
		method.invoke(null, paramValues); // NOTE it's null!!!!

	}
}
