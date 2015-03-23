package com.jc.demo.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {

	public static void main(String[] args) throws FileNotFoundException {
		long start = System.currentTimeMillis();
		String file = "SpringOne2GX 2013 Opening Night Keynote.mp4";
		try {
			FileInputStream fin = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			FileOutputStream fout = new FileOutputStream("out.mp4");
			while (fin.read(buffer) != -1) {
				fout.write(buffer);
			}
			fin.close();
			fout.close();
			System.out.println("it uses "
					+ (System.currentTimeMillis() - start) + "ms"); //it uses 45860ms
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
