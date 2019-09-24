package de.materna.GraphGsb;

public class Main {

	public static void main(String[] args) {
		
//		Source z2 = new Filesystem2();
//		Reader r2 = new GraphContentHandler();
//		r2.printGraph(z2.getList2());
		
		Source z = new Filesystem();
		Reader r = new GraphContentHandler();
		r.printGraph(z.getList());
	}
	
}
