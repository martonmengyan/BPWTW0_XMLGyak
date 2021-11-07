package dombpwtw01027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryBPWTW0 {
	
	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
		File xmlFile = new File("src/carsBPWTW0.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		listNodes(doc,"supercars");
		
	}
	
	public static void listNodes(Document doc, String nName) {
		Node rNode = doc.getFirstChild();
		NodeList nList = rNode.getChildNodes();
		
		System.out.println("Root element: "+ rNode.getNodeName() + "\n-------------------------------\n");

		for(int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				if(nNode.getNodeName() == nName) {
					NodeList cList = nNode.getChildNodes();
					StringBuilder sb = new StringBuilder();
					sb.append(getCompany(nNode));
					
					for(int j = 0; j < cList.getLength(); j++) {
						Node cNode = cList.item(j);
						if(cNode.getNodeType() == Node.ELEMENT_NODE) {
							sb.append(getName(cNode));
							sb.append(getType(cNode));
						}
					}
					System.out.println(sb.toString());
				}
			}
		}
	}
	
	public static String getName(Node nName) {
		Element elem = (Element) nName;
		
		StringBuilder sb = new StringBuilder("car name : ");
		sb.append(elem.getTextContent().toString());
		sb.append("\n");
		
		return sb.toString();
	}
	
	public static String getType(Node nName) {
		Element elem = (Element) nName;
		
		StringBuilder sb = new StringBuilder("car type : ");
		sb.append(elem.getAttribute("type"));
		sb.append("\n");
		
		return sb.toString();
	}
	
	public static String getCompany(Node nName) {
		Element elem = (Element) nName;
		
		StringBuilder sb = new StringBuilder("Current Element : ");
		sb.append("\n");
		sb.append(elem.getNodeName());
		sb.append("company : ");
		sb.append(elem.getAttribute("company"));
		sb.append("\n");
		
		return sb.toString();
	}
}
