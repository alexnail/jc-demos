package com.jevoncode.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class YahooFinance {
	public static void getPrice(final String ticker) throws IOException {
		final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s="+ticker);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		final String discardHeader = reader.readLine();
		final String data = reader.readLine();
		System.out.println(data); 
		
	}
	public static void main(String[] args) throws IOException {
		getPrice("2505");
	}
}
