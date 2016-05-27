package com.jc.log.bean;

public class Work {
	private String name; //工作名字
	private String beginTime; // 工作开始时间
	private Long elapseTime; // 工作耗时
	private String fromFileName; // 从哪个文件开始
	private String toFileName; // 从哪个文件结束
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public Long getElapseTime() {
		return elapseTime;
	}
	public void setElapseTime(Long elapseTime) {
		this.elapseTime = elapseTime;
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
	
	
}
