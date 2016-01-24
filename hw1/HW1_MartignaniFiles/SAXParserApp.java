import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
import java.io.*;

public class SAXParserApp extends DefaultHandler { // ContentHandler interface

	public static void main(String[] args) {
		String input = "Sample.xml"; // input XML document
		
		try {
			// Step 1. create a new SAXParser factory with the SAXParserFactory class
			// Step 2. Configure the factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			
			// Step 3. Create a new SAXParser object from factory
			SAXParser SAXparser = factory.newSAXParser();
			
			// Step 4. Set event handlers
			SAXParserApp handler = new SAXParserApp();
			SAXparser.parse(input, handler);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // ends main

	// Step 5. Parse the input XML document
	
	// variables to use
	boolean name, month, day, year, officeAttribute;
	String officeLocation; // holds attribute value
	
	// implementing ContentHandler Interface, handling Content Events
	// Document Events
	@Override
	public void startDocument() throws SAXException	{
		System.out.println("{Start Document}");
	}
	
	@Override 
	public void endDocument() throws SAXException {
		System.out.println("{End Document}");
	}
	
	// Element Events
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("(Start Element)------>" + qName);
		if (qName.equalsIgnoreCase("NAME")) {
			name = true;
		}
		if (qName.equalsIgnoreCase("OFFICE")) {
			officeLocation = attributes.getValue("location");
			officeAttribute = true;
		}
		if (qName.equalsIgnoreCase("MONTH")) {
			month = true;
		}
		if (qName.equalsIgnoreCase("DAY")) {
			day = true;
		}
		if (qName.equalsIgnoreCase("YEAR")) {
			year = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("End element: " + qName);
	}
	
	// prints parsed XML file
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (name) {
			System.out.println("Name: " + new String(ch, start, length));
			name = false;
		}
		if (officeAttribute) {
			System.out.println("Attribute 'location' of office: " + officeLocation);
			officeAttribute = false;
		}
		if (month) {
			System.out.println("Month: " + new String(ch, start, length));
			month = false;
		}
		if (day) {
			System.out.println("Day: " + new String(ch, start, length));
			day = false;
		}
		if (year) {
			System.out.println("Year: " + new String(ch, start, length));
			year = false;
		}
	}
	
} // ends class
