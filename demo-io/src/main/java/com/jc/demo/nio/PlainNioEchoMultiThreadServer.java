package com.jc.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlainNioEchoMultiThreadServer {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
	String threadName = Thread.currentThread().getName();
	String logTime = sdf.format(new Date());
	private ExecutorService pool = Executors.newFixedThreadPool(50);

	public void serve(int port) throws Exception {
		System.out.println("Listening for connections on port " + port);
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		ServerSocket ss = serverSocketChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
		ss.bind(address);
		serverSocketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
				// handle in a proper way
				break;
			}
			Set readyKeys = selector.selectedKeys();
			Iterator iteractor = readyKeys.iterator();
			while (iteractor.hasNext()) {
				SelectionKey key = (SelectionKey) iteractor.next();
				iteractor.remove();
				try {
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						System.out.println("Accepted connection from " + client);
						client.configureBlocking(false);
						SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ,
								ByteBuffer.allocate(100));
						// ByteBuffer buf = ByteBuffer.allocate(1024);
						// clientKey.attach(buf);
					}
					if (key.isReadable()) {
						System.out.println(logTime + ":[" + threadName + "] key uninterest OP_READ");
						key.interestOps(key.interestOps() & ~SelectionKey.OP_READ);
						System.out.println(logTime + ":[" + threadName + "]" + key + " key.isReadable");
						pool.submit(new Worker(key));
						// SocketChannel client = (SocketChannel) key.channel();
						// ByteBuffer output = (ByteBuffer) key.attachment();
						// client.read(output);
						// output.flip();
						// System.out.println(output.capacity());
						// System.out.println((char)output.get());
					}
					// if (key.isWritable()) {
					// //key.isWritable()是表示Socket可写,网络不出现阻塞情况下,一直是可以写的,所认一直为true.一般我们不注册OP_WRITE事件.
					// System.out.println(logTime+":["+threadName+"] key
					// uninterest OP_WRITE");
					// SocketChannel client = (SocketChannel) key.channel();
					// ByteBuffer output = (ByteBuffer) key.attachment();
					// output.flip();
					// client.write(output);
					// output.compact();
					// }
					if (key.isWritable()) {
						send(key);
					}
				} catch (IOException e) {
					key.cancel();
					try {
						key.channel().close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	private void send(SelectionKey key) throws IOException {
		SocketChannel client = (SocketChannel) key.channel();
		ByteBuffer output = (ByteBuffer) key.attachment();
		output.flip(); //从写模式改为读模式
		client.write(output);
//		output.compact();
		output.clear();//从读模式改为写模式
		key.attach(output);
//		output.flip();
	}

	public static void main(String[] args) throws Exception {
		new PlainNioEchoMultiThreadServer().serve(8080);
	}
}

class Worker implements Runnable {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
	String threadName = Thread.currentThread().getName();
	String logTime = sdf.format(new Date());

	private SelectionKey key;
	private static Charset charset = Charset.forName("utf-8");

	public Worker(SelectionKey key) {
		super();
		this.key = key;
	}

	@Override
	public void run() {
		SocketChannel client = (SocketChannel) key.channel();
		// ByteBuffer buf = (ByteBuffer) key.attachment();
		ByteBuffer buf = ByteBuffer.allocate(2024);
		buf.clear();
		int len = 0;
		try {
			while ((len = client.read(buf)) > 0) {// 非阻塞，立刻读取缓冲区可用字节
				buf.flip();//从写模式改为读模式
				String message = charset.decode(buf).toString(); // decode方法会情况buf里面的内容
				System.out.println(logTime + ":[" + threadName + "]receive :" + message);
				// buf.clear();
				// key.attach(buf);
				message = "return to you! messge:" + message +"\r\n";
				System.out.println(logTime + ":[" + threadName + "]send :" + message);
				buf.clear();//从读模式改为写模式
				buf.put(message.getBytes());
				key.attach(buf);
			}
			if (len == -1) {
				System.out.println(logTime + ":[" + threadName + "]client out of connect.  close socketchannel");
				client.close();
			}

			System.out.println(logTime + ":[" + threadName + "] key interest OP_READ");
			key.interestOps(key.interestOps() | SelectionKey.OP_READ);

			System.out.println(logTime + ":[" + threadName + "] key selector wakeup");
			key.selector().wakeup();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}