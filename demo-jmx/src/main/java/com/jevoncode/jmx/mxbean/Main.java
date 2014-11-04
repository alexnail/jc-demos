package com.jevoncode.jmx.mxbean;

import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
/**
 *  
 * @author jevoncode
 * 暂时的理解就是，JMX提供了一个规范，然后自己实现MXBean，然后注册进MB Server里，而这个MXBean可以控制你想要控制的对象，然后通过MB Server操作MXBean，从而控制想要控制的对象。
 */
public class Main {
	public static void main(String[] args) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ObjectName mxbeanName = new ObjectName("com.jevoncode.jxm.mxbean:type=QueueSampler");

		Queue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Request-1");
		queue.add("Request-2");
		queue.add("Request-3");
		QueueSampler mxbean = new QueueSampler(queue);

		mbs.registerMBean(mxbean, mxbeanName);

		System.out.println("Waiting...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
