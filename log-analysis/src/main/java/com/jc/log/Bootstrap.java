package com.jc.log;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBException;

import com.jc.log.bean.WorkDefinitions;
import com.jc.log.util.XmlUtil;

public class Bootstrap {
	public static WorkDefinitions workDefinitions = null;
	public static final String DEFAULT_WORK_DEFINITION_FILE_NAME = "work-definition.xml";
	static{
		URL resources = WorkDefinitions.class.getClassLoader().getResource("work-definition.xml");
		try {
			File workDefinitionFile = new File(resources.toURI());
			workDefinitions = (WorkDefinitions) XmlUtil.unMarshal(WorkDefinitions.class, workDefinitionFile);
		} catch (URISyntaxException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
	}
}
