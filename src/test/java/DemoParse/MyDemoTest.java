package DemoParse;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import android.provider.Contacts.Intents.Insert;

public class MyDemoTest {
	
	public static Node insertData(Document doc,String attrValue ) {
	    Element node = doc.createElement("include");
        Attr attrType1 = doc.createAttribute("name");
        attrType1.setValue(attrValue);     
        node.setAttributeNode(attrType1);
        return node;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		try {
		
			//DEmo for modify xml
			
		
		         File inputFile = new File("Demo.xml");
		         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		         Document doc = docBuilder.parse(inputFile);
		         Node test = doc.getFirstChild();
		         Node classitr = doc.getElementsByTagName("methods").item(0);
		         Node includeitr = doc.getElementsByTagName("include").item(0);
		         Element element = doc.getDocumentElement();
		         Text lineBreak = doc.createTextNode("\n");
		      
		         
		     /*    // update supercar attribute
		         NamedNodeMap attr = classitr.getAttributes();
		         Node nodeAttr = attr.getNamedItem("name");
		         nodeAttr.setTextContent("com.xp8.StabilityTest.XP8_APK_DownloadTest");*/
		         

		         // loop the supercar child node
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
		         
		     
			     
		    
				// write the content on console
		         TransformerFactory transformerFactory = TransformerFactory.newInstance();
		         Transformer transformer = transformerFactory.newTransformer();
		         DOMSource source = new DOMSource(doc);
		         Result output = new StreamResult(inputFile);
		         Source input = new DOMSource(doc);
		         transformer.transform(input, output);
		         transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		         Writer out = new StringWriter();
		         transformer.transform(new DOMSource(doc), new StreamResult(out));
		  
		         
		       
		         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        
		         System.out.println("-----------Modified File-----------");
		         StreamResult consoleResult = new StreamResult(System.out);
		         transformer.transform(source, consoleResult);
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	         
	}

}
