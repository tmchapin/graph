package main;

class TreeNode extends Node {
	Node parent;
	
	TreeNode(Node nd) {
		super(); // examine for error/redundancy
		parent = nd;
	}
}
