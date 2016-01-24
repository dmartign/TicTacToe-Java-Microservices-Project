package cse551.hw1.parsers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import de.odysseus.staxon.json.jaxb.JsonXML;
import de.odysseus.staxon.json.jaxb.JsonXMLMapper;

public class JSONMain {

	public static void main(String[] args) throws XMLStreamException,
	JAXBException {

		JsonXMLMapper<Customer> mapper = new JsonXMLMapper<Customer>(
				Customer.class);

		Customer readCustomer = mapper
				.readObject(new StringReader(
						"{\"name\":\"NAME\",\"phoneNumbers\":[\"222-222-2222\",\"333-333-3333\"]}"));
		mapper.writeObject(System.out, readCustomer);
		XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance()
				.createXMLStreamWriter(System.out);

	}

	@JsonXML(virtualRoot = true, prettyPrint = true, multiplePaths = "phone")
	@XmlRootElement
	static class Customer {
		private String name;
		private List<String> phoneNumbers = new ArrayList<String>();

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<String> getPhoneNumbers() {
			return this.phoneNumbers;
		}

		public void setPhoneNumbers(List<String> phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
		}
	}

}
