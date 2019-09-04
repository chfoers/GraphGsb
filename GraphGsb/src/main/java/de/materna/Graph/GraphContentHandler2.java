package de.materna.Graph;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class GraphContentHandler2 implements ContentHandler {
	public String path;
	public Knoten knoten;
	public Kante kante;
	public GraphBuilder g;
	public String name;
	public String pathname;
	public String x = "/";
	int z = 0;
	ArrayList<String> list = new ArrayList<String>();

	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (localName.equals("document") && attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String aname = attributes.getLocalName(i);
				if (aname.equals("path")) {
					path = attributes.getValue(i);
					path = path.concat(x);
				}
				if (aname.equals("name")) {
					name = attributes.getValue(i);
				}
			}
			pathname = path.concat(name);
			knoten = new Knoten();
			knoten.setId(knoten.getId() + 1);
			knoten.setVertexpath(pathname);

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

		GraphBuilder.setAnotherlist(list);
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
	public void endElement(String uri, String localName, String qName) throws SAXException {

		GraphBuilder.Liste.put(pathname, GraphBuilder.getAnotherlist());

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