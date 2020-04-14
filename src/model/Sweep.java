package model;
import java.util.*;
public class Sweep implements Solver{
	
	private  Node[] nodes;
	private double cost;
	private Node origin;
	private double[][] distMatrix;
	
	
	
	
	//constructor
	
	public Sweep(Model model) {
		
		this.nodes = model.getNodes();
		this.cost = 0;
		this.origin = model.getOrigin();
		this.distMatrix = model.getDistMatrix();
		
	}
	
	@Override
	public Solution solve(Model model) {
		
		calculateRoute();
		calculateRouteCost(); 
		
		return new Solution(nodes, cost);
	}
	
	
	private void calculateRouteCost() {
		
		for(int i=0; i<nodes.length-1; i++) {
			
			cost += distMatrix[nodes[i].getId()][nodes[i+1].getId()];
			
		}
		
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
	
	
	//Precondición: Todos los nodos se encuentran en el primer cuadrante
	public void calculateRoute() {
		
		if(origin == null) {
			origin = generateOrigin();
		}
	
		
		for (int i = 0; i < nodes.length; i++) {
			
			Double x = nodes[i].getxCoord();
			Double y = nodes[i].getyCoord();
			Double angle = Math.atan(y/x);
			
			if(nodes[i].getxCoord()> origin.getxCoord() && nodes[i].getyCoord()>= origin.getyCoord()) { //Cuadrante 1
				angle += 0;
					
			}else if(nodes[i].getxCoord()>=origin.getxCoord() &&  nodes[i].getyCoord()<origin.getyCoord()) {  // Cuadrante 4
				angle = 180 - angle; 
				
			} else if (nodes[i].getxCoord()<origin.getxCoord() && nodes[i].getyCoord() <= origin.getyCoord()) { // Cuadrante 3
				angle += 180;
			
			}else if (nodes[i].getxCoord() <= origin.getxCoord() && nodes[i].getyCoord()>origin.getyCoord() ) {   // Cuadrante 2
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
	

	
	// this method will generate the origin point which must to be in the between all node
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
