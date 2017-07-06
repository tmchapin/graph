package main;

import java.util.Set;
import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {
	Set<Node> nodes;
	
	Graph() {
		nodes = new HashSet<Node>();
	}
	
	Node addNode() {
		Node nd = new Node();
		nodes.add(nd);
		return nd;
	}
	
	void rmNode(Node nd) {
		for (Node nbr : nd.neighbours) {
			nbr.neighbours.remove(nd);
		}
		nodes.remove(nd);
	}
	
	void addEdge(Node nd1, Node nd2) {
		nd1.neighbours.add(nd2);
		nd2.neighbours.add(nd1);
	}
	
	void rmEdge(Node nd1, Node nd2) {
		nd1.neighbours.remove(nd2);
		nd2.neighbours.remove(nd1);
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
		// depth first implementation
		component.add(nd);
		for (Node nbr : nd.neighbours) {
			if (!component.contains(nbr)) {
				buildComponent(component, nbr);
			}
		}
	}
	
	List<Node> shortestPath(Node mouse, Node cheese) {
		// breadth first
		Queue<Step> labyrinth = new LinkedList<Step>();
		Map<Node, Node> prev = new HashMap<Node, Node>();
		Map<Node, Integer> dist = new HashMap<Node, Integer>();
		
		labyrinth.add(new Step(mouse, 0));
		prev.put(mouse, null);
		dist.put(mouse, 0);
		
		while (!labyrinth.isEmpty()) {
			Step current = labyrinth.poll();
			for (Node nbr : current.nd.neighbours) {
				if (prev.get(nbr).equals(null) || dist.get(nbr) < current.dist) {
					prev.put(nbr, current.nd);
					dist.put(nbr, current.dist);
					
					if (nbr.equals(cheese)) {
						// collecting final answer
						Stack<Node> retrace = new Stack<Node>();
						Node nd = nbr;
						while (nd != null) {
							retrace.push(nd);
							nd = prev.get(nd);
						}
						List<Node> path = new LinkedList<Node>();
						while (!retrace.isEmpty()) {
							path.add(retrace.pop());
						}
						return path;
					}
					
					for (Node newNbr : nbr.neighbours) {
						labyrinth.add(new Step(newNbr, current.dist + 1));
					}
				}
			}
		}
		return null;
	}
}

class Step {
	Node nd;
	int dist;
	
	Step(Node nd, int dist) {
		this.nd = nd;
		this.dist = dist;
	}
}