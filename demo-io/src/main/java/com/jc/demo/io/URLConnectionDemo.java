package com.jc.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import sun.net.www.content.text.PlainTextInputStream;

public class URLConnectionDemo {
	public static void main(String[] args) throws URISyntaxException {
		try {
			URL url = new URL("file:/E:/testlogs.txt");  //获取流的class为 java.io.BufferedInputStream，这是由URLStreamHandler去确定什么协议，用什么流
//			URL url = URLConnectionDemo.class.getClassLoader().getResource("test.xml");//获取流的classclass为 java.io.BufferedInputStream 这是由URLStreamHandler去确定什么协议，用什么流
//			URL url = new URL("http://baidu.com");  //获取流的class为sun.net.www.protocol.http.HttpURLConnection$HttpInputStream
			System.out.println(url.toURI());
			URLConnection conn  = url.openConnection();
			InputStream in = conn.getInputStream();
			System.out.println(in.getClass());
			in.close();
//			Object o = url.getContent();  //获取内容，根据不同的资源返回不同对象有PlainTextInputStream
//			System.out.println(((PlainTextInputStream)o).);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
