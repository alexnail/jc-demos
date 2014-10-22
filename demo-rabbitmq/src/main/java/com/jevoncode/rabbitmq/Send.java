package com.jevoncode.rabbitmq;

import java.io.IOException;
import java.util.ResourceBundle;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException {
		ResourceBundle config = ResourceBundle.getBundle("native");
		// System.out.println(config.getString("message"));
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// for (int i = 0; i < 999999999; i++) {
		String message = new String(config.getString("message").getBytes("ISO-8859-1"), "UTF-8");
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");
		// }
		channel.close();
		connection.close();
	}
}
