package com.jc.log.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workDefinitions")
@XmlAccessorType (XmlAccessType.FIELD)
public class WorkDefinitions {
    @XmlElement(name = "workDefinition")
	private List<WorkDefinition> workDefinitions;

	public List<WorkDefinition> getWorkDefinitions() {
		return workDefinitions;
	}

	public void setWorkDefinitions(List<WorkDefinition> workDefinitions) {
		this.workDefinitions = workDefinitions;
	}

}
