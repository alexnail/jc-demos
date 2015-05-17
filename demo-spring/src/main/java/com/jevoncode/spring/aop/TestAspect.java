package com.jevoncode.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAspect {
	Logger log = LoggerFactory.getLogger(TestAspect.class);
	public void doAfter(JoinPoint jp) {
		System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName());
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;;
		System.out.println(" method: " + pjp.getTarget().getClass().getName() + "."
				+ pjp.getSignature().getName()+" process time: " + time + " ms");
		return retVal;
	}

	public void doBefore(JoinPoint jp) {
		System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName());
		Object[] args = jp.getArgs();
		for(Object arg:args){
			System.out.println("请求参数为："+arg);
		}
	}

	public void doThrowing(JoinPoint jp, Throwable ex) {
		System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()
				+ " throw exception");
//		System.out.println(ex);
		log.error("出现异常",ex);
	}

	private void sendEx(String ex) {
		// TODO 发送短信或邮件提醒
	}
}
