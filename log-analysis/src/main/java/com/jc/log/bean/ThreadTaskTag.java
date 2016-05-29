package com.jc.log.bean;

public class ThreadTaskTag {

	private String serverId;
	private String time; // 记录时间
	private String threadName; // 线程名
	private String text; // 日志正文
	private String tag; // 标志开始还是结束，开始BEGIN，结束CLOSING
	private String correspondingSlogan;
	private String fileName;

	public ThreadTaskTag(String serverId, String time, String threadName, String text, String tag,
			String correspondingSlogan, String fileName) {
		super();
		this.serverId = serverId;
		this.time = time;
		this.threadName = threadName;
		this.text = text;
		this.tag = tag;
		this.correspondingSlogan = correspondingSlogan;
		this.fileName = fileName;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
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

	public String getCorrespondingSlogan() {
		return correspondingSlogan;
	}

	public void setCorrespondingSlogan(String correspondingSlogan) {
		this.correspondingSlogan = correspondingSlogan;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
