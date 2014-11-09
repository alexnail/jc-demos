package com.jevoncode.digester.jakartagem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

public class MainHardCode {
	public static void main(String[] args) throws IOException, SAXException {
		// Initialize the digester
		Digester digester = new Digester();
		digester.addObjectCreate("people/person", Person.class);
		digester.addSetProperties("people/person");
		digester.addSetNext("people/person", "add", "java.lang.Object");
		digester.addBeanPropertySetter("people/person/name", "name");
		digester.addBeanPropertySetter("people/person/age", "age");

		// Push empty List onto Digester's Stack
		List<Person> people = new ArrayList<Person>();
		digester.push(people);

		// Parse the XML document
		// InputStream input = new FileInputStream("data.xml");
		InputStream input = MainXMLBased.class.getResourceAsStream("/data.xml");
		digester.parse(input);

		for (Person p : people)
			System.out.println(p);
	}
}
