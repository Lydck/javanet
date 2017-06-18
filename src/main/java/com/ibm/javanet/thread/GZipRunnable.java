package com.ibm.javanet.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {

	private final File file;

	public GZipRunnable(File file) {
		super();
		this.file = file;
	}

	public void run() {
		if (!file.getName().endsWith(".zip")) {
			File output = new File(file.getParent(), file.getName() + ".zip");
			if (!output.exists()) {
				try (InputStream in = new BufferedInputStream(new FileInputStream(file));
						OutputStream out = new BufferedOutputStream(
								new GZIPOutputStream(new FileOutputStream(output)));) {
					int b;
					while ((b = in.read()) != -1) {
						out.write(b);
					}
					out.flush();

				} catch (Exception e) {

				}

			}
		}
	}

}
