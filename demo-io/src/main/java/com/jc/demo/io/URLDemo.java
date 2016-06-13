package com.jc.demo.io;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {
	public static void testProtocol(String url) {
		try {
			URL u = new URL(url);
			System.out.println(u.getProtocol() + " is supported");
		} catch (MalformedURLException e) {
			String protocol = url.substring(0, url.indexOf(":"));
			System.out.println(protocol + " is not supported");
		}
	}

	public static void main(String[] args) {
		// 超文本协议
		testProtocol("http://www.jevoncode.com");
		
		// 安全http
		testProtocol("https://www.jevoncode.com");
		
		// 文件传输协议
		testProtocol("ftp://jevoncode.com/upload");
		
		// 简单邮件传输协议
		testProtocol("mailto:jc@jevoncode.com");
		
		// telnet
		testProtocol("telnet://jevoncode.com");
		// 本地文件访问
		testProtocol("file://etc/passwd");
		// goher
		testProtocol("goher://jevoncode.com");
		// 轻量组目录访问协议
		testProtocol("ldap://jevoncode.com/dbconnection");
		// jar
		testProtocol("jar:jevoncode.com/jc.jar");
		// NFS，网络文件系统
		testProtocol("nfs://jevoncode.com/usr/tmp");
		//jdbc的定制协议
		testProtocol("jdbc:msyql://jevoncode.com:3306/jc");
		//rmi 远程方法调用协议	
		testProtocol("rmi://jevoncode.com/handler");
		//HotJava的定制协议
		testProtocol("doc:/UserGuide/jc.html");
		testProtocol("netdoc:/UserGuide/jc.html");
		testProtocol("systemresource://www.jevoncode.com/+/index.html");
		testProtocol("verbatim:http://www.jevoncode.com");

	}
}
