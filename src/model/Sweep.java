package model;
import java.util.*;
public class Sweep implements Solver{
	
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
	 
	 *  Create an instance of the Sweep class 
	 *  
	 * @param model model the current instance
	 * 
	 */
	public Sweep() {
		
	}
	
	
	/*
	 * Creates an instance of the Solution class using the Sweep algorithm 
	 * 
	 * @param model model the current instance
	 */
	
	@Override
	public Solution solve(Model model) {
		
		this.nodes = model.getNodes();
		this.cost = 0;
		this.origin = model.getOrigin();
		this.distMatrix = model.getDistMatrix();
		
		calculateRoute();
		cost = calculateRouteCost();  
		
		return new Solution(nodes, cost);
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
	

	/*
	 * Returns the nodes matrix; 
	 * 
	 * @return Node[] the matrix 
	 */
	public Node[] getNodes() {
		return nodes;
	}
	/*
	 * Returns the central node
	 * 
	 * @return Node the origin condinade
	 */
	public Node getOrigin() {
		return origin;
	}
	
	/*
	 * Returns the cost of the tour
	 * 
	 * @return double the cost of the tour
	 */
	public double getCost() {
		return cost;
	}
	
	// this method will calculate the route based on the angles
	
	

	
	/* 
	 * Calculate the tour by using the nodes matrix
	 * 
	 * <b> pre:</b> All nodes are in the first quadrant of a cartesian plane <br>
	 * 
	 * <b> post <b> The nodes matrix is reordered based on the tour 
	 */
	public void calculateRoute() {
		
		if(origin == null) {
			origin = generateOrigin();
		}
	
		
		for (int i = 0; i < nodes.length; i++) {
			
			Double x = nodes[i].getxCoord();
			Double y = nodes[i].getyCoord();
			Double angle = Math.atan(y/x);
			
			if(nodes[i].getxCoord()> origin.getxCoord() && nodes[i].getyCoord()>= origin.getyCoord()) {   //First quadrant
				angle += 0;
					
			}else if(nodes[i].getxCoord()>=origin.getxCoord() &&  nodes[i].getyCoord()<origin.getyCoord()) {  //Fourth quadrant
				angle = 180 - angle; 
				
			} else if (nodes[i].getxCoord()<origin.getxCoord() && nodes[i].getyCoord() <= origin.getyCoord()) { //Third quadrant
				angle += 180;
			
			}else if (nodes[i].getxCoord() <= origin.getxCoord() && nodes[i].getyCoord()>origin.getyCoord() ) {   //Second quadrant
				angle = 360 - angle;
				
			}
			
			nodes[i].setAngle(angle);
			
				 
		}
		
		
		Arrays.sort(nodes, new Comparator<Node>() {

			@Override
			public int compare(Node n1, Node n2) {
				return Double.compare(n1.getAngle(), n2.getAngle());
			}
		});
		
	}
	

	
	/*
	 * this method will generate the origin cordinade 
	 */
	public Node generateOrigin() {
		double xPoint = 0;
		double yPoint = 0;
		for (int i = 0; i<nodes.length;i++) {
			 xPoint += nodes[i].getxCoord();
			 yPoint += nodes[i].getyCoord();
		}
		Node origin = new Node(-1, xPoint/nodes.length, yPoint/nodes.length);
		return origin;
	}

	
	
}
