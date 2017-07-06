package main;

class TreeGraph extends Graph {
	int countEdges() {
		return countNodes() - 1;
	}
	
	boolean isConnected() {
		return true;
	}
}
