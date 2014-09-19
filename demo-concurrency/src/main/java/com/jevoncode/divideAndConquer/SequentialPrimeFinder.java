package com.jevoncode.divideAndConquer;

public class SequentialPrimeFinder extends AbstractPrimeFinder{

	@Override
	public long countPrimes(int number) {
		return countPrimesInRange(1, number);
	}
	
	public static void main(String[] args) {
		new SequentialPrimeFinder().timeAndCompute(10000000);
	}
}
