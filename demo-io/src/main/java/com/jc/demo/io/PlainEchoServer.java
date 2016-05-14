package com.jc.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class PlainEchoServer {

	
	public void serve(int port) throws IOException{
		ServerSocket server = new ServerSocket(port);
		while(true){
			final Socket clientSocket = server.accept();
			System.out.println("接受来自"+clientSocket+"的链接");
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);
						int i = 0;
						while(true){
							String echo = reader.readLine();
							i++;
							System.out.println(i+"--"+echo);
							writer.println(echo);
							writer.flush();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("处理完毕");
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
