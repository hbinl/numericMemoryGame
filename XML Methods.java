import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

private void writeXML() {
        // For writing into a new XML file
        try {
            System.out.println("Start");
            String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "SEACO" + "/" + "userData.xml";
            File file = new File(filepath);
            boolean fileCreated = file.createNewFile();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            Element root = doc.createElement("seaco");
            doc.appendChild(root);
            Element shape = doc.createElement("shape");
            root.appendChild(shape);
            Element initialAnswer = doc.createElement("initialAnswer");
            shape.appendChild(initialAnswer);
            initialAnswer.appendChild(doc.createTextNode("1"));

            // update staff attribute
            //Node staff = doc.getElementsByTagName("staff").item(0);
            //NamedNodeMap attr = staff.getAttributes();
            //Node nodeAttr = attr.getNamedItem("id");
            //nodeAttr.setTextContent("2");

            // append a new node to staff
            //Element age = doc.createElement("age");
            //age.appendChild(doc.createTextNode("28"));
            //staff.appendChild(age);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));  
            transformer.transform(source, result);

            //system.out log
            StreamResult resultx = new StreamResult(System.out);
            transformer.transform(source, resultx);
            System.out.println("Done");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
}

private void updateXML() {
        try {
            System.out.println("Start");
            String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "SEACO" + "/" + "userData.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // key difference between writeNew and update: .parse() vs .newDocument()
            Document doc = docBuilder.parse("file://" + filepath);

            Node root = doc.getFirstChild();
            Node shape = doc.getElementsByTagName("shape").item(0);
            Element finalAnswer = doc.createElement("finalAnswer");
            finalAnswer.appendChild(doc.createTextNode("2"));
            shape.appendChild(finalAnswer);

            Node pair = doc.createElement("pair");
            root.appendChild(pair);
            Element noOfColumn = doc.createElement("noOfColumn");
            pair.appendChild(noOfColumn);

            //Write into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            //System.out log
            StreamResult resultx = new StreamResult(System.out);
            transformer.transform(source, resultx);
            System.out.println("Done");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException ioe) {
            ioe.printStackTrace();
        }



        

            /*initialAnswer.appendChild(doc.createTextNode("1"));
            shape.appendChild(initialAnswer);
            seaco.appendChild(shape);
            root.appendChild(seaco);
            doc.appendChild(root);

            //Node company = doc.getFirstChild();

            System.out.println("Ddx");
            //
            //Node staff = doc.getElementsByTagName("staff").item(0);

            // update staff attribute
            //NamedNodeMap attr = staff.getAttributes();
            //Node nodeAttr = attr.getNamedItem("id");
            //nodeAttr.setTextContent("2");

            // append a new node to staff
            //Element age = doc.createElement("age");
            //age.appendChild(doc.createTextNode("28"));
            //staff.appendChild(age);

            // loop the staff child node
            //NodeList list = staff.getChildNodes();
            /*
            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                // get the salary element, and update the value
                if ("salary".equals(node.getNodeName())) {
                    node.setTextContent("2000000");
                }

                //remove firstname
                if ("firstname".equals(node.getNodeName())) {
                    staff.removeChild(node);
                }

            } */

            // write the content into xml file