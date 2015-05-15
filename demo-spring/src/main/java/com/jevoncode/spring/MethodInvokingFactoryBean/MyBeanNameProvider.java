package com.jevoncode.spring.MethodInvokingFactoryBean;

public class MyBeanNameProvider {
	 public static String getName() {  
	        return "" + System.currentTimeMillis();  
	    }  
}
