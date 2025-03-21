/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * www.sanfoundry.com - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class TarjanAlgorithm {

	/** number of vertices **/
	private int V;
	/** preorder number counter **/
	private int preCount;
	/** low number of v **/
	private int[] low;
	/** to check if v is visited **/
	private boolean[] visited;
	/** to store given graph **/
	private HashSet<Integer>[] graph;
	/** to store all scc **/
	private List<List<Integer>> sccComp;
	private Stack<Integer> stack;

	/** function to get all strongly connected components **/
	public List<List<Integer>> getSCComponents(HashSet<Integer>[] graph) {
		V = graph.length;
		// Do not clone the graph
		this.graph = graph;
		low = new int[V];
		visited = new boolean[V];
		stack = new Stack<Integer>();
		sccComp = new ArrayList<List<Integer>>();
		for (int v = 0; v < V; v++)
			if (!visited[v])
				dfs(v);
		return sccComp;
	}

	/** function dfs **/
	public void dfs(int v) {
		low[v] = preCount++;
		visited[v] = true;
		stack.push(v);
		int min = low[v];
		for (int w : graph[v]) {
			if (!visited[w])
				dfs(w);
			if (low[w] < min)
				min = low[w];
		}
		if (min < low[v]) {
			low[v] = min;
			return;
		}
		List<Integer> component = new ArrayList<Integer>();
		int w;
		do {
			w = stack.pop();
			component.add(w);
			low[w] = V;
		} while (w != v);
		sccComp.add(component);
	}
}
