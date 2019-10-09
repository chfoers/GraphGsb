package de.materna.demos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FileOutputTest {
	public static int z = 0;
	public static List<String> ausgabeKnoten = new ArrayList<String>();
	
	public void setAusgabeKnoten (List<String> ausgabeKnoten) {
		this.ausgabeKnoten = ausgabeKnoten;
	}
	public List<String> getAusgabeKnoten() {
		return ausgabeKnoten;
	}
	
	public void delete() {
		File p = new File("C:\\Users\\cfoerste\\p");
		File[] paths;
		paths = p.listFiles();
		// for each pathname in pathname array
        for(File path:paths) {
        
           // prints file and directory paths
           path.delete();
        }
	}
	public void write() {

		
		// Pfad wird eingelesen.
		//		Scanner meinScanner = new Scanner(System.in);
		//		System.out.print("Bitte Pfad angeben: ");
		//		String dateiname = meinScanner.next();
		
		String dateiname = "C:\\Users\\cfoerste\\p\\Ebene"+z+".txt";
		File meineDatei = new File(dateiname);
		
		if (meineDatei.exists()==false) {
			
			try (BufferedWriter meinWriter = new BufferedWriter(new FileWriter(meineDatei))) {
				for (int i = 0; i < ausgabeKnoten.size(); i++) {
					meinWriter.write(ausgabeKnoten.get(i));
					meinWriter.newLine();	
					
				}
				System.out.println("schreibe in"+dateiname);
//				// Gab es die Datei bisher nicht, wird sie nun angelegt.
//				System.out.println("Gab es die Datei bisher nicht, wird sie nun angelegt." + meineDatei.createNewFile());
//				// Hier wird sie zur Demonstration gelöscht.
//				System.out.println("Hier wird sie zur Demonstration gelöscht." + meineDatei.delete());
//				// Hier wird sie garantiert neu angelegt.
//				System.out.println("Hier wird sie garantiert neu angelegt. " + meineDatei.createNewFile());
			} catch (IOException e) {
				// Beispielsweise eine falsche Pfadangabe kann einem Fehler führen.
				System.out.println("Ein Fehler trat auf: " + e);
			}
		}
		
		
		else {
			z++;
			dateiname="C:\\Users\\cfoerste\\p\\Ebene"+z+".txt";
			meineDatei = new File(dateiname);
			try (BufferedWriter meinWriter = new BufferedWriter(new FileWriter(meineDatei))) {
				for (int i = 0; i < ausgabeKnoten.size(); i++) {
					meinWriter.write(ausgabeKnoten.get(i));
					meinWriter.newLine();
					
				}
				System.out.println("schreibe in"+dateiname);
//				// Gab es die Datei bisher nicht, wird sie nun angelegt.
//				System.out.println("Gab es die Datei bisher nicht, wird sie nun angelegt." + meineDatei.createNewFile());
//				// Hier wird sie zur Demonstration gelöscht.
//				System.out.println("Hier wird sie zur Demonstration gelöscht." + meineDatei.delete());
//				// Hier wird sie garantiert neu angelegt.
//				System.out.println("Hier wird sie garantiert neu angelegt. " + meineDatei.createNewFile());
			} catch (IOException e) {
				// Beispielsweise eine falsche Pfadangabe kann einem Fehler führen.
				System.out.println("Ein Fehler trat auf: " + e);
			}
		}
		
		
		// meinScanner.close();
		
	}

}
