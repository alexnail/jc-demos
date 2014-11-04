package com.jevoncode.jmx.mxbean;

public interface QueueSamplerMXBean {
	public QueueSample getQueueSample();

	public void clearQueue();
}
