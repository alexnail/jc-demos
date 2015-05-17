package com.jevoncode.spring.aop.service.impl;

public class BServiceImpl {
	public void barB(String _msg, int _type) {
		System.out.println("BServiceImpl.barB(msg:" + _msg + " type:" + _type + ")");
		if (_type == 1) {
			int i=1/0;
//			throw new IllegalArgumentException("测试异常");
		}
	}

	public void fooB() {
		System.out.println("BServiceImpl.fooB()");
	}
}
