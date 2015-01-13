package com.jevoncode.proxy;

public class ServiceFactory {
	public static JcService getJcService(){
		return (JcService) TransactionWrapper.decorate(new JcServiceImpl());
	}
}
