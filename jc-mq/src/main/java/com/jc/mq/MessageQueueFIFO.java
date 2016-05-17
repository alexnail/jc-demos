package com.jc.mq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageQueueFIFO {

	/**
	 * The array buffer into which the elements of the ArrayList are stored. The
	 * capacity of the ArrayList is the length of this array buffer. Any empty
	 * ArrayList with elementData == EMPTY_ELEMENTDATA will be expanded to
	 * DEFAULT_CAPACITY when the first element is added.
	 * Message数组缓存，容量就是这数组的长度，当第一个Message添加进这JcQueue，elementData将从elementData ==
	 * EMPTY_ELEMENTDATA扩展到DEFAULT_CAPACITY的容量
	 */
	private transient Message[] elementData;

	/**
	 * Shared empty array instance used for empty instances. 共享空数组
	 */
	private static final Message[] EMPTY_ELEMENTDATA = {};

	/**
	 * Default initial capacity. 默认容量10个
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * The size of the ArrayList (the number of elements it contains). 队列的长度
	 * 
	 * @serial
	 */
	private int size;

	private Object object = new Object();

	public MessageQueueFIFO() {
		super();
		elementData = EMPTY_ELEMENTDATA;
	}

	public boolean push(Message message) {
		synchronized (object) {
			String name = Thread.currentThread().getName();
			while ((size + 1) > MAX_ARRAY_SIZE) {
				try {
					System.out.println("["+name+"]-队列已满，进入等待");
					object.wait();
					System.out.println("["+name+"]-被唤醒，继续检查队列是否已满");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ensureCapacityInternal(size + 1);
			elementData[size++] = message;
			object.notify();
		}
		return true;
	}

	public Message pop() {
		synchronized (object) {
			String name = Thread.currentThread().getName();
			while(size<=0){
				try {
					System.out.println("["+name+"]-队列为空,进入等待");
					object.wait();
					System.out.println("["+name+"]-被唤醒，继续检查队列是否为空");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("["+name+"]-不为空，进行消费");
			Message oldValue = elementData[0];
			int numMoved = size - 1 - 0;
			System.arraycopy(elementData, 0 + 1, elementData, 0, numMoved);
			elementData[--size] = null; // clear to let GC do its work
			object.notify();
			return oldValue;
		}
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (elementData == EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}

		ensureExplicitCapacity(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity) {
		// overflow-conscious code
		if (minCapacity - elementData.length > 0)
			grow(minCapacity);
	}

	/**
	 * The maximum size of array to allocate. Some VMs reserve some header words
	 * in an array. Attempts to allocate larger arrays may result in
	 * OutOfMemoryError: Requested array size exceeds VM limit
	 * 数组的最大分配数，由于一些VM保留了数组的信息（header words），这些信息如数组的长度
	 * .length，所以直接最大值可能会造成OutOfMemoryError（请求数组长度大于VM的限制）
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * Increases the capacity to ensure that it can hold at least the number of
	 * elements specified by the minimum capacity argument.
	 * 增加容量确保至少能够装下minimum个元素
	 * 
	 * @param minCapacity
	 *            the desired minimum capacity
	 */
	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
		if (newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
		// minCapacity is usually close to size, so this is a win:
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(10000);
		long million = 1000000;
		List<Integer> maxCapacity = new ArrayList<>();
		long begin = System.currentTimeMillis();
		long subBegin = System.currentTimeMillis();
		for (int i = 1; i <= Integer.MAX_VALUE; i++) {
			// System.out.println(i);
			maxCapacity.add(i);
			if (i % million == 0) {
				System.out.println("第" + (i / million) + "百万用时:" + (System.currentTimeMillis() - subBegin) + "ms");
				subBegin = System.currentTimeMillis();
			}
		}
		System.out.println("用时:" + (System.currentTimeMillis() - begin) + "ms");

		for (int i : maxCapacity) {
			System.out.println("trace " + i);
		}
	}
}
