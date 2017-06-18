package com.ibm.javanet.thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipFile {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			GZipRunnable gZip = new GZipRunnable(new File("E:\\LOL_V3.2.1.3_FULL.7z.001"));
			service.submit(gZip);
		}
	}
}
