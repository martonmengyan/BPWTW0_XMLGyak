package hu.domparse.bpwtw0;

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
	public static void main(String args[]) {
		try {
			File xmlFile = new File("src/XMLBPWTW0.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			Node rNode = doc.getChildNodes().item(0);
		  	NodeList nList = rNode.getChildNodes();
		  	
		  	//A 06605851274 Telefonszám attribútúmmal rendelkezõ nevét átírja Kovács Vanessza Anett-re.
			modifyNodeByAttr(nList,"Telefonszam","06605851274","Nev","Kovacs Vanessza Anett");
			//A vásárlók fizetési_módját átírja Paypal-ra mindenesetben.
			modifyNodeByName(nList,"Vasarlo","Fizetes_modja","PayPal");
			//A www.sfeam.com Link attribútúmmal rendelkezõ emailt-jét átírja newsfeammanagment@gmail.com-re.
			modifyNodeByName(nList,"Jatek","Platform","PC");
			//kiíró függvény meghívása
			listNodes(doc);

		} catch (TransformerException | TransformerFactoryConfigurationError |
				ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
		}
	}
	
	/*attrTypeName az attribútom nevét, attrName az attribútom tartalmát,
	 * a name a gyerek elem nevét, a nameTo a gyerek elem új tartalmát tartalmazza.*/
	private static void modifyNodeByAttr(NodeList nList,String attrTypeName,
			String attrName, String name, String nameTo) {
		for (int temp = 0; temp < nList.getLength(); temp++) {  
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				//megkeresi azt az elemet, amelynek az 'attrTypeName' tartalma 'attrName'
				if(elem.getAttribute(attrTypeName).equals(attrName)) {
					//kikeresett elemnek a gyerekelemeit megszámoljuk és lementjük 'cList'-be
					NodeList cList = elem.getChildNodes();
					//végig járjuk az adott gyerekelemet
					for(int j=0; j < cList.getLength(); j++) {	
						Node cNode = cList.item(j);
						//megváltoztatja a kikeresett gyerek elem 'name' tulajdonságát 'nameTo'-ra 
						if(cNode.getNodeType() == Node.ELEMENT_NODE) {
							if(cNode.getNodeName().equals(name)) {
							cNode.setTextContent(nameTo);
							}
						}
					}
				}
			}
		}
	}
	
	//a nodeName az elem nevét,name a gyerek elem nevét, a nameTo a gyerek elem új tartalmát tartalmazza.
	private static void modifyNodeByName(NodeList nList,String nodeName, String name, String nameTo) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				//megkeresi azokat az elemeket, amelyeknek a neve 'nodeName'
				if(elem.getNodeName().equals(nodeName)) {
					//kikeresett elemeket megszámoljuk és lementjük 'cList'-be
					NodeList cList = elem.getChildNodes();
					//végig járjuk az adott elemek gyerek elemeit
					for(int j=0; j < cList.getLength(); j++) {
						Node cNode = cList.item(j);
						//megváltoztatja a kikeresett gyerek elem 'name' tulajdonságát 'nameTo'-ra 
						if(cNode.getNodeType() == Node.ELEMENT_NODE) {
							if(cNode.getNodeName().equals(name)) {
								cNode.setTextContent(nameTo);
							}
						}
					}
				}
			}
		}
	}
	
	//kiíró függvény
	public static void listNodes(Document doc) throws TransformerFactoryConfigurationError, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
					
		DOMSource source = new DOMSource(doc);
		StreamResult console = new StreamResult(System.out);
				  
		transf.transform(source, console);
	}
	
}