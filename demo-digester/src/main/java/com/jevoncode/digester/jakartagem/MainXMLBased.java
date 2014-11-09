package com.jevoncode.digester.jakartagem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

public class MainXMLBased {
	public static void main(String[] args) throws IOException, SAXException {
		// Configure Digester from XML ruleset
		URL rules = MainXMLBased.class.getResource("/person-rules.xml");
		Digester digester = DigesterLoader.createDigester(rules);

		// Push empty List onto Digester's Stack
		List<Person> people = new ArrayList<Person>();
		digester.push(people);

		// Parse the XML document
//		InputStream input = new FileInputStream("data.xml");
		InputStream input = MainXMLBased.class.getResourceAsStream("/data.xml");
		digester.parse(input);
		
		for(Person p : people)
			System.out.println(p);
	}
}
