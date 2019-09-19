package de.materna.GraphPattern;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

@SuppressWarnings("deprecation")
public class GraphContentHandler implements Reader, ContentHandler {

	public String path;
	public Knoten knoten;
	public String docName;
	public String fullPathName;
	public String x = "/";

	ArrayList<String> list = new ArrayList<String>();

	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public static void main(String[] args) {	}

	// erstelle Graph aus der Quelle s (Source)
	@Override
	public void printGraph(ArrayList<String> s) {

		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();

			for (String e : s) {
				FileReader reader = new FileReader(e);
				InputSource inputSource = new InputSource(reader);
				xmlReader.setContentHandler(new GraphContentHandler());

				// Parsen wird gestartet
				xmlReader.parse(inputSource);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		GraphBuilder.createStringGraph();
		LOG.info("TARJAN:"+GraphBuilder.getTarjan());
		
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
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
			GraphBuilder.listeKnoten.put(fullPathName, GraphBuilder.getfillList());
		
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

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

}