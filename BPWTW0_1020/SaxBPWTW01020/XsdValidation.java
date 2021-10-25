package SaxBPWTW01020;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stax.StAXSource;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XsdValidation {
   public static void main(String[] args) {
	   boolean isValid = true;
	   isValid = validateXMLSchema();
       if(isValid){
    	   System.out.println("XSD Validation Succesful!");
       } else {
    	   System.out.println("XSD Validation error!");
       }
   }
   
   public static boolean validateXMLSchema(){
	   try {
		   XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		   InputStream in = new FileInputStream("src/SaxBPWTW01020/macskakBPWTW0.xml");
		   XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		   
		   SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		   Schema schema = factory.newSchema(new File("src/SaxBPWTW01020/macskakBPWTW0.xsd"));
		   
		   Validator validator = schema.newValidator();
		   validator.validate(new StAXSource(eventReader));
	      } catch (IOException e){
	          System.out.println("Exception: "+e.getMessage());
	          return false;
	       }catch(SAXException e1){
	          System.out.println("SAX Exception: "+e1.getMessage());
	          return false;
	       } catch (XMLStreamException e2) {
		  System.out.println("XMLStreamException: "+e2.getMessage());
		  return false;
		}
	   return true;
	}
}
