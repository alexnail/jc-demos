package com.jc.demo.algorithm;

public class QuickSort {
	public static void main(String[] args) {
		int a[] = { 1, 8, 6, 8, 9, 2, 0, 6, 9, 6, 5 };
		int low = 0;
		int high = a.length - 1;
		doQuickSort(a, low, high);
		print(a);
	}

	private static void doQuickSort(int[] a, int low, int high) {
		if (low < high) {
			int p = partition(a, low, high);
			doQuickSort(a, low, p - 1);
			doQuickSort(a, p + 1, high);
		}
	}

	private static int partition(int[] a, int low, int high) {
		int pivot = a[low];
		while (low < high) {
			while (low < high & a[high] >= pivot)
				high--;
			swap(a, low, high);
			while (low < high & a[low] < pivot)
				low++;
			swap(a, low, high);
		}
		print(a);
		return low;
	}

	private static void swap(int[] a, int low, int high) {
		int temp = a[low];
		a[low] = a[high];
		a[high] = temp;
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
