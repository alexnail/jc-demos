package com.jevoncode.digester.hierachy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.jevoncode.digester.jakartagem.MainXMLBased;

public class Main {
	public static void main(String[] args) throws IOException, SAXException {
		// Initialize the digester
				Digester digester = new Digester();
				digester.addObjectCreate("people/person", Element.class);
				digester.addSetProperties("people/person");
				digester.addSetNext("people/person", "add", "java.lang.Object");
				digester.addObjectCreate("people/person/name", Element.class);
				digester.addSetProperties("people/person/name");
				digester.addSetNext("people/person/name", "addChild", "com.jevoncode.digester.hierachy.Element");
				digester.addObjectCreate("people/person/age", Element.class);
				digester.addSetProperties("people/person/age");
				digester.addSetNext("people/person/age", "addChild", "com.jevoncode.digester.hierachy.Element");

				// Push empty List onto Digester's Stack
				List<Element> elements = new ArrayList<Element>();
				digester.push(elements);

				// Parse the XML document
				// InputStream input = new FileInputStream("data.xml");
				InputStream input = MainXMLBased.class.getResourceAsStream("/data-hierachy.xml");
				digester.parse(input);

				for (Element p : elements)
					System.out.println(p);
	}
}
