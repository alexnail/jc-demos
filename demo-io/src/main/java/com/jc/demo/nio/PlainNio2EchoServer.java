package com.jc.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


public class PlainNio2EchoServer {
	public  void serve(int port) throws Exception{
		System.out.println("Listening for connections on port "+port);
//		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		final AsynchronousServerSocketChannel serverSocketChannel= AsynchronousServerSocketChannel.open();
//		ServerSocket ss = serverSocketChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
//		ss.bind(address);
		serverSocketChannel.bind(address);
//		serverSocketChannel.configureBlocking(false);
		final CountDownLatch latch = new CountDownLatch(1);
		serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

			@Override
			public void completed(AsynchronousSocketChannel channel, Object attachment) {
				serverSocketChannel.accept(null,this);
				ByteBuffer buffer= ByteBuffer.allocate(100);
				channel.read(buffer, buffer, new EchoCompletionHandler(channel));
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				try {
					serverSocketChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					latch.countDown();
				}
			}
		});
		try{
			/**await()才是CountDownLatch里相应的等待函数。
			wait()是同步锁，是Object类的方法，与notify()配对使用的，使用时必须要有sychronized关键字。*/
			latch.await();
//			synchronized (latch) {
//				latch.wait();
//			}
		}catch(InterruptedException e){
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
 
	}
	
	
	public static void main(String[] args) throws Exception {
		new PlainNio2EchoServer().serve(8080);
	}
	
	private final class EchoCompletionHandler implements CompletionHandler<Integer, Object>{
 
		private AsynchronousSocketChannel channel;
		
		
		public EchoCompletionHandler(AsynchronousSocketChannel channel) {
			super();
			this.channel = channel;
		}

		@Override
		public void completed(Integer result, Object attachment) {
			ByteBuffer buf = (ByteBuffer) attachment;
			buf.flip();
			channel.write(buf, buf, new CompletionHandler<Integer, Object>() {

				@Override
				public void completed(Integer result, Object attachment) {
					ByteBuffer buf = (ByteBuffer) attachment;
					if(buf.hasRemaining()){
						channel.write(buf,buf,this);
					}else{
						buf.compact();
						channel.read(buf, buf, EchoCompletionHandler.this);
					}
				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					try {
						channel.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

		@Override
		public void failed(Throwable exc, Object attachment) {
			try {
				channel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
