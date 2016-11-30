package com.lydck.javanet;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

public class SampleURLConn {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
//			URL url = new URL("http://localhost/spring-mvc/index.jsp");
			URL url = new URL("http://www.baidu.com");
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);
			OutputStream raw = uc.getOutputStream();
			BufferedOutputStream buffered = new BufferedOutputStream(raw);
			OutputStreamWriter out = new OutputStreamWriter(buffered, "iso-8859-1");
			out.write("?query='mobile'");
			out.flush();
			out.close();
			
			System.out.println(uc);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
