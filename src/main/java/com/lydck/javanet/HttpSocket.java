package com.lydck.javanet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpSocket {
	public static void main(String[] args) {
		try(ServerSocket http = new ServerSocket(80)){
			Socket accept = http.accept();
			System.out.println("请求来了:" + accept.getInetAddress().getLocalHost());
			OutputStream out = accept.getOutputStream();
			out.write(97);
			out.write(98);
			out.write(99);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
