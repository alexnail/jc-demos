package com.jevoncode.proxy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        JcService jcService = (JcService) TransactionWrapper.decorate(new JcServiceImpl());
        JcService jcService = ServiceFactory.getJcService();
        jcService.doSomething();
    }
}
