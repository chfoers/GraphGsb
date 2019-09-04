package de.materna.Graph;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
//import org.jgrapht.traverse.*;

//import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;


public class GraphBuilder {

	private static final Logger LOG = LogManager.getLogger(GraphBuilder.class);
	
	public static Knoten knoten;
	public Kante kante;

	public static HashMap<String, ArrayList<String>> Liste = new HashMap<String, ArrayList<String>>();
	public static ArrayList<String> anotherlist = new ArrayList<String>();

	public HashMap<String, ArrayList<String>> getEdgeMap2() {
		return Liste;
	}

	public void setEdgeMap2(String s, ArrayList<String> x) {
		Liste = getEdgeMap2();
	}

	public static ArrayList<String> getAnotherlist() {
		return anotherlist;
	}

	public static void setAnotherlist(ArrayList<String> anotherlist) {
		GraphBuilder.anotherlist = anotherlist;
	}

	public GraphBuilder() {
	} // ensure non-instantiability.

	public static void main(String[] args) throws MalformedURLException, ExportException {
		

//		Graph<String, DefaultEdge> stringGraph = createStringGraph();
//
//		// note undirected edges are printed as: {<v1>,<v2>}
//		System.out.println("-- toString output");
//		// @example:toString:begin
//		System.out.println(stringGraph);
//		// @example:toString:end
//		System.out.println();
	}

	public static Graph<String, DefaultEdge> createStringGraph() {
		
		Graph<String, DefaultEdge> g = new DirectedWeightedPseudograph<>(DefaultEdge.class);
			for (String s : Liste.keySet()) {
				for( String z : Liste.get(s)) {
					if (Liste.containsValue(z) == false){
						g.addVertex(s);
						g.addVertex(z);
						g.addEdge(s, z);}
					else {
					g.addVertex(s);	
					}	
				}
			}
		
		return g;
	}
	}

