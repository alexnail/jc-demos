package com.jc.demo.net.javatutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KnockKnockClient {
	public static void main(String[] args) {
		try (Socket socket = new Socket("127.0.0.1", 8834);
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					System.in));
			String fromServer;
			String fromUser;

			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye."))
					break;

				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port 8834");
			System.exit(-1);
		}

	}
}
