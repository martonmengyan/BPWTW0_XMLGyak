package xpathbpwtw01110;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class xPathBPWTW0 {
	
	public static void main(String[]args) {
		
		try {
			File xmlFile = new File("src/studentBPWTW0.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document document = dBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//1.
			String expression = "class/student"; 
			//2.
			//String expression = "//student[@id='01']";
			//3.
			//String expression = "//student";
			//4.
			//String expression = "/class/student[position()=2]";
			//5.
			//String expression = "/class/student[last()]";
			//6.
			//String expression = "/class/student[last()-1]";
			//7. 
			//String expression = "/class/student[position()<3]";
			//8.
			//String expression = "/class/*";
			//9.
			//String expression = "//student[@*]";
			//10.
			//String expression = "//*";
			//11.
			//String expression = "/class/student[kor>20]";
			
			
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for(int i=0; i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("Current element:" + node.getNodeName());
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					if(node.getNodeName().equals("student")) {
				
					
					Element element = (Element) node;
					
					System.out.println("Hallgató ID: " + element.getAttribute("id"));
					System.out.println("Keresztnev: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println("Vezeteknev: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becenev: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
					System.out.println("");
				}
				}
			
			}
		
	} catch(SAXException | IOException | XPathExpressionException | ParserConfigurationException e) {
        e.printStackTrace();
	}
} 

}
