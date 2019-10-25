package de.materna.GraphGsb;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author cfoerste Parst XML-Datei liest die benötigten Informationen aus und
 *         gibt diese weiter
 */

public class GraphContentHandler implements Reader, ContentHandler {

	public String path; // Hilfsvariable, Pfadname aus XML-Datei
	public Knoten knoten;
	public String docName; // Hilfsvariable, Pfadname aus XML-Datei
	public String fullPathName; // vollständige Pfadausgabe
	public String x = "/"; // Hilfsvariable, Zeichen

	ArrayList<String> list = new ArrayList<String>(); // Zwischenliste die GraphBuilder übergeben wird
	public static FileOutput fileOutput = new FileOutput();

//	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public static void main(String[] args) {
	}

	private Stack currentElement = new Stack();

	@Override
	public void printGraphA(ArrayList<String> s) {

		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();

			for (String e : s) {
				FileReader reader = new FileReader(e);
				InputSource inputSource = new InputSource(reader);
				xmlReader.setContentHandler(new GraphContentHandler());

				xmlReader.parse(inputSource);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		fileOutput.delete();
		GraphBuilder.createStringGraphA();

	}

	@Override
	public void printGraphB(ArrayList<String> s) {

		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();

			for (String e : s) {
				FileReader reader = new FileReader(e);
				InputSource inputSource = new InputSource(reader);
				xmlReader.setContentHandler(new GraphContentHandler());

				xmlReader.parse(inputSource);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		fileOutput.delete();
		GraphBuilder.createStringGraphB();

	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (localName.equals("document") && attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String aname = attributes.getLocalName(i);
				if (aname.equals("path")) {
					path = attributes.getValue(i);
					path = path.concat(x);
				}
				if (aname.equals("name")) {
					docName = attributes.getValue(i);
				}
			}
			fullPathName = path.concat(docName);
			knoten = new Knoten();
			knoten.setId(knoten.getId() + 1);
			knoten.setVertexpath(fullPathName);

		}
		if (localName.equals("link") && attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String bname = attributes.getLocalName(i);
				if (bname.equals("href")) {
					path = attributes.getValue(i).substring(7);
				}
			}

			Long nid = knoten.getId();
			knoten = new Knoten();
			knoten.setId(nid + 1);
			knoten.setVertexpath(path);
			list.add(path);

		}
		GraphBuilder.setfilllist(list);
		currentElement.push(qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		GraphBuilder.listeKnoten.put(fullPathName, GraphBuilder.getfillList());
		currentElement.pop();
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		String cdata = new String(ch, start, length);

//	    System.out.println("Element '" + currentElement.peek()
//	      + "' contains text: " + cdata);
		if (cdata.contains("xlink:href=") == true) {
			String[] parts = cdata.split(" ");
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].contains("xlink:href=")) {
					String a = parts[i].substring(19);
					a = a.substring(0, a.length() - 1);
					// System.out.println(a);
					list.add(a);
				}
			}
		}
		;
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

}