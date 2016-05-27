package com.jc.log.bean;

public class ThreadTaskTag {

	private String time; // 记录时间
	private String threadName; // 线程名
	private String text; // 日志正文
	private String tag; //标志开始还是结束，开始BEGIN，结束CLOSING
	private String closingOrBegin; 
	private String fileName;

	public ThreadTaskTag(String time, String threadName, String text, String tag, String closingOrBegin,
			String fileName) {
		super();
		this.time = time;
		this.threadName = threadName;
		this.text = text;
		this.tag = tag;
		this.closingOrBegin = closingOrBegin;
		this.fileName = fileName;
	}
 
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	
	public String getClosingOrBegin() {
		return closingOrBegin;
	}

	public void setClosingOrBegin(String closingOrBegin) {
		this.closingOrBegin = closingOrBegin;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
