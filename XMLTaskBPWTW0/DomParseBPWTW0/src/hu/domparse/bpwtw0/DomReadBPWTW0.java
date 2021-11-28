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
			
			System.out.printf("gy�k�relem: %s%n",rNode.getNodeName());
			listChildNodes(nList);

		} catch (ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
		}
	}
	//megh�vjuk a gy�k�relemre a gyerekelem ki�rat� f�ggv�nyt.
	private static void listChildNodes(NodeList nList) {
		/*boolean v�ltoz� a sz�p ki�r�s�rt, hogyha az adott elem az adatbazis k�zvetlen gyerek eleme,
		 *akkor ki�r�s el�tt kihagy egy sort.*/
		boolean main = true;
		/*adatbazis k�zvetlen gyerek elemeinek neve, ezekkel hasonl�tjuk �ssze a getNodeName()-et,
		 *hogy eld�nts�k hogy az adatbazis k�zvetlen gyerek eleme-e.*/
		String nNamesMain[] = { "Publisher", "Forgalmazo", "Vasarlo", "Jatek", "Weboldal", "Indok" };
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	    	Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				//Bej�rjuk a string t�mb�t �s �sszehasonl�tjuk a NodeName-ekkel.
				for(String nName : nNamesMain) {
					if(node.getNodeName().equals(nName)) {
						main=false;
					}
				}
				//Ha az adatbazis k�zvetlen gyerek eleme, egy sort kihagyunk.
				if(!main) {
					System.out.printf("%n");
				}
				//NodeName ki�rat�sa.
				System.out.printf("%s",node.getNodeName());
				            
				//Van-e attrib�t�ma? Ha van ki�ratjuk. (.getNodeName(),getNodeValue() )
				if (node.hasAttributes()) {
					NamedNodeMap nodeMap = node.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node tempNode = nodeMap.item(i);
						System.out.printf(", %s : %s",tempNode.getNodeName(), tempNode.getNodeValue());
					}
				} else System.out.printf(": ");
				//Ha van az elemnek gyerekeleme, akkor azt nem �ratjuk ki r�gt�n
				if (node.getChildNodes().getLength()==1) {
					System.out.printf("%s%n",node.getTextContent());
				} else System.out.printf("%n");
				//gyerekelemre megh�vjuk a gyerek elemeket ki�rat� f�ggv�nyt.
				if (node.hasChildNodes()) {
					listChildNodes(node.getChildNodes());
				}
			}       
		}
	}
}