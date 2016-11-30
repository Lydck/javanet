package com.lydck.javanet;

import java.net.URLEncoder;

public class QueryString {
	private StringBuilder query = new StringBuilder();
	public QueryString() {}
	public synchronized void add(String name, String value) {
		query.append('&');
		encode(name, value);
	}
	private synchronized void encode(String name, String value) {
		try {
			query.append(URLEncoder.encode(name, "UTF-8"));
			query.append('=');
			query.append(URLEncoder.encode(value, "UTF-8"));
		} catch(Exception e) {
			throw new RuntimeException("编码失败");
		}
	}
	public String getQuery() {
		return query.toString();
	}
	public String toString(){
		return getQuery();
	}
}
