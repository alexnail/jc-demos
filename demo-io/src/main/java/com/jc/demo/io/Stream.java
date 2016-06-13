package com.jc.demo.io;

import java.io.IOException;
import java.io.OutputStream;

public class Stream {
	public static void generateChharactersCache(OutputStream out) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		int start = firstPrintableCharacter;
		byte[] line = new byte[numberOfCharactersPerLine + 2];
		while (true) {
			for (int i = start; i < start + numberOfCharactersPerLine; i++) {
				// out.write(((i - firstPrintableCharacter) %
				// numberOfPrintableCharacters) + firstPrintableCharacter);
				line[i - start] = (byte) (((i - firstPrintableCharacter) % numberOfPrintableCharacters)
						+ firstPrintableCharacter);
			}
			line[72] = '\r';// 回车
			line[73] = '\n';// 换行
			out.write(line);
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
	}

	public static void generateChharacters(OutputStream out) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		int start = firstPrintableCharacter;
		while (true) {
			for (int i = start; i < start + numberOfCharactersPerLine; i++) {
				out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
			}
			out.write('\r'); // 回车
			out.write('\n'); // 换行
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
	}

	public static void main(String[] args) throws IOException {
//		generateChharacters(System.out);
		generateChharactersCache(System.out);
	}
}
