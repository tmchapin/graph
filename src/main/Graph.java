package main;

import java.util.Set;
import java.util.HashSet;

public class Graph {
	Set<Node> nodes;
	
	Graph() {
		nodes = new HashSet<Node>();
	}
	
	int countNodes() {
		return nodes.size();
	}
	
	int countEdges() {
		int tally = 0;
		for (Node nd : nodes) {
			tally += nd.neighbours.size();
		}
		return tally/2;
	}
	
	boolean isConnected() {
		if (nodes.isEmpty()) {
			return true; // by convention, shush you
		} else {
			Set<Node> component = new HashSet<Node>();
			buildComponent(component, nodes.iterator().next());
			return component.size() == nodes.size();
		}
	}
	
	void buildComponent(Set<Node> component, Node nd) {
		// depth first implementation, O(n)
		component.add(nd);
		for (Node nbr : nd.neighbours) {
			if (!component.contains(nbr)) {
				buildComponent(component, nbr);
			}
		}
	}
}