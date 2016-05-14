package com.jc.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.BufferUnderflowException;

public class PlainEchoClient {

	
	public static void main(String args[]) throws IOException{
		Socket client = new Socket("127.0.0.1", 3434);
		PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
		while(true){
		writer.println("j");
		writer.flush(); 
		}
//		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//		System.out.println(reader.readLine());
	}
}
