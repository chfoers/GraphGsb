package de.materna.GraphPattern;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
//import org.jgrapht.traverse.*;

import de.materna.demos.TarjanSimpleCycles;

//import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphBuilder {

	public static HashMap<String, ArrayList<String>> listeKnoten = new HashMap<String, ArrayList<String>>();
	public static ArrayList<String> fillList = new ArrayList<String>();

	public static ArrayList<String> getfillList() {
		return fillList;
	}

	public static void setfilllist(ArrayList<String> fillList) {
		GraphBuilder.fillList = fillList;
	}

	public GraphBuilder() {
	} // ensure non-instantiability.

	public static void main(String[] args) throws MalformedURLException, ExportException {
	}

	@SuppressWarnings("unlikely-arg-type")
	public static Graph<String, DefaultEdge> createStringGraph() {

		Graph<String, DefaultEdge> g = new DirectedWeightedPseudograph<>(DefaultEdge.class);
		for (String s : listeKnoten.keySet()) {
			for (String z : listeKnoten.get(s)) {
				if (listeKnoten.containsValue(z) == false) {
					g.addVertex(s);
					g.addVertex(z);
					g.addEdge(s, z);
				} else {
					g.addVertex(s);
				}
			}
		}
		TarjanSimpleCycles cyc = new TarjanSimpleCycles();
   		cyc.setGraph(g);
        System.out.println("TARJAN:______________________________________________________"+ cyc.findSimpleCycles());
		return g;
	}
}
