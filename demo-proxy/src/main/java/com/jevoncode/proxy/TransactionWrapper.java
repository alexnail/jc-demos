package com.jevoncode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class TransactionWrapper {

	public static Object decorate(Object delegate){
		return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),//
				delegate.getClass().getInterfaces(), new XAWrapperHandler(delegate));
	}
	
	
	static class XAWrapperHandler implements InvocationHandler{
		private final Object delegate; 
		public XAWrapperHandler(Object delegate){
			this.delegate = delegate;
		}
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			try{
			System.out.println("start transcation "+Thread.currentThread().getName());
			result = method.invoke(delegate, args);
			System.out.println("end transcation "+Thread.currentThread().getName());
			}catch(Throwable t){
				System.out.println("rollback transaction");
				throw t;
			}
			return result;
		}
	}

}


