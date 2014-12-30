package com.jevoncode.proxy;

import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException
    {
        System.out.println( "Hello World!" );
//        JcService jcService = (JcService) TransactionWrapper.decorate(new JcServiceImpl());
        JcService jcService = ServiceFactory.getJcService();
        jcService.doSomething();
    }
}
