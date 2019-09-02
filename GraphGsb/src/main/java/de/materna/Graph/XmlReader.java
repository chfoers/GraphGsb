package de.materna.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.xml.parsers.SAXParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

@SuppressWarnings("deprecation")
public class XmlReader {
	
	
	public static ArrayList<File> ff = new ArrayList<File>();
	public static String temp;
	public static FileReader reader;
	public static InputSource inputSource;

	public static void main(String[] args) {
		
		
		try {
			// XMLReader erzeugen
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();

			 File file = new File("C:\\Users\\cfoerste\\Desktop\\content\\standardlsg\\SharedDocs\\Personen\\DE");
			// fetchFiles(file, f -> System.out.println(f.getAbsolutePath()));
			 fetchFiles(file, f -> f.getAbsolutePath());
		     for( File curFile : ff )
		        {
		            temp = curFile.toString();	
		 		    FileReader reader = new FileReader(temp);
		     
//			  FileReader reader = new FileReader(
//					"C:\\Users\\cfoerste\\Desktop\\content\\standardlsg\\SharedDocs\\Personen\\DE\\bsp_person2.xml");
//	   
			
			InputSource inputSource = new InputSource(reader);

			// PersonenContentHandler wird übergeben
			xmlReader.setContentHandler(new GraphContentHandler2());
		
		     
	       
			// Parsen wird gestartet
			xmlReader.parse(inputSource);
		        }  } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		}
	
	public static void fetchFiles(File dir, Consumer<File> fileConsumer) {
			
	      if (dir.isDirectory()) {
	          for (File file1 : dir.listFiles()) {
	              fetchFiles(file1, fileConsumer);
	             ff.add(file1);
	          }
	      } else {
	          fileConsumer.accept(dir);
	      }
	      }
}