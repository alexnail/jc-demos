package com.jc.demo.algorithm;

public class SelectionSort {
	public static void main(String[] args) {
		int a[] = { 1, 8, 6, 8, 9, 2, 0, 6, 9, 6, 5 };
		for (int i = 0; i < a.length; i++) {
			int k = i;
			for (int j = i; j < a.length; j++)
				if (a[k] > a[j])
					k = j;

			if (k != i) {
				int temp = a[k];
				a[k] = a[i];
				a[i] = temp;
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
