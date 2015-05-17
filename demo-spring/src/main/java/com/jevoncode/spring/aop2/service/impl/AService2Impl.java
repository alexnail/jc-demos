package com.jevoncode.spring.aop2.service.impl;

import com.jevoncode.spring.aop2.service.AService2;

public class AService2Impl implements AService2{
	@Override
	public void fooA(String _msg) {
		System.out.println("AService2Impl.fooA(msg:" + _msg + ")");

	}

	@Override
	public void barA() {
		  System.out.println("AService2Impl.barA()");  

	}
}
