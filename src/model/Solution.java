package model;

public class Solution {
	
	// Attributes
	
	private Node[] route;
	private double routeCost;
	
	// Constructor
	
	public Solution(Node[] route, double routeCost) {
		this.route = route;
		this.routeCost = routeCost;
	}
	
	
	// Getters
	
	public Node[] getRoute() {
		return route;
	}

	public double getRouteCost() {
		return routeCost;
	}
	
	
	
}
