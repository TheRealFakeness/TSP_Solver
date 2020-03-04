package model;

public class Sweep {
	private Node[] nodes;
	private double cost;
	private Node origin;
	
	
	//constructor
	
	public Sweep(Node[] nodes,Node origin, double cost) {
		this.nodes = nodes;
		this.cost = cost;
		this.origin = origin;
	}
	
	// getters
	
	public Node[] getNodes() {
		return nodes;
	}
	public Node getOrigin() {
		return origin;
	}
	public double getCost() {
		return cost;
	}
	
	
	
}
