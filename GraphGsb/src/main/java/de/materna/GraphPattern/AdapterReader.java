package de.materna.GraphPattern;

import java.util.ArrayList;

public class AdapterReader implements Reader  {

		private Reader reader;
		private Source source;
	
		public  AdapterReader (Reader r, Source s) {
			reader =  r;
			source = s;
		}

		public void printGraph(ArrayList<String> s) {
			reader.printGraph(source.getList());
		
		}
}