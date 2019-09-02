package de.materna.Graph;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
//import org.jgrapht.traverse.*;

//import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;

public class GraphBuilder {

	public static Knoten knoten;
	public Kante kante;

	public static HashMap<Long, String> EdgeMap = new HashMap<Long, String>();

	public HashMap<Long, String> getEdgeMap() {
		return EdgeMap;
	}

	public void setEdgeMap(long l, String path) {
		EdgeMap = getEdgeMap();
	}

	public GraphBuilder() {
	} // ensure non-instantiability.

	public static void main(String[] args) throws MalformedURLException, ExportException {

		Graph<Long, DefaultEdge> stringGraph = createStringGraph();
		
		// note undirected edges are printed as: {<v1>,<v2>}
        System.out.println("-- toString output");
        // @example:toString:begin
        System.out.println(stringGraph);
        // @example:toString:end
        System.out.println();
	}

	public static Graph<Long, DefaultEdge> createStringGraph() {
		
		Graph<Long, DefaultEdge> g = new DirectedWeightedPseudograph<>(DefaultEdge.class);
		
		Long f = (long) 1;
		Long z =(long) 2 ;
		
		g.addVertex(f);
		g.addVertex(z);
		g.addEdge(f, z);
		System.out.println(z);
	
		for (Iterator<Long> it = EdgeMap.keySet().iterator(); it.hasNext();) {
			Object key = it.next();
			System.out.println(key);
			Object vlaue = EdgeMap.get(key);
			System.out.println(vlaue);
		}
			
		return g;

	}
}
