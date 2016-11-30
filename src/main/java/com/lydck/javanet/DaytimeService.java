package com.lydck.javanet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeService {
	public final static int PORT = 14;
	public static void main(String[] args) throws IOException {
		try(ServerSocket server = new ServerSocket(PORT)) {
			while(true) {
				try(Socket connection = server.accept()){
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					Date date = new Date();
					out.write(date.toString() + "\r\n");
					out.flush();
					out.close();
				}
			}
		}
	}
}
