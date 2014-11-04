package com.jevoncode.jmx.standard;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
/**
 * VM args:-Dcom.sun.management.jmxremote
 * @author jevoncode
 *
 */
public class Main {
	public static void main(String[] args) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		//The object name is an instance of the JMX class ObjectName and must conform to the syntax defined by the JMX specification.
		//just a domain(a name,you can type anything, but it's better to define com.jevoncode.jmx.standard the package in which the Hello MBean is contained)
		ObjectName name = new ObjectName("com.jevoncode.jmx.standard:type=Hello");  
		Hello mbean = new Hello();
		mbs.registerMBean(mbean, name);
		System.out.println("Waiting forever...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
