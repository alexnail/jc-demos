package com.jc.log;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.jc.log.bean.WorkDefinition;
import com.jc.log.bean.WorkDefinitions;

public class XmlUtilTest {
	static WorkDefinitions workDefinitions = new WorkDefinitions();

	static {
		workDefinitions.setWorkDefinitions(new ArrayList<WorkDefinition>());
		WorkDefinition work1 = new WorkDefinition();
		work1.setName("work1");
		work1.setSloganBegin("work1 begin!!!!");
		work1.setSloganClosing("work1 closing!!!");
		WorkDefinition work2 = new WorkDefinition();
		work2.setName("work2");
		work2.setSloganBegin("work2 begin!!!!");
		work2.setSloganClosing("work2 closing!!!");
		workDefinitions.getWorkDefinitions().add(work1);
		workDefinitions.getWorkDefinitions().add(work2);
	}

	public static void main(String[] args) {
		try {
			marshalingExample();
			unMarshalingExample();
		} catch (JAXBException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void marshalingExample() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(WorkDefinitions.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// Marshal the employees list in console
		jaxbMarshaller.marshal(workDefinitions, System.out);

		// Marshal the employees list in file
		jaxbMarshaller.marshal(workDefinitions, new File("E:/works.xml"));
	}

	private static void unMarshalingExample() throws JAXBException, URISyntaxException {
		JAXBContext jaxbContext = JAXBContext.newInstance(WorkDefinitions.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		// We had written this file in marshalling example
		URL resources = WorkDefinitions.class.getClassLoader().getResource("work-definition.xml");
		System.out.println(resources);
		System.out.println(resources.toURI());
		WorkDefinitions wds = (WorkDefinitions) jaxbUnmarshaller.unmarshal(new File(resources.toURI()));

		for (WorkDefinition workDefinition : wds.getWorkDefinitions()) {
			System.out.println(workDefinition);
		}
	}
}
