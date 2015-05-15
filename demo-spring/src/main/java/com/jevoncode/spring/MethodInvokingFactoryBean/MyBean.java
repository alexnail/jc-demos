package com.jevoncode.spring.MethodInvokingFactoryBean;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MyBean {
	private String name;
	private String javaVersion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
