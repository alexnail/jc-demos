package com.jevoncode.spring.MethodInvokingFactoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/jevoncode/spring/MethodInvokingFactoryBean/spring.xml");
		MyBean myBean = (MyBean) ctx.getBean("myBean");
		System.out.println(myBean);
	}
}
