package dombpwtw01110;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyBPWTW0 {

	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
		File xmlFile = new File("src/nyelvekBPWTW0.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		listNodes(doc);
		//módosítás
		modifyNode(doc, "szerver_nyelvek","Oracle","Java","MongoDB", "OOP", "Document-oriented DB");
		//Kiírás
		System.out.println("------------ Modified File ------------");
		listNodes(doc);

		
}

	  public static void listNodes(Document doc) throws TransformerFactoryConfigurationError, TransformerException {
		  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		  Transformer transf = transformerFactory.newTransformer();
			
		  DOMSource source = new DOMSource(doc);
		  StreamResult console = new StreamResult(System.out);
		  
		  transf.transform(source, console);
	  }

	  public static void modifyNode(Document doc, String name, String content, String type1, String type2, String nameTo1, String nameTo2) {
		    
		  	Node rNode = doc.getChildNodes().item(0);
		  	NodeList nList = rNode.getChildNodes();
		    
		    for (int i = 0; i < nList.getLength(); i++) {
		    	
		    	Node nNode = nList.item(i);
		    	
		    	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
		    		
		    		Element elem = (Element) nNode;

		    		if(elem.getNodeName().equals(name)) {
		    			if(elem.getAttribute("ceg").equals(content)) {
		    				NodeList cList = elem.getChildNodes();
		    				for(int j=0; j < cList.getLength(); j++) {
			    				Node cNode = cList.item(j);
			    				
			    				if(cNode.getNodeType() == Node.ELEMENT_NODE) {
			    					Element cElem = (Element) cNode;
			    					if(cElem.getAttribute("tipus").equals(type1)) {
			    						cElem.setTextContent(nameTo1);
			    					}
			    				}
			    				if(cNode.getNodeType() == Node.ELEMENT_NODE) {
			    					Element cElem = (Element) cNode;
			    					if(cElem.getAttribute("tipus").equals(type2)) {
			    						cElem.setTextContent(nameTo2);
			    					}
			    				}
			    			}
		    			}
		    			
		    			
		    			
		    			
		    		}
		    	}
		    }		    
	  }
	  
}