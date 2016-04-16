package mainjava;

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
//import com.hp.hpl.jena.rdf.model.Model;

public class HelloRDFWorld {

	public static void main(String[] args) {
		Model m = ModelFactory.createDefaultModel();
		String NS = "http://example.com/test/";
		
		Resource r = m.createResource(NS + "r");
		Property p = m.createProperty(NS + "p");
		
		r.addProperty(p, "HelloWorld",XSDDatatype.XSDstring);
		m.write(System.out,"RDF/XML");
	}

}
