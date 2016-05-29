package com.jc.log.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jc.commons.DateUtils;

public class ThreadTask implements Comparable<ThreadTask> {
	private Integer id;
	private String threadName;
	private String begin;
	private String end;
	private Long elapseTime;
	private String time;
	private String fromFileName;
	private String toFileName;

	public ThreadTask(Integer id, String threadName, String begin, String end, Long elapseTime, String time,
			String fromFileName, String toFileName) {
		super();
		this.id = id;
		this.threadName = threadName;
		this.begin = begin;
		this.end = end;
		this.elapseTime = elapseTime;
		this.time = time;
		this.fromFileName = fromFileName;
		this.toFileName = toFileName;
	}

	public String getFromFileName() {
		return fromFileName;
	}

	public void setFromFileName(String fromFileName) {
		this.fromFileName = fromFileName;
	}

	public String getToFileName() {
		return toFileName;
	}

	public void setToFileName(String toFileName) {
		this.toFileName = toFileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Long getElapseTime() {
		return elapseTime;
	}

	public void setElapseTime(Long elapseTime) {
		this.elapseTime = elapseTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int compareTo(ThreadTask task) {
		int interval = 0;
		try {
			Date taskDate = DateUtils.parse(task.getTime());
			Date meDate = DateUtils.parse(time);
			interval = (int) (meDate.getTime() - taskDate.getTime()); // 按记录时间正序排序
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (interval != 0)
			return interval;
		else
			return (int) (task.getElapseTime() - elapseTime); // 记录时间相等则按耗时倒序排序，耗时多的排前面
	}

}
