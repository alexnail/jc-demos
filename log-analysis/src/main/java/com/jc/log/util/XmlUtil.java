package com.jc.log.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtil {
	/**
	 * 将对象转为xml文件
	 * @param clazz
	 * @param t
	 * @param xmlFile
	 * @throws JAXBException
	 */
	public static <T> void  marshal(Class clazz,T t,File xmlFile) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(t, xmlFile);
	}
	/**
	 * 将xml文件转为对象
	 * @param clazz
	 * @param xmlFile
	 * @return
	 * @throws JAXBException
	 */
	public static Object unMarshal(Class clazz,File xmlFile) throws JAXBException  {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return jaxbUnmarshaller.unmarshal(xmlFile);
	}
}
