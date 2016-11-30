package com.ibm.javanet.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLInputStream {
	public static void main(String[] args) {
		try {
			URL u = new URL("http://lydck@www.baidu.com?category=lydck#topicid=123");
			InputStream in = u.openStream();
			String line;
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader r = new BufferedReader(reader);
			while((line = r.readLine()) != null) {
				System.out.println(line);
			in.close();
			}
			/*System.out.println(u.getProtocol());
			System.out.println(u.getHost());
			System.out.println(u.getPort());
			System.out.println(u.getDefaultPort());
			System.out.println(u.getFile());
			System.out.println(u.getRef());
			System.out.println(u.getQuery());
			System.out.println(u.getUserInfo());
			System.out.println(u.getAuthority());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
