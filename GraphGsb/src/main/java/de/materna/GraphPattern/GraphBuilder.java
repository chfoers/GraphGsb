package de.materna.GraphPattern;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
//import org.jgrapht.traverse.*;

import de.materna.demos.TarjanSimpleCycles;

//import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GraphBuilder {

	public static HashMap<String, ArrayList<String>> listeKnoten = new HashMap<String, ArrayList<String>>();
	//fillList wird bei Klasse GraphContentHandler gefüllt
	public static ArrayList<String> fillList = new ArrayList<String>();
	public static Graph<String, DefaultEdge> g = new DirectedPseudograph<>(DefaultEdge.class);
	public static List<String> endVertices = new ArrayList<>();
	public static Set<DefaultEdge> outgoingEdge;
	static TarjanSimpleCycles<String, DefaultEdge> cyc = new TarjanSimpleCycles<String, DefaultEdge>();
	public static Set<String> vertices = g.vertexSet();

//	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public static ArrayList<String> getfillList() {
		return fillList;
	}

	public static void setfilllist(ArrayList<String> fillList) {
		GraphBuilder.fillList = fillList;
	}

	public GraphBuilder() {	} // ensure non-instantiability.

	public static void main(String[] args) throws MalformedURLException, ExportException {	}

	@SuppressWarnings("unlikely-arg-type")
	public static void createStringGraph() {

		/**
		 * GraphenErstellung
		 */
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
		System.out.println(g);
		createStringGraphOBlatt();
	}

	@SuppressWarnings("rawtypes")
	public static List getTarjan() {
		/**
		 * Tarjan
		 */
		cyc.setGraph(g);
		List a = cyc.findSimpleCycles();
		return a;
	}

	public static void removev() {

		String x;
		for (Iterator<String> iter = vertices.iterator(); iter.hasNext();) {
			x = iter.next();
			if (g.outgoingEdgesOf(x).size() == 0) {
				endVertices.add(x);
				
			}
		}
		for (String v : endVertices) {
			g.removeVertex(v);
		}
		vertices = g.vertexSet();
	}

	public static void createStringGraphOBlatt() {
		
		String s;
		
		for (int i=0; i<1;i++) {
			removev();
			for (Iterator<String> iter = vertices.iterator(); iter.hasNext();) {
				s = iter.next();
				if (g.outgoingEdgesOf(s).size() == 0) {
						i--;
						
				}
			}
		}
		System.out.println("Der neue Graph!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + g.toString());
		
	}
}
