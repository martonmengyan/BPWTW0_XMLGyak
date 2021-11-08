package dombpwtw01027;

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

public class DomModifyBPWTW0 {

	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
		File xmlFile = new File("src/carsBPWTW0.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		listNodes(doc);
		//Luxurycars törlése
		deleteNode(doc,"luxurycars");
		//Ferrari módosítása
		modifyNode(doc, "Ferrari", "Lamborghini");
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

	  public static void modifyNode(Document doc, String name, String nameTo) {
		    
		  	Node rNode = doc.getChildNodes().item(0);
		  	NodeList nList = rNode.getChildNodes();
		    
		    for (int i = 0; i < nList.getLength(); i++) {
		    	
		    	Node nNode = nList.item(i);
		    	
		    	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
		    		
		    		Element elem = (Element) nNode;

		    		if(elem.getAttribute("company").equals(name)) {
		    			elem.setAttribute("company", nameTo);
		    			NodeList cList = elem.getChildNodes();
		    			
		    			for(int j=0,k=1; j < cList.getLength(); j++) {
		    				
		    				Node cNode = cList.item(j);
		    				if(cNode.getNodeType() == Node.ELEMENT_NODE) {
		    					String nameWithType = nameTo + " 00" + k;
		    					cNode.setTextContent(nameWithType);
		    					k++;
		    				}
		    			}
		    		}
		    	}
		    }		    
	  }
	  
	  public static void deleteNode(Document doc, String name) {
		    
		  	Node rNode = doc.getChildNodes().item(0);
		  	NodeList rList = rNode.getChildNodes();
		    
		    for (int i = 0; i < rList.getLength(); i++) {
		    	
		    	Node node = rList.item(i);
		    	
		    	if(node.getNodeType() == Node.ELEMENT_NODE) {
		    		
		    		Element elem = (Element) node;
		    		
					if(elem.getNodeName().equals(name)) {
						rNode.removeChild(elem);
					}
		    	
		    	}
		    }
		    
	  }
}
