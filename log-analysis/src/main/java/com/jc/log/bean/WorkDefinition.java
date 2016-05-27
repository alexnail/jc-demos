package com.jc.log.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 工作定义
 * 
 * @author Jc
 *
 */
@XmlRootElement(name = "workDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkDefinition {
	private String name;
	private String sloganBegin;
	private String sloganClosing;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSloganBegin() {
		return sloganBegin;
	}

	public void setSloganBegin(String sloganBegin) {
		this.sloganBegin = sloganBegin;
	}

	public String getSloganClosing() {
		return sloganClosing;
	}

	public void setSloganClosing(String sloganClosing) {
		this.sloganClosing = sloganClosing;
	}

	@Override
	public String toString() {
		return "WorkDefinition [name=" + name + ", sloganBegin=" + sloganBegin + ", sloganClosing=" + sloganClosing
				+ "]";
	}

}
