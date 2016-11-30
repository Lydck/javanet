package com.lydck.javanet;

import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaytimeClient {
	public Date getDateFromNetwork() throws Exception {
		try(Socket socket = new Socket("time.nist.gov", 13)) {
			socket.setSoTimeout(15000);
			StringBuilder time = new StringBuilder();
			InputStreamReader reader = new InputStreamReader(socket.getInputStream(), "ASCII");
			for(int c = reader.read(); c != -1; c = reader.read()) {
				time.append((char) c);
			}
			return parseDate(time.toString());
		}
	}
	static Date parseDate(String time) throws Exception {
		String[] pieces = time.split(" ");
		String dateTime = pieces[1] + " " + pieces[2] + " UTS";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.parse(dateTime);
	}
	public static void main(String[] args) throws Exception {
		DaytimeClient c = new DaytimeClient();
		Date time = c.getDateFromNetwork();
		System.out.println(time);
	}
}
