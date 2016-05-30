package com.jc.log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import com.jc.commons.XmlUtil;
import com.jc.log.bean.WorkDefinitions;
import com.jc.log.io.LogFile;

public class Bootstrap {
	public static WorkDefinitions workDefinitions = null;
	public static WorkerAnalysis workerAnalysis  =  null;
	public static final String DEFAULT_WORK_DEFINITION_FILE_NAME = "work-definition.xml";
	public static final String DEFAULT_CONFIG_FILE_NAME = "config.properties";
	static{
		URL resources = WorkDefinitions.class.getClassLoader().getResource(DEFAULT_WORK_DEFINITION_FILE_NAME);
		InputStream configIn = WorkDefinitions.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE_NAME);
		try {
			File workDefinitionFile = new File(resources.toURI());
			workDefinitions = (WorkDefinitions) XmlUtil.unMarshal(WorkDefinitions.class, workDefinitionFile);
			Properties config =  new Properties();
			config.load(configIn);
			String dir = config.getProperty("dir");
			LogFile logFile = new LogFile(dir);
			workerAnalysis  = new WorkerAnalysis(workDefinitions.getWorkDefinitions(),logFile,config);
		} catch (URISyntaxException | JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws ParseException {
		workerAnalysis.parserLogDir();
		System.out.println(workerAnalysis.output());
	}
}
