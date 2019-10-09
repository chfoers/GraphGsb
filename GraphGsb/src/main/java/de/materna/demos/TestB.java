/*
 * (C) Copyright 2003-2018, by Barak Naveh and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */
package de.materna.demos;

//@example:urlCreate:begin

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;




import java.io.*;


import java.util.*;




public final class TestB {
	final static List<String> endVertices = new ArrayList<>();
	public static Set<String> v;
	public static FileOutputTest fileOutputTest = new FileOutputTest();
	public static Graph<String, DefaultEdge> g = new DirectedWeightedPseudograph<>(DefaultEdge.class);
	public static Set<String> vertices = g.vertexSet();
	public static Set<String> tarjans;
	public static List<String> ausgabeKnoten = new ArrayList<String>();
	public static List<String> neighborList;

	public static Set<DefaultEdge> u;
	static TarjanSimpleCycles<String, DefaultEdge> cyc = new TarjanSimpleCycles<String, DefaultEdge>();

	private TestB() {
	} // ensure non-instantiability.

	/**
	 * The starting point for the demo.
	 * 
	 * @param args ignored.
	 *
	 * @throws ExportException       if graph cannot be exported.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ExportException, IOException {
		
		long time = System.currentTimeMillis();
		
        reportPerformanceFor("starting at", time);
		// @example:toString:begin
        fileOutputTest.delete();
		createStringGraph();
		reportPerformanceFor("graph allocation", time);
		
		 
	}

	/**
	 * Creates a toy directed graph based on URL objects that represents link
	 * structure.
	 *
	 * @return a graph based on URL objects.
	 * @throws IOException 
	 */

	private static void createStringGraph() throws IOException {

		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";
		String v5 = "v5";
		String v6 = "v6";
		String v7 = "v7";
		String v8 = "v8";
		String v9 = "v9";
		String v10 = "v10";
		String v11 = "v11";

		// add the vertices
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);
		g.addVertex(v5);
		g.addVertex(v6);
		g.addVertex(v7);
		g.addVertex(v8); // alleiniger Knoten..
		g.addVertex(v9); // Knoten mit SelfLoop, Tarjan
		g.addVertex(v10);// Knoten mit SelfLoop, Tarjan
		g.addVertex(v11);

		// add edges to create a circuit
		g.addEdge(v1, v2);
		g.addEdge(v2, v3);
		g.addEdge(v3, v4);
		g.addEdge(v4, v5);
		g.addEdge(v4, v2);
		g.addEdge(v5, v6);
		g.addEdge(v6, v7);
		g.addEdge(v9, v9);
		g.addEdge(v3, v9);
		g.addEdge(v10, v10);
		g.addEdge(v5, v11);
		g.addEdge(v11, v7);
		
//		g.addEdge(v2, v1);
//		g.addEdge(v3, v2);
//		g.addEdge(v4, v3);
//		g.addEdge(v5, v4);
//		g.addEdge(v2, v4);
//		g.addEdge(v6, v5);
//		g.addEdge(v7, v6);
//		g.addEdge(v9, v9);
//		g.addEdge(v9, v3);
//		g.addEdge(v10, v10);	

		createStringGraphOBlatt();
		getTarjan();
	}

	@SuppressWarnings("rawtypes")
	public static List getTarjan() throws IOException {
		
		boolean p = true;
		String vertex = "";
		cyc.setGraph(g);
		List<List<String>> listTarjan = cyc.findSimpleCycles();

		for (int i = 0; i < listTarjan.size(); i++) {
			for (int j = 0; j < listTarjan.get(i).size(); j++) {
				vertex = listTarjan.get(i).get(j);
				neighborList = Graphs.neighborListOf(g, vertex);
				for (int x = 0; x < neighborList.size(); x++) {

					if (listTarjan.get(i).contains(neighborList.get(x))) {} 
					else {
						p = false;
					}
				}
			}
			
			if (p == true) {
				g.removeVertex(vertex);
				ausgabeKnoten.add(vertex);
				fileOutputTest.setAusgabeKnoten(ausgabeKnoten);
				fileOutputTest.write();
				ausgabeKnoten.clear();
			}
			p = true;
		}
		ausgabeKnoten.addAll(g.vertexSet());
		fileOutputTest.setAusgabeKnoten(ausgabeKnoten);
		fileOutputTest.write();
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
		fileOutputTest.setAusgabeKnoten(ausgabeKnoten);
		fileOutputTest.write();
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

	private static void reportPerformanceFor(String msg, long refTime)
    {
        double time = (System.currentTimeMillis() - refTime) / 1000.0;
        double mem = usedMemory() / (1024.0 * 1024.0);
        mem = Math.round(mem * 100) / 100.0;
        System.out.println(msg + " (" + time + " sec, " + mem + "MB)");
    }

    private static long usedMemory()
    {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

    
}
