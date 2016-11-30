package com.lydck.javanet;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.URL;

import javax.swing.JFrame;

public class SecureSourceViewer {
	public static void main(String[] args) throws Exception {
		Authenticator.setDefault(new DialogAuthenticator("lydck", new JFrame()));
		URL u = new URL("https://shimo.im/");
		try(InputStream in = new BufferedInputStream(u.openStream())) {
			Reader r = new InputStreamReader(in);
			int c;
			while((c = r.read()) != -1) {
				System.out.print((char)c);
			}
		}
	}
}
