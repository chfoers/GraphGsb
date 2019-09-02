package de.materna.Graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlReader {


	public static void main(String[] args) {
		try {
			// XMLReader erzeugen
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();

			// Pfad zur XML Datei
			FileReader reader = new FileReader(
					"C:\\Users\\cfoerste\\Desktop\\content\\standardlsg\\SharedDocs\\Personen\\DE\\bsp_person1.xml");

			InputSource inputSource = new InputSource(reader);

			// PersonenContentHandler wird übergeben
			xmlReader.setContentHandler(new GraphContentHandler());

			// Parsen wird gestartet
			xmlReader.parse(inputSource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}

}