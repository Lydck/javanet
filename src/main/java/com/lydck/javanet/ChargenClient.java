package com.lydck.javanet;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
	public static void main(String[] args) throws Exception {
		SocketAddress address = new InetSocketAddress("www.baidu.com"
				+ "", 19);
		SocketChannel client = SocketChannel.open(address);
		ByteBuffer buffer = ByteBuffer.allocate(74);
		WritableByteChannel out = Channels.newChannel(System.out);
		while(client.read(buffer) != -1) {
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
	}
}
