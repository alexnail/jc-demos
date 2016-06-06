package com.jc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

public class ReturnDigest implements Runnable {

	private String fileName;

	private byte[] digest;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getDigest() {
		return digest;
	}

	public void setDigest(byte[] digest) {
		this.digest = digest;
	}

	public ReturnDigest(String fileName) {
		super();
		this.fileName = fileName;
	}

	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(fileName);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din.read() != -1)
				;
			din.close();
			setDigest(sha.digest());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		File e = new File("E:/");
		List<ReturnDigest> ts = new ArrayList<>();
		for (File file : e.listFiles()) {
			ReturnDigest rd = new ReturnDigest(file.getAbsolutePath());
			Thread t = new Thread(rd);
			t.start();
			ts.add(rd);
		}

		for (ReturnDigest rd : ts) {
			StringBuffer result = new StringBuffer(rd.getFileName());
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(rd.getDigest()));
			System.out.println(result);
		}
	}

}
