package com.lydck.javanet;

import java.net.Socket;

public class LowPortScanner {
	public static void main(String[] args) {
		for(int i = 550; i < 1024; i++) {
			try(Socket socket = new Socket("localhost", i);) {
				System.out.println("There is a server on port: " + i + socket);
				System.out.println(socket.getReceiveBufferSize());
				System.out.println(socket.getSendBufferSize());
				System.out.println(socket.getTrafficClass());
			} catch (Exception e) {
				System.out.println("No port: " + i);
				continue;
			}
			
		}
		
	}
}
