package cse551.hw1.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;

public class XMLtoJSONMain {

	public static void main(String[] args) throws TransformerException,
			XMLStreamException, IOException {
		InputStream input = XMLtoJSONMain.class.getClassLoader()
				.getResourceAsStream("Sample.xml");
		OutputStream output = System.out;
		/*
		 * If we want to insert JSON array boundaries for multiple elements, we
		 * need to set the <code>autoArray</code> property. If our XML source
		 * was decorated with <code>&lt;?xml-multiple?&gt;</code> processing
		 * instructions, we'd set the <code>multiplePI</code> property instead.
		 * With the <code>autoPrimitive</code> property set, element text gets
		 * automatically converted to JSON primitives (number, boolean, null).
		 */
		JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true)
				.autoPrimitive(true).prettyPrint(true).build();
		try {
			/*
			 * Create source (XML).
			 */
			XMLStreamReader reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(input);
			Source source = new StAXSource(reader);

			/*
			 * Create result (JSON).
			 */
			XMLStreamWriter writer = new JsonXMLOutputFactory(config)
			.createXMLStreamWriter(output);
			Result result = new StAXResult(writer);

			/*
			 * Copy source to result via "identity transform".
			 */
			TransformerFactory.newInstance().newTransformer()
			.transform(source, result);
		} finally {
			/*
			 * As per StAX specification, XMLStreamReader/Writer.close() doesn't
			 * close the underlying stream.
			 */
			output.close();
			input.close();
		}
	}

}
