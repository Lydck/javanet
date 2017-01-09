package com.lydck.javanet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.UnknownHostException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HTTPSClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		SocketFactory socketFactory = SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket) socketFactory.createSocket("www.usps.com", 443);
		String[] supported = socket.getSupportedCipherSuites();
		for (String support : supported) {
			System.out.println(support);
		}
		socket.setEnabledCipherSuites(supported);
		Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
		
		out.write("GET http://www.usps.com /HTTP/1.1\r\n");
		out.flush();
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String s;
		while((s = reader.readLine()) != null) {
			System.out.println(s);
		}
	}
}
