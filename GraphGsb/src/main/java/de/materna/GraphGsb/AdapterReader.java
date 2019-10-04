package de.materna.GraphGsb;

import java.util.ArrayList;

public class AdapterReader implements Reader  {

		private Reader reader;
		private Source source;
	
		public  AdapterReader (Reader r, Source s) {
			reader =  r;
			source = s;
			
		}

		public void printGraphB(ArrayList<String> s) {
			reader.printGraphB(source.getList());
		
		}

		@Override
		public void printGraphA(ArrayList<String> s2) {
			reader.printGraphA(source.getList2());
			
		}
}