package com.jc.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jc.xml.dom.XmlValidationModeDetector;

/**
 * 模仿spring3.2.x解析xml的代码
 * @author jevoncode
 *
 */
public class DOMDemo {
	/**
	 * JAXP attribute used to configure the schema language for validation.
	 */
	private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	/**
	 * JAXP attribute value indicating the XSD schema language.
	 */
	private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		String fileName = "provider.xml";
		InputStream in =  DOMDemo.class.getClassLoader().getResourceAsStream(fileName);
		int validationMode = new XmlValidationModeDetector().detectValidationMode(in);
		if(validationMode==3)
			System.out.println(fileName + "是'XML Schema'验证方式");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		boolean namespaceAware = false;
		factory.setNamespaceAware(namespaceAware);
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		in =  DOMDemo.class.getClassLoader().getResourceAsStream(fileName);
		Document doc = docBuilder.parse(in);
		Element root = doc.getDocumentElement();
		System.out.println(root.getNamespaceURI());
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				System.out.println(ele.getTagName()+"的命名空间="+ele.getNamespaceURI());
			}
		}
	}
}
