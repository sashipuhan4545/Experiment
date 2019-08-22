package DemoParse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class CreateXmlTest {
	

	public static void main(String[] args) throws TransformerException {
	
		 String filePath = "Demo.xml";
	        File xmlFile = new File(filePath);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        dbFactory.setValidating(true);
	        dbFactory.setIgnoringElementContentWhitespace(true);
	        DocumentBuilder dBuilder;
	        
	        try {
	            dBuilder = dbFactory.newDocumentBuilder();
	            
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            Node classitr = doc.getElementsByTagName("methods").item(0);
	            
//	            deleteElement(doc);
                 removeEmptyNodes(classitr, "methods");	            
//	            addElement(doc, "XP8_TC_",4);
      
	            doc.getDocumentElement().normalize();
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File("Demo.xml"));
	            StreamResult consoleResult = new StreamResult(System.out);
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.transform(source, result);
	   
	            System.out.println("XML file updated successfully");
	            transformer.transform(source, consoleResult);
	        }
	  catch (SAXException| ParserConfigurationException | IOException   e ) {
         e.printStackTrace();
     }
	}

	
	 private static void deleteElement(Document doc) {
		  Node classitr = doc.getElementsByTagName("methods").item(0);
		  NodeList list = classitr.getChildNodes();
		     
	         for (int temp = 0; temp < list.getLength(); temp++) {
	            Node node = list.item(temp);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               if ("include".equals(eElement.getNodeName())) { 
	            	 System.out.println("Im in ");
	            	 String str =eElement.getAttributes().getNamedItem("name").getNodeValue();
	            	 System.out.println(str);
	                  if((eElement.getAttributes().getNamedItem("name").getNodeValue()).contains("XP8_TC_")) {
	                	  System.out.println("Im in Contains");		                 
	                  eElement.getParentNode().removeChild(eElement);
	                  continue;
	                  }
	              }
	            }
	         }         
	      }
	 
	 
	 public static void removeEmptyNodes(Node node1, String nameToRemove) throws FileNotFoundException {
		 try {
			/*NodeList nodeList = node.getChildNodes();
			for(int i=0; i < nodeList.getLength(); i++){
				Node childNode = nodeList.item(i);
				String nodeName = childNode.getNodeName(); 
				if(nodeName.equals(nameToRemove) && childNode.getTextContent().equals("")){
					childNode.getParentNode().removeChild(childNode);
					i--;
				}
				removeEmptyNodes(childNode, nameToRemove);
			}
		        */
			   NodeList list = node1.getChildNodes();
			 for (int temp = 0; temp < list.getLength(); temp++) {
		            Node node = list.item(temp);
		            if (node.getNodeType() == Node.ELEMENT_NODE) {
		               Element eElement = (Element) node;
		               System.out.println(eElement.getNodeName());
		               if ("".equals(eElement.getNodeName())) { 
			            	 System.out.println("Im in ");
		               }
		            }
			 }

		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}
	  
	 private static void addElement(Document doc,String attrValue, int num) {
	       Node classitr = doc.getElementsByTagName("methods").item(0);
	       
	       for(int temp=1; temp<num; temp++) {
		    Element include = doc.createElement("include");
	        Attr attrType1 = doc.createAttribute("name");
	        attrType1.setValue(attrValue);     
	        include.setAttributeNode(attrType1);
	        classitr.appendChild(include);
	       }	        	       
	    }
	 
	 
	 
}
