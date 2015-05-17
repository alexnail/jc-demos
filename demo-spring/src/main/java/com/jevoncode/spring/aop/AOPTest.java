package com.jevoncode.spring.aop;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.jevoncode.spring.aop.service.AService;
import com.jevoncode.spring.aop.service.impl.BServiceImpl;
import com.jevoncode.spring.aop2.service.AService2;

public class AOPTest extends AbstractDependencyInjectionSpringContextTests {

	private AService aService;

	private BServiceImpl bService;

	private AService2 aService2;

	protected String[] getConfigLocations() {
		String[] configs = new String[] { "com/jevoncode/spring/aop/applicationContext.xml" };
		return configs;
	}

	/** 
	 * 测试正常调用 
	 */
	public void testCall() {
		System.out.println("SpringTest JUnit test");
		aService.fooA("JUnit test fooA");
		System.out.println("===========================================================");
		aService.barA();
		System.out.println("===========================================================");
		bService.fooB();
		System.out.println("===========================================================");
		bService.barB("JUnit test barB", 0);
		System.out.println("===========================================================");
		aService2.fooA("JUnit test fooA2");
		System.out.println("===========================================================");
		aService2.barA();
		System.out.println("===========================================================");
	}

	/** 
	 * 测试After-Throwing 
	 */
	public void testThrow() {
		try {
			 bService.barB("JUnit call barB",1);
		} catch (IllegalArgumentException e) {

		}
	}

	public void setAService(AService service) {
		aService = service;
	}

	public void setBService(BServiceImpl service) {
		bService = service;
	}

	public void setaService2(AService2 aService2) {
		this.aService2 = aService2;
	}

}