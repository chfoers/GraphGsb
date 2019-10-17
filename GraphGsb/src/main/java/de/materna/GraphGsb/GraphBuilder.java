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
	// fillList wird bei Klasse GraphContentHandler gefüllt
	public static ArrayList<String> fillList = new ArrayList<String>();
	public static Graph<String, DefaultEdge> g = new DirectedPseudograph<>(DefaultEdge.class);
	public static List<String> endVertices = new ArrayList<>();
	public static Set<DefaultEdge> outgoingEdge;
	static TarjanSimpleCycles<String, DefaultEdge> cyc = new TarjanSimpleCycles<String, DefaultEdge>();
	public static Set<String> vertices = g.vertexSet();
	public static List<String> neighborList;
	public static List<String> ausgabeKnoten = new ArrayList<>();

	public static FileOutput fileOutput = new FileOutput();

//	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public static ArrayList<String> getfillList() {
		return fillList;
	}

	public static void setfilllist(ArrayList<String> fillList) {
		GraphBuilder.fillList = fillList;
	}

	public GraphBuilder() {
	} // ensure non-instantiability.

	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);

	public static void main(String[] args) throws MalformedURLException, ExportException {
	}

	@SuppressWarnings("unlikely-arg-type")
	public static Graph<String, DefaultEdge> createStringGraphA() {
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
	
		setSelfLoops();
		getTarjan();
		return g;
	}

	public static void setSelfLoops() {
		Set<String> allvertex = g.vertexSet();
		for (Iterator<String> iter = allvertex.iterator(); iter.hasNext();) {
			String knoten = iter.next();
			g.addEdge(knoten, knoten);
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public static Graph<String, DefaultEdge> createStringGraphB() {

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

		createStringGraphOBlatt();
		getTarjan();
		return g;
	}

	@SuppressWarnings("rawtypes")
	public static List getTarjan() {

		boolean p = true;
		String vertex = "";
		cyc.setGraph(g);
		List<List<String>> listTarjan = cyc.findSimpleCycles();

		for (int i = 0; i < listTarjan.size(); i++) {
			for (int j = 0; j < listTarjan.get(i).size(); j++) {
				vertex = listTarjan.get(i).get(j);
				neighborList = Graphs.neighborListOf(g, vertex);
				for (int x = 0; x < neighborList.size(); x++) {

					if (listTarjan.get(i).contains(neighborList.get(x))) {
					} else {
						p = false;
					}
				}
			}

			if (p == true) {
				g.removeVertex(vertex);
				ausgabeKnoten.add(vertex);
				fileOutput.setAusgabeKnoten(ausgabeKnoten);
				fileOutput.write();
				ausgabeKnoten.clear();
			}
			p = true;
		}
		ausgabeKnoten.addAll(g.vertexSet());
		fileOutput.setAusgabeKnoten(ausgabeKnoten);
		fileOutput.write();
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
		fileOutput.setAusgabeKnoten(ausgabeKnoten);
		fileOutput.write();
		ausgabeKnoten.clear();
		for (String v : endVertices) {
			g.removeVertex(v);
		}
		endVertices.clear();
		vertices = g.vertexSet();

	}

	public static void createStringGraphOBlatt() {
		String s;
		removev();
		for (Iterator<String> iter = vertices.iterator(); iter.hasNext();) {
			s = iter.next();
			if (g.outgoingEdgesOf(s).size() == 0) {
				endVertices.add(s);
			}
		}
		if (endVertices.isEmpty() == false) {
			createStringGraphOBlatt();
		}
	}
}
