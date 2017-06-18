package com.ibm.javanet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress allByName = InetAddress.getByName("localhost");
		System.out.println(allByName);
	}
}
