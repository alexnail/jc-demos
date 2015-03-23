package com.jc.demo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileNIO {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String file = "SpringOne2GX 2013 Opening Night Keynote.mp4";
		try {
			// 输入
			FileInputStream fin = new FileInputStream(file);
			FileChannel fci = fin.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int r;
			// 输出
			FileOutputStream fout = new FileOutputStream("nio.mp4");
			FileChannel fco = fout.getChannel();
			while ((r = fci.read(buffer)) != -1) {
				buffer.flip();
				fco.write(buffer);
				buffer.clear();
			}
			System.out.println("it uses "
					+ (System.currentTimeMillis() - start) + "ms"); //it uses 15812ms

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
