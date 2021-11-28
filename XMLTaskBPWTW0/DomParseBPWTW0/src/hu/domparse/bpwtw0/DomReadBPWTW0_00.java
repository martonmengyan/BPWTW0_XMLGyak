package hu.domparse.bpwtw0;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadBPWTW0_00 {
	public static void main(String args[]) {
		try {
			File xmlFile = new File("src/XMLBPWTW0.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

		  	System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		  	// Elemek neveit tartalmaz� lista:
		  	String nNames[] = { "Publisher", "Forgalmaz�", "V�s�rl�", "J�t�k", "Weboldal", "Indok" };
		  	// Elemek attrib�tum�nak neveit tartalmaz� lista:
			for (int nNumber=0; nNumber < nNames.length; nNumber++) {
				NodeList nList = doc.getElementsByTagName(nNames[nNumber]);
				
				for (int i = 0; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					// Elem nev�nek ki�r�sa:
					System.out.printf("%s",nNames[nNumber]);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element elem = (Element)nNode;
						
						// Elem attrib�tum�nak ki�r�sa, ha van:
						if (nNode.hasAttributes()) {
				               // get attributes names and values
				               NamedNodeMap nodeMap = nNode.getAttributes();
				               for (int j = 0; j < nodeMap.getLength(); j++)
				               {
				                   Node tempNode = nodeMap.item(j);
				                   System.out.printf("%s : %s%n",tempNode.getNodeName(), tempNode.getNodeValue());
				               }
				            } else System.out.printf("%n");
						
						// Elem tulajdons�gainak ki�r�sa
						NodeList cNodes = elem.getChildNodes();
						for (int j = 0; j < cNodes.getLength(); j++) {
							if (cNodes.item(j).getTextContent().trim() != "") {
								System.out.println(cNodes.item(j).getNodeName() + ": " + normalizeText(cNodes.item(j).getTextContent().trim()));
							}	
						}
					}
					System.out.println();
				}
			}
			//visitChildNodes(nList);
		} catch (ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
		}
	}
	// Sz�veg formaz�sa a sz�p megjelen�s �rdek�ben
	private static String normalizeText(String text) {
		text = text.replaceAll("\\n", ", ");
		text = text.replaceAll("\\s+", " ");
		return text;
	}

}