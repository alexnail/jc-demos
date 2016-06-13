package com.jc.demo.io;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
	public static void main(String[] args) {
		String ip = "224.0.1.1";
		try {
//			InetAddress local = InetAddress.getLocalHost();
//			ip =local.getHostAddress(); //获取本地地址 局域网的
			InetAddress address = InetAddress.getByName(ip);
			if(address.isAnyLocalAddress()){
				System.out.println(address+" is a wildcard address."); // /0:0:0:0:0:0:0:0 is a wildcard address.
			} else
			if(address.isLoopbackAddress()){
				System.out.println(address+" is loopback address."); // /127.0.0.0 ~ 127.255.255.255 is loopback address.
			} else
			if(address.isLinkLocalAddress()){
				System.out.println(address+" is a link-local address."); // /169.254.0.0 ~ 169.254.255.255 is a link-local address.
			} else
			if(address.isSiteLocalAddress()){
				System.out.println(address+" is a site-local address."); // /10.0.0.0 ~ 10.255.255.255、172.16.0.0 ~ 172.31.255.255、192.168.0.0 ~ 192.168.255.255 is a site-local address.
			}else{
				System.out.println(address+" is a global address.");
			}
			
			if(address.isMulticastAddress()){//IPv4的广播地址的范围是224.0.0.0 ~ 239.255.255.255
				if(address.isMCGlobal()){
					System.out.println(address+" is a global multicast address."); //IPv4的广播地址除了224.0.0.0~255和第一个字节是239的IP地址都是全球范围的广播地址
				}else
				if(address.isMCOrgLocal()){
					System.out.println(address+" is an organization wide multicast address."); //IPv4的组织范围广播地址的第一个字节是239，第二个字节不小于192，第三个字节不大于195，如239.193.100.200、239.192.195.0都是组织范围广播地址
				}else
				if(address.isMCSiteLocal()){
					System.out.println(address+" is a site wide multicast address."); //IPv4的站点范围广播地址的范围是239.255.0.0 ~ 239.255.255.25
				}else
				if(address.isMCLinkLocal()){
					System.out.println(address+" is a subnet wide multicast address."); //IPv4的子网广播地址的范围是224.0.0.0 ~ 224.0.0.255
				}else
				if(address.isMCNodeLocal()){
					System.out.println(address+" is an interface-local multicast address."); //所有的IPv4广播地址都不是本地接口广播地址
				}else{
					System.out.println(address+" is an unknown multicast address type.");
				}
			}else{
				System.out.println(address+" is a unicast address.");
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
