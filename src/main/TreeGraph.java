package main;

class TreeGraph extends Graph {
	Node addNode(Node parent) {
		Node nd = new TreeNode(parent);
		nodes.add(nd);
		return nd;
	}
	
	void rmNode(Node nd) {
		for (Node nbr : nd.neighbours) {
			rmNode(nbr);
		}
		nodes.remove(nd);
	}
	
	int countEdges() {
		return countNodes() - 1;
	}
	
	boolean isConnected() {
		return true;
	}
}
