import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


public class DOMParserApp {

	public static void main(String[] args) {
		try {
			// Input XML document to parse
			File xmlFile = new File("Sample.xml");
			// Step 1. Create a new DOM parser factory with the DocumentBuilderFactoryclass and Step 2. configure factory
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			//Step 3. Create a new DOM builder (DocumentBuilder) object from the factory
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			//Step 4. Set the error handler and entity resolver for the DOM builder
			
			// 5.Parse the input XML document
			// Get the document's root XML node
			NodeList root = doc.getChildNodes();
			Node store = getNode("Store", root);
			
			String orderID = ""; // holds attribute value
			String name = "";
			String month = "";
			String day = "";
			String year = "";
			String officeLoc = ""; // holds attribute value
			
			NodeList orderNode = doc.getElementsByTagName("Order");
			NodeList itemNode = doc.getElementsByTagName("Items");
			NodeList dateNode = doc.getElementsByTagName("sold_date");
			NodeList officeNode = doc.getElementsByTagName("office");
			for (int loop = 0; loop < orderNode.getLength(); loop++) {
				Node node0 = orderNode.item(loop);
				orderID = getNodeAttribute(node0, "OrderID");
				System.out.println ("Current Node: " + node0.getNodeName() + ", OrderID: " + orderID);
				
				Node node = itemNode.item(loop);
				NodeList childNodes = node.getChildNodes();
				name = getNodeValue("Name", (NodeList) childNodes);
				System.out.println("Name: " + name);
				
				Node node2 = dateNode.item(loop);
				NodeList dateChildren = node2.getChildNodes();
				month = getNodeValue("Month", (NodeList) dateChildren);
				System.out.println("Month: " + month);
				day = getNodeValue("Day", (NodeList) dateChildren);
				System.out.println("Day: " + day);
				year = getNodeValue("Year", (NodeList) dateChildren);
				System.out.println("Year: " + year);
				
				Node node3 = officeNode.item(loop);	
				officeLoc = getNodeAttribute(node3, "location");
				System.out.println("Office: " + officeLoc);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // ends main

	protected static Node getNode(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				return node;
			}
		}
		return null;
	}
	
	// to obtain node contents
	protected static String getNodeValue(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				NodeList childNodes = node.getChildNodes();
				for (int y = 0; y < childNodes.getLength(); y++) {
					Node data = childNodes.item(y);
					if (data.getNodeType() == Node.TEXT_NODE) {
						return data.getNodeValue();
					}
				}
			}
		}
		return "";
	}
	
	// to obtain attribute values
	protected static String getNodeAttribute(Node node, String attribute)
    {
        String attributeValue = node.getAttributes().getNamedItem(attribute).getTextContent();
        return attributeValue;
    }
	
} // ends class
