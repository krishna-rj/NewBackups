package com.test.xmlcreator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlCreator1 {

	public void xmlCreator(Orders orders) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement("item");
			doc.appendChild(rootElement);

			Attr attr1 = doc.createAttribute("ItemGroupCode");
			attr1.setValue("0123");
			rootElement.setAttributeNode(attr1);

			Attr attr2 = doc.createAttribute("ItemID");
			attr2.setValue(Integer.toString(orders.getItemId()));
			rootElement.setAttributeNode(attr2);

			Attr attr3 = doc.createAttribute("OrganizationCode");
			attr3.setValue("Matrix");
			rootElement.setAttributeNode(attr3);

			Attr attr4 = doc.createAttribute("UnitOfMeasure");
			attr4.setValue("EACH");
			rootElement.setAttributeNode(attr4);

			// PrimaryInformation element as element1
			Element element1 = doc.createElement("PrimaryInformation");
			rootElement.appendChild(element1);

			// setting attribute to element
			Attr attr5 = doc.createAttribute("ColorCode");
			attr5.setValue("BLACK");
			element1.setAttributeNode(attr5);

			Attr attr6 = doc.createAttribute("Description");
			attr6.setValue(orders.getDescription());
			element1.setAttributeNode(attr6);

			Attr attr7 = doc.createAttribute("ExtendedDescription");
			attr7.setValue("Gates TCKWP329 Engine Timing Belt Kit with Water Pump");
			element1.setAttributeNode(attr7);

			Attr attr8 = doc.createAttribute("IsAirShippingAllowed");
			attr8.setValue("Y");
			element1.setAttributeNode(attr8);

			Attr attr9 = doc.createAttribute("IsDefaultDescriptionForParent");
			attr9.setValue("Y");
			element1.setAttributeNode(attr9);

			Attr attr10 = doc.createAttribute("IsPickupAllowed");
			attr9.setValue("Y");
			element1.setAttributeNode(attr10);

			Attr attr11 = doc.createAttribute("IsReturnable");
			attr11.setValue("Y");
			element1.setAttributeNode(attr11);

			Attr attr12 = doc.createAttribute("IsShippingAllowed");
			attr12.setValue("Y");
			element1.setAttributeNode(attr12);

			Attr attr13 = doc.createAttribute("ItemType");
			attr13.setValue("THECORNERSizeCustomItemMicroColor");
			element1.setAttributeNode(attr13);

			Attr attr14 = doc.createAttribute("Status");
			attr14.setValue("300");
			element1.setAttributeNode(attr14);

			Attr attr15 = doc.createAttribute("MasterCatalogID");
			attr15.setValue("123456");
			element1.setAttributeNode(attr15);

			Attr attr16 = doc.createAttribute("ModelItemUnitOfMeasure");
			attr16.setValue("EACH");
			element1.setAttributeNode(attr16);

			Attr attr17 = doc.createAttribute("PrimaryEnterpriseCode");
			attr17.setValue("Matrix");
			element1.setAttributeNode(attr17);

			Attr attr18 = doc.createAttribute("IsHazmat");
			attr18.setValue("N");
			element1.setAttributeNode(attr18);

			// InventoryParameters element as element1
			Element element2 = doc.createElement("InventoryParameters");
			rootElement.appendChild(element2);

			// setting attribute to element
			Attr attr19 = doc.createAttribute("ATPRule");
			attr19.setValue("DEFAULT");
			element2.setAttributeNode(attr19);

			// InventoryParameters element as element1
			Element element3 = doc.createElement("ClassificationCodes");
			rootElement.appendChild(element3);

			// setting attribute to element
			Attr attr20 = doc.createAttribute("Model");
			attr20.setValue("0001");
			element3.setAttributeNode(attr20);

			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("omit-xml-declaration", "yes");
			DOMSource source = new DOMSource(doc);

			// StreamResult result = new StreamResult(new File("D:\\orders.xml"));

			// transformer.transform(source, result);

			transformer.transform(source, result);

			String xml = writer.toString();

			// File file = new File("D:\\orders.xml");

			try {
				String filename = "D:\\orders.xml";
				FileWriter fw = new FileWriter(filename, true); // the true will append the new data
				fw.write(xml + "\n");// appends the string to the file
				fw.close();
			} catch (IOException ioe) {
				System.err.println("IOException: " + ioe.getMessage());
			}

			/*
			 * // Output to console for testing StreamResult consoleResult = new
			 * StreamResult(System.out); transformer.transform(source, consoleResult);
			 */
		}

		catch (Exception e) {
			// TODO: handle exception
		}

	}

}
