package com.ibm.javanet.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 列出所有网络接口
 * @author Lydck
 *
 */
public class InterfaceLister {
	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()) {
			NetworkInterface ni = interfaces.nextElement();
			System.out.println(ni);
		}
	}
}
