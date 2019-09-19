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
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;

//@example:urlCreate:end
//@example:render:begin
//@example:render:end
//@example:urlCreate:begin
//@example:urlCreate:end

/**
 * A simple introduction to using JGraphT.
 *
 * @author Barak Naveh
 */
public final class HelloJGraphT {
	final static List<String> endVertices = new ArrayList<>();
	public static Set<String> v;

	public static Graph<String, DefaultEdge> g = new DirectedWeightedPseudograph<>(DefaultEdge.class);
	public static Set<String> vertices = g.vertexSet();

	public static Set<DefaultEdge> u;

	static TarjanSimpleCycles cycl = new TarjanSimpleCycles();

	private HelloJGraphT() {
	} // ensure non-instantiability.

	/**
	 * The starting point for the demo.
	 * 
	 * @param args ignored.
	 *
	 * @throws MalformedURLException if invalid URL is constructed.
	 * @throws ExportException       if graph cannot be exported.
	 */
	public static void main(String[] args) throws MalformedURLException, ExportException {

		// @example:toString:begin
		createStringGraph();

		removev();

	}

	/**
	 * Creates a toy directed graph based on URL objects that represents link
	 * structure.
	 *
	 * @return a graph based on URL objects.
	 */

	private static void createStringGraph() {

		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";
		String v5 = "v5";
		String v6 = "v6";
		String v7 = "v7";
		String v8 = "v8";

		// add the vertices
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);
		g.addVertex(v5);
		g.addVertex(v6);
		g.addVertex(v7);
		g.addVertex(v8);

		// add edges to create a circuit
		g.addEdge(v1, v2);
		g.addEdge(v2, v3);
		g.addEdge(v3, v4);
		g.addEdge(v4, v5);
		g.addEdge(v4, v2);
		g.addEdge(v5, v6);
		g.addEdge(v6, v7);

		System.out.println(g);
		
		cycl.setGraph(g);
		System.out.println(cycl.findSimpleCycles());
		
		createStringGraphOBlatt();

	}

	public static void removev() {

		String x;
		for (Iterator<String> iter = vertices.iterator(); iter.hasNext();) {
			x = iter.next();
			if (g.outgoingEdgesOf(x).size() == 0) {
				endVertices.add(x);
				System.out.println("Diese werden gelöscht" + x);
			}
		}
		for (String v : endVertices) {

			g.removeVertex(v);
		}

		vertices = g.vertexSet();
		System.out.println("der neue Set von Knoten" + vertices);
		
	}
	
public static void createStringGraphOBlatt() {
		
		String s;
		
		for (int i=0; i<1;i++) {
			removev();
			for (Iterator<String> iter = vertices.iterator(); iter.hasNext();) {
				s = iter.next();
				if (g.outgoingEdgesOf(s).size() == 0) {
						i--;
						System.out.println(s);
						System.out.println(i);
				}
			}
		}
		System.out.println("Der neue Graph!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + g.toString());
	}

}
