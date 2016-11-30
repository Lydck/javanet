package com.lydck.javanet;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ibm.javanet.util.DateUtil;

public class LastModified {
	public static void main(String[] args) throws Exception {
		URL u = new URL("http://localhost/spring-mvc/index.jsp");
		HttpURLConnection http = (HttpURLConnection)u.openConnection();
		http.setRequestMethod("HEAD");
		System.out.println(DateUtil.getDate(http.getLastModified()));
		System.out.println(http.usingProxy());
		System.out.println(http.getResponseCode());
		System.out.println(http.getResponseMessage());
		Map<String, List<String>> headerFields = http.getHeaderFields();
		Set<Entry<String, List<String>>> entrySet = headerFields.entrySet();
		for (Entry<String, List<String>> entry : entrySet) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
