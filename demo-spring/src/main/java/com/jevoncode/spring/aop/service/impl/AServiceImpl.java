package com.jevoncode.spring.aop.service.impl;

import com.jevoncode.spring.aop.service.AService;


public class AServiceImpl implements AService {

	@Override
	public void fooA(String _msg) {
		System.out.println("AServiceImpl.fooA(msg:" + _msg + ")");

	}

	@Override
	public void barA() {
		  System.out.println("AServiceImpl.barA()");  

	}

}
