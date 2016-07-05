package com.jc.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class PlainNioEchoServer {
	public void serve(int port) throws Exception{
		System.out.println("Listening for connections on port "+port);
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		ServerSocket ss = serverSocketChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
		ss.bind(address);
		serverSocketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(true){
			try{
				selector.select();
			}catch(IOException e){
				e.printStackTrace();
				// handle in a proper way
				break;
			}
			Set readyKeys = selector.selectedKeys();
			Iterator iteractor =readyKeys.iterator();
			while(iteractor.hasNext()){
				SelectionKey key = (SelectionKey) iteractor.next();
				iteractor.remove();
				try{
					if(key.isAcceptable()){
						ServerSocketChannel server  = (ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						System.out.println("Accepted connection from "+client);
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ, ByteBuffer.allocate(100));
					}
					if(key.isReadable()){
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer output = (ByteBuffer) key.attachment();
						client.read(output);
						output.flip();
						System.out.println(output.capacity());
						System.out.println((char)output.get());
						Thread.sleep(3000*1000);
					}
					if(key.isWritable()){
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer output = (ByteBuffer) key.attachment();
						output.flip();
						client.write(output);
						output.compact();
					}
				}catch(IOException e){
					key.cancel();
					try{
						key.channel().close();
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		new PlainNioEchoServer().serve(8080);
	}
}
