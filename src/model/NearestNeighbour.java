package model;

import java.util.Random;

public class NearestNeighbour implements Solver {

	/*
	 * List of all nodes in the current instance
	 */
	private  Node[] nodes;
	/*
	 * Cost of the tour in the current instance
	 */
	private double cost;
	/*
	 * The initial node of the tour
	 */
	private Node origin;
	/*
	 * Symmetric matrix that represents the distance between nodes, 
	 * where distMatrix[i][j] is the distance from the
	 * node i to the node j
	 */
	private double[][] distMatrix;
	
	/*
	 * Creates an instance of the NearestNeighbour class
	 */
	public NearestNeighbour() {
		
	}
	
	
	@Override
	public Solution solve(Model model) {
		
		this.nodes = model.getNodes();
		this.cost = 0;
		this.origin = model.getOrigin();
		this.distMatrix = model.getDistMatrix();
		
		nodes = calculateTour();
		cost = calculateRouteCost();
	
		return new Solution(nodes, cost);
	}
	
	/*
	 * Calculates the tour of nodes
	 * 
	 * @return Node[] Matrix of nodes in tour order
	 */
	public Node[] calculateTour() {
		
		Node[] tour = new Node[nodes.length+1];
		
		if(origin == null) {
			Random r = new Random();
			origin = nodes[r.nextInt(nodes.length)];
		}
		
		tour[0] = origin;
		
		for(int i=0; i<nodes.length; i++) {
			
			boolean firstValue = false;
			int min = -1;
		
			for(int j=0; j<nodes.length; j++) {
				
				if(firstValue && distMatrix[i][j] < distMatrix[i][min] && i != j) {
					min = j;
				}
				
				if(!nodes[j].isOnRoute() && i!=j && !firstValue) {
					min = nodes[j].getId();
					firstValue = true;
				}
				
			}
			
			tour[i+1] = nodes[min];
			nodes[min].setOnRoute(true);
		
		}
		

		return tour;
	}
	
	/*
	 * Calculates the cost of the tour
	 * 
	 * @return double  the cost of the tour
	 */
	private double calculateRouteCost() {
		double cost = 0;
		for(int i=0; i<nodes.length-1; i++) {
			
			cost += distMatrix[nodes[i].getId()][nodes[i+1].getId()];
			
		}
		
		// Adds the cost from the last node of the tour to the initial node of the tour
		cost += distMatrix[nodes[nodes.length-1].getId()][nodes[0].getId()];
		
		return cost;
	}
	
	
}
