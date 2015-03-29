package com.jc.demo.algorithm;

public class InsertionSort {

	public static void main(String[] args) {
		int a[] = { 1, 8, 6, 8, 9, 2, 0, 6, 9, 6, 5 };
		for (int i = 1; i < a.length; i++) {
			if (a[i] < a[i - 1]) {
				int temp = a[i];
				a[i] = a[i - 1];
				int j = i - 1;
				while (temp < a[j - 1]) {
					a[j] = a[j - 1];
					j--;
					if (j == 0)
						break;
				}
				a[j] = temp;
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
