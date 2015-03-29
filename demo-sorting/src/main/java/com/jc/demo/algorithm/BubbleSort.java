package com.jc.demo.algorithm;

public class BubbleSort {
	public static void main(String[] args) {
		int a[] = { 1, 8, 6, 8, 9, 2, 0, 6, 9, 6, 5 };
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
		}
		print(a);
	}

	private static void print(int[] a) {
		StringBuffer sb = new StringBuffer();
		String delimit = "";
		for (int i : a) {
			sb.append(delimit).append(i);
			delimit = ",";
		}
		System.out.println(sb.toString());
	}

}
