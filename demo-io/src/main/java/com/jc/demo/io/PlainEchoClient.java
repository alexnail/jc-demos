package com.jc.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.jc.mq.Message;
import com.jc.mq.MessageQueueFIFO;

public class PlainEchoClient {
	List<OneClient> users = new ArrayList<>();

	MessageQueueFIFO queue = new MessageQueueFIFO();

	public PlainEchoClient initPlentyClient() {
		for (int i = 1; i < 10000000; i++) {
			users.add(new OneClient().request());
			System.out.println("初始化第" + i + "用户");
		}
		return this;
	}

	public void go() {
		for (OneClient user : users) {
			user.request();
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		// Thread.sleep(10000);
		// new PlainEchoClient().initPlentyClient();
		PlainEchoClient plainEchoClient = new PlainEchoClient();
		for (int i = 0; i < 2; i++) {
			new Thread(new Producer(plainEchoClient)).start();
		}
		for (int i = 0; i < 2; i++) {
			new Thread(new Consumer(plainEchoClient)).start();
		}
	}

	public void addUser(Message user) {
		queue.push(user);
	}

	public Message popUser() {
		return queue.pop();
	}

}

class OneClient implements Runnable {
	Socket client;

	public OneClient() {
		try {
			client = new Socket("127.0.0.1", 3434);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			long begin = System.currentTimeMillis();
			PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
			System.out.println(new Date() + "-->" + client + "发送请求，发送内容j");
			writer.println("j");
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println(new Date() + "-->" + client + "接受服务器响应:" + reader.readLine());
			System.out.println(new Date() + "-->" + client + "一次请求耗时:" + (System.currentTimeMillis() - begin) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public OneClient request() {
		Random randomMachine = new Random();
		Long requestInThisTime = (long) (randomMachine.nextInt(10) * 1000);
		// try {
		// Thread.sleep(requestInThisTime);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		new Thread(this).start();
		return this;
	}
}

class Producer implements Runnable {
	private PlainEchoClient plainEchoClient;

	public Producer(PlainEchoClient client) {
		super();
		this.plainEchoClient = client;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000000; i++) {
			Message message = new Message();
			OneClient user = new OneClient();
			message.setContent(user);
			plainEchoClient.addUser(message);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	private PlainEchoClient plainEchoClient;

	public Consumer(PlainEchoClient client) {
		super();
		this.plainEchoClient = client;
	}

	@Override
	public void run() {
		while (true) {
			Message message = plainEchoClient.popUser();
			OneClient user = (OneClient) message.getContent();
			user.request();
		}
	}
}