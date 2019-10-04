package de.materna.GraphGsb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	public static List<String> neighborList;
	public static List<String> ausgabeKnoten = new ArrayList<>();

//	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public static ArrayList<String> getfillList() {
		return fillList;
	}

	public static void setfilllist(ArrayList<String> fillList) {
		GraphBuilder.fillList = fillList;
	}

	public GraphBuilder() {	} // ensure non-instantiability.
	
	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public static void main(String[] args) throws MalformedURLException, ExportException {	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static Graph<String, DefaultEdge> createStringGraphA() {

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
		setSelfLoops();
		getTarjan();
		return g;
	}
	
	public static void setSelfLoops() {
//		System.out.println();
//		System.out.println("an jedem Knoten werden jetzt SelfLoops hinzugefügt");

		Set<String> allvertex = g.vertexSet();
		
		

		for (Iterator<String> iter = allvertex.iterator(); iter.hasNext();) {
			String knoten = iter.next();
					g.addEdge(knoten, knoten);

		}
	//	System.out.println(g.edgeSet());
	//	System.out.println("Der neue Graph" + g);
	}
	

	@SuppressWarnings("unlikely-arg-type")
	public static Graph<String, DefaultEdge> createStringGraphB() {

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
		getTarjan();
		return g;
	}

	@SuppressWarnings("rawtypes")
	public static List getTarjan() {
		
		System.out.println();
//		System.out.println("Jetzt folgt der Tarjan-Alg.");
		boolean p = true;
		String vertex = "";
	
		cyc.setGraph(g);
		List<List<String>> listTarjan = cyc.findSimpleCycles();
		//System.out.println("Alle Tarjans" + listTarjan);
		//System.out.println();

		for (int i = 0; i < listTarjan.size(); i++) {
		//	System.out.println(listTarjan.get(i));
			for (int j = 0; j < listTarjan.get(i).size(); j++) {
				vertex = listTarjan.get(i).get(j);
				neighborList = Graphs.neighborListOf(g, vertex);
			//	System.out.println("Nachbarn von " + vertex + " sind:" + neighborList);

				for (int x = 0; x < neighborList.size(); x++) {

					if (listTarjan.get(i).contains(neighborList.get(x))) {
				//		System.out.println(neighborList.get(x) + " ist im Tarjan " + listTarjan.get(i) + " enthalten");
					} else {
//						System.out.println(
//								neighborList.get(x) + " ist im Tarjan " + listTarjan.get(i) + " NICHT enthalten");
								p = false;
					}
				}
	
			}if ( p == false) {
//				System.out.println("Tarjan nicht alleine!");
//				System.out.println("");
			} 
			 else {
//					System.out.println("Tarjan ist alleine!, dieser Tarjan wird jetzt ausgegeben und gelöscht!");
//					System.out.println("");
//					System.out.println(vertex + " wird gelöscht/ausgegeben");
					g.removeVertex(vertex);
					ausgabeKnoten.add(vertex);
		//			System.out.println("Der Graph der am Ende ausgegeben wird: " + g);
				}
				p = true;
			}
//			System.out.println("AUSGABE (erst Blätter, dann alleinstehende Tarjans, dann Rest):");
			ausgabeKnoten.addAll(g.vertexSet());
			LOG.info(ausgabeKnoten);
			ausgabeKnoten.clear();
		return listTarjan;
	}

	public static void removev() {

		String x;
		for (Iterator<String> iter = vertices.iterator(); iter.hasNext();) {
			x = iter.next();
			if (g.outgoingEdgesOf(x).size() == 0) {
				endVertices.add(x);
				ausgabeKnoten.add(x);
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
		System.out.println();
//		System.out.println("Der neue Graph!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + g.toString());
//		System.out.println();
//		System.out.println("gib die Blätter aus"+endVertices);
	}
}
