package de.materna.Graph;

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
			pathname= path.concat(name);
			knoten = new Knoten();
			knoten.setId(knoten.getId() + 1);
			knoten.setVertexpath(pathname);
			knoten.setWurzel(true);
			System.out.println(knoten.getId());
			GraphBuilder.Liste.put(knoten.getVertexpath(), GraphBuilder.anotherlist);
			
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

			GraphBuilder.anotherlist.add(knoten.getVertexpath());
		}
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
//		for (String id : GraphBuilder.Liste.keySet()) {
//			System.out.println(id + ": " + GraphBuilder.Liste.get(id));
//		}
	System.out.println(GraphBuilder.createStringGraph());
	System.out.println();
		
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