package model;

public class Sweep {
	private static Node[] nodes;
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
	
	// this method will calculate the route based on the angles
	
	public static void calculateRoute() {
		Node origin = generateOrigin(nodes);
		double M = Math.random();
		
		for (int i = 0; i <nodes.length; i++) {
			
			nodes[i].setFirstAngle(Math.atan((origin.getyCoord()-nodes[i].getyCoord())/(origin.getxCoord()-nodes[i].getxCoord()))); 
		}
		
		if (M < 0.5) {
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i].getxCoord()<origin.getxCoord() && nodes[i].getyCoord() <= origin.getyCoord()) {
					
				}else if(nodes[i].getxCoord()>=origin.getxCoord() &&  nodes[i].getyCoord()<origin.getyCoord()) {
					
				}else if (nodes[i].getxCoord()) {
					
				}
			}
		}
		
	}
	
	// this method will generate the origin point which must to be in the between all nodes
	
	
	public static Node generateOrigin(Node[] nodes) {
		double x;
		double xPoint = 0;
		double yPoint = 0;
		for (int i = 0; i<nodes.length;i++) {
			 xPoint += nodes[i].getxCoord();
			 yPoint += nodes[i].getyCoord();
		}
		Node origin = new Node(0000,xPoint/nodes.length,yPoint/nodes.length);
		return origin;
	}
	
}
