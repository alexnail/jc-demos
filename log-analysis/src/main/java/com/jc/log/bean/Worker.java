package com.jc.log.bean;

public class Worker {

	private String serverIp;
	private String name; // 工作者姓名,一般服务器id+线程名
	public Worker(String serverIp, String name) {
		super();
		this.serverIp = serverIp;
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((serverIp == null) ? 0 : serverIp.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (serverIp == null) {
			if (other.serverIp != null)
				return false;
		} else if (!serverIp.equals(other.serverIp))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Worker [serverIp=" + serverIp + ", name=" + name + "]";
	}

}
