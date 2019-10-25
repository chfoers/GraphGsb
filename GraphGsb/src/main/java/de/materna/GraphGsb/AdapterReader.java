package de.materna.GraphGsb;

import java.util.ArrayList;
/**
 * 
 * @author cfoerste
 * Adapter Klasse (AdapterPattern)
 */
public class AdapterReader {
	
	public Reader reader;
	public Source source;

	public AdapterReader(Reader r, Source s) {
		reader = r;
		source = s;

}
	public void printGraphB(ArrayList<String> s) {
		reader.printGraphB(source.getList());
	}

	public void printGraphA(ArrayList<String> s2) {
		reader.printGraphA(source.getList2());

	}
	
	
}