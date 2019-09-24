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
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;

import de.materna.GraphGsb.Reader;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.List;

import org.jgrapht.io.*;
import org.jgrapht.io.ExportException;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 * A simple introduction to using JGraphT.
 *
 * @author Barak Naveh
 */
public final class JGraphTTarjan 
{



    @SuppressWarnings("rawtypes")
	public static void main(String[] args)
            throws MalformedURLException,
            ExportException
        {
    		createStringGraph();
    		
        }
    public static Graph<String, DefaultEdge> createStringGraph() {
    	Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
    	

		String v0 = "v0";
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
    

        // add the vertices
        g.addVertex(v0);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        
        // add edges to create a circuit
        g.addEdge(v0, v1);
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v3, v1);
        
        
    	@SuppressWarnings("rawtypes")
		TarjanSimpleCycles cyc = new TarjanSimpleCycles();
   		cyc.setGraph(g);
        System.out.println(cyc.findSimpleCycles());
       
		return g;
    }
}
