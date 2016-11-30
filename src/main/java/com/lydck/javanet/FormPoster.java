package com.lydck.javanet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class FormPoster {
	private QueryString query = new QueryString();
	private URL url;	
	public FormPoster(URL url) {
		if(!url.getProtocol().toLowerCase().startsWith("http")) {
			throw new IllegalArgumentException("只支持http协议的URL");
		}
		this.url = url;
	}
	public void add(String name, String value) {
		query.add(name, value);
	}
	public URL getURL() {
		return url;
	}
	public InputStream post() throws IOException {
		//打开连接，准备POST
		URLConnection uc = url.openConnection();
		uc.setDoOutput(true);
		uc.setRequestProperty("Accept", "application/xml");
		try(OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
			//POST行、Content-type首部和Content-length首部
			out.write(query.toString());
			out.write(query.toString());
			out.write("\r\n");
			out.flush();
		}
		//返回响应
		return uc.getInputStream();
	}
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost/spring-mvc/showUserList");
		FormPoster poster = new FormPoster(url);
		poster.add("username", "lydck");
		poster.add("password", "lydck123");
		poster.add("email", "yjxinag@cn.ibm.com");
		try(InputStream in = poster.post()){
			InputStreamReader reader = new InputStreamReader(in);
			int c;
			while((c = reader.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
		}
	}
}
