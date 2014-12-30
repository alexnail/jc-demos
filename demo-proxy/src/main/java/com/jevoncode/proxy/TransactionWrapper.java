package com.jevoncode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class TransactionWrapper {

	public static Object decorate(Object delegate) {
		return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),//
				delegate.getClass().getInterfaces(), new XAWrapperHandler(delegate));
	}

	static class XAWrapperHandler implements InvocationHandler {
		private final Object delegate;

		public XAWrapperHandler(Object delegate) {
			this.delegate = delegate;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			try {
				TransactionDB.beginTransaction();
				result = method.invoke(delegate, args);
				TransactionDB.commitTransaction();
			} catch (Throwable t) {
				TransactionDB.rollbackTransaction();
				throw t;
			}
			return result;
		}
	}

}
