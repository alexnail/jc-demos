package com.jevoncode.jaxp.sax;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
/**
 * args:src\main\resources\rich_iii.xml
 * @author jevoncode
 *
 */
public class SAXLocalNameCount extends DefaultHandler {

	/** A Hashtable with tag names as keys and Integers as values */
	private Hashtable tags;

	// Parser calls this once at the beginning of a document
	public void startDocument() throws SAXException {
		tags = new Hashtable();
	}

	// Parser calls this for each element in a document
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		String key = localName;
		Object value = tags.get(key);
		if (value == null) {
			// Add a new entry
			tags.put(key, new Integer(1));
		} else {
			// Get the current count and increment it
			int count = ((Integer) value).intValue();
			count++;
			tags.put(key, new Integer(count));
		}
	}

	// Parser calls this once after parsing a document
	public void endDocument() throws SAXException {
		Enumeration e = tags.keys();
		while (e.hasMoreElements()) {
			String tag = (String) e.nextElement();
			int count = ((Integer) tags.get(tag)).intValue();
			System.out.println("Local Name \"" + tag + "\" occurs " + count + " times");
		}
	}

	/**
	 * Convert from a filename to a file URL.
	 */
	private static String convertToFileURL(String filename) {
		// On JDK 1.2 and later, simplify this to:
		// "path = file.toURL().toString()".
		String path = new File(filename).getAbsolutePath();
		if (File.separatorChar != '/') {
			path = path.replace(File.separatorChar, '/');
		}
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return "file:" + path;
	}

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		String filename = null;

		for (int i = 0; i < args.length; i++) {
			filename = args[i];
			if (i != args.length - 1) {
				usage();
			}
		}

		if (filename == null) {
			usage();
		}

		// Create a JAXP SAXParserFactory and configure it
		SAXParserFactory spf = SAXParserFactory.newInstance();

		// Set namespaceAware to true to get a parser that corresponds to
		// the default SAX2 namespace feature setting. This is necessary
		// because the default value from JAXP 1.0 was defined to be false.
		spf.setNamespaceAware(true);

		// Create a JAXP SAXParser
		SAXParser saxParser = spf.newSAXParser();

		// Get the encapsulated SAX XMLReader
		XMLReader xmlReader = saxParser.getXMLReader();

		// Set the ContentHandler of the XMLReader
		xmlReader.setContentHandler(new SAXLocalNameCount());

		// Set an ErrorHandler before parsing
		xmlReader.setErrorHandler(new MyErrorHandler(System.err));

		// Tell the XMLReader to parse the XML document
		xmlReader.parse(convertToFileURL(filename));

	}

	private static void usage() {
		System.err.println("Usage: SAXLocalNameCount <file.xml>");
		System.err.println("       -usage or -help = this message");
		System.exit(1);
	}
	
	 // Error handler to report errors and warnings
    private static class MyErrorHandler implements ErrorHandler {
        /** Error handler output goes here */
        private PrintStream out;

        MyErrorHandler(PrintStream out) {
            this.out = out;
        }

        /**
         * Returns a string describing parse exception details
         */
        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }
            String info = "URI=" + systemId +
                " Line=" + spe.getLineNumber() +
                ": " + spe.getMessage();
            return info;
        }

        // The following methods are standard SAX ErrorHandler methods.
        // See SAX documentation for more info.

        public void warning(SAXParseException spe) throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }
        
        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }
}
