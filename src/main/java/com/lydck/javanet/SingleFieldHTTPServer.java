package com.lydck.javanet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleFieldHTTPServer {
	private static final Logger logger = Logger.getLogger("SingleFieldHTTPServer");
	
	private final byte[] content;
	private final byte[] header;
	private final int port;
	private final String encoding;
	public SingleFieldHTTPServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
		this.content = data.getBytes(encoding);
		String header = "HTTP/1.0 200 OK\r\n"
				+ "Server: OneFile 2.0\r\n"
				+ "Content-length: " + this.content.length + "\r\n"
				+ "Content-type: " + mimeType + "; charset=" + encoding + "\r\n\r\n";
		this.header = header.getBytes(Charset.forName("ASCII"));
		this.port = port;
		this.encoding = encoding;
	}
	public void start() {
		ExecutorService pool = Executors.newFixedThreadPool(50);
		try(ServerSocket server = new ServerSocket(this.port)) {
			logger.info("Accepting connections on port " + server.getLocalPort()); 
			logger.info("Data to be sent:");
			logger.info(new String(this.content, encoding));
			while(true) {
				try {
					Socket accept = server.accept();
					pool.submit(new HTTPHandler(accept));
				} catch(Exception ex) {
					logger.log(Level.WARNING, "Exception accepting connection", ex);
				}
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Unexception error", e);
		}
	}
	class HTTPHandler implements Callable<Void> {
		private final Socket connection;
		HTTPHandler(Socket connection) {
			this.connection = connection;
		}
		public Void call() throws IOException {
			OutputStream out = new BufferedOutputStream(connection.getOutputStream());
			InputStream in = new BufferedInputStream(connection.getInputStream());
			StringBuilder request = new StringBuilder();
			//读取第一行
			while(true) {
				int c = in.read();
				if(c == '\r' || c == '\n' || c == -1)
					break;
				request.append((char) c);
			}
			//如果是HTTP/1.0或者以后的版本，则发送一个MIME首部
			if(request.toString().indexOf("HTTP/") != -1) {
				out.write(header);
			}
			out.write(content);
			out.flush();
			return null;
		}
	}
	public static void main(String[] args) throws IOException {
		String contentType = URLConnection.getFileNameMap().getContentTypeFor("E:\\github\\javanet\\src\\main\\java\\com\\lydck\\javanet\\SingleFieldHTTPServer.java");
		byte[] content = Files.readAllBytes(Paths.get("E:\\github\\javanet\\src\\main\\java\\com\\lydck\\javanet\\SingleFieldHTTPServer.java"));
		SingleFieldHTTPServer server = new SingleFieldHTTPServer(new String(content), "UTF-8", contentType, 80);
		server.start();
		
	}
}
