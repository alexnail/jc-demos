package com.jc.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Random;

public class PlainEchoServer {

	int workerCounts = 0;

	public synchronized void increaseWorker() {
		workerCounts++;
		System.out.println("增加worker，现worker数为；" + workerCounts);
	}

	public synchronized void decreaseWorker() {
		workerCounts--;
		System.out.println("减少worker，现worker数为；" + workerCounts);
	} 
	 
	
	public void serve(int port) throws IOException{
		ServerSocket server = new ServerSocket(port);
		while (true) {
			final Socket clientSocket = server.accept();
			increaseWorker();
			new Thread(new Runnable() {
				String name = Thread.currentThread().getName();
				public void run() {
					System.out.println("接受来自" + clientSocket + "的链接，并交给线程" + name + "处理。");
					try {
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()));
						PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
						int i = 0;
						while (true) {
							String echo = reader.readLine();
							i++;
							// System.out.println("第"+i+"次接受并原路返回"+echo);
							Random r = new Random();
							int dealTimeRandom = r.nextInt(50) * 1000;
							try {
								System.out.println(name+"线程处理该请求需时"+dealTimeRandom+"ms");
								Thread.sleep(dealTimeRandom);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							writer.println(echo);
							writer.flush();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(name + "线程处理" + clientSocket + "请求完毕！");
					decreaseWorker();
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		try {
			new PlainEchoServer().serve(3434);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
