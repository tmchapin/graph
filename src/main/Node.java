package main;

import java.util.Set;
import java.util.HashSet;

public class Node {
	
	Set<Node> neighbours;
	
	Node() {
		neighbours = new HashSet<Node>(); 
	}
	
	void addNbr(Node nd) {
		neighbours.add(nd);
	}
	
	void rmNbr(Node nd) {
		neighbours.remove(nd);
	}
}