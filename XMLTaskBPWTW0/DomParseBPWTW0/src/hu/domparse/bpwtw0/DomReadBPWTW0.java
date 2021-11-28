package hu.domparse.bpwtw0;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadBPWTW0 {
	public static void main(String args[]) {
		try {
			File xmlFile = new File("src/XMLBPWTW0.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			Node rNode = doc.getChildNodes().item(0);
			NodeList nList = rNode.getChildNodes();
			
			System.out.printf("gyökérelem: %s%n",rNode.getNodeName());
			listChildNodes(nList);

		} catch (ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
		}
	}
	//meghívjuk a gyökérelemre a gyerekelem kiírató függvényt.
	private static void listChildNodes(NodeList nList) {
		/*boolean változó a szép kiírásért, hogyha az adott elem az adatbazis közvetlen gyerek eleme,
		 *akkor kiírás elõtt kihagy egy sort.*/
		boolean main = true;
		/*adatbazis közvetlen gyerek elemeinek neve, ezekkel hasonlítjuk össze a getNodeName()-et,
		 *hogy eldöntsük hogy az adatbazis közvetlen gyerek eleme-e.*/
		String nNamesMain[] = { "Publisher", "Forgalmazo", "Vasarlo", "Jatek", "Weboldal", "Indok" };
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	    	Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				//Bejárjuk a string tömböt és összehasonlítjuk a NodeName-ekkel.
				for(String nName : nNamesMain) {
					if(node.getNodeName().equals(nName)) {
						main=false;
					}
				}
				//Ha az adatbazis közvetlen gyerek eleme, egy sort kihagyunk.
				if(!main) {
					System.out.printf("%n");
				}
				//NodeName kiíratása.
				System.out.printf("%s",node.getNodeName());
				            
				//Van-e attribútúma? Ha van kiíratjuk. (.getNodeName(),getNodeValue() )
				if (node.hasAttributes()) {
					NamedNodeMap nodeMap = node.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node tempNode = nodeMap.item(i);
						System.out.printf(", %s : %s",tempNode.getNodeName(), tempNode.getNodeValue());
					}
				} else System.out.printf(": ");
				//Ha van az elemnek gyerekeleme, akkor azt nem íratjuk ki rögtön
				if (node.getChildNodes().getLength()==1) {
					System.out.printf("%s%n",node.getTextContent());
				} else System.out.printf("%n");
				//gyerekelemre meghívjuk a gyerek elemeket kiírató függvényt.
				if (node.hasChildNodes()) {
					listChildNodes(node.getChildNodes());
				}
			}       
		}
	}
}