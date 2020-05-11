package model;

import java.util.ArrayList;
import java.util.Random;

public class ClarkeAndWright implements Solver {

	/*
	 * Symmetric matrix that represents the distance between nodes, where
	 * distMatrix[i][j] is the distance from the node i to the node j
	 */
	private double[][] distMatrix;

	/*
	 * List of all nodes in the current instance
	 */
	private Node[] nodes;

	/*
	 * The initial node of the tour
	 */
	private Node origin;

	/*
	 * Number of tours
	 */
	private int tourNums;

	/*
	 * Savings matrix of joining the first node of tour i with the first node of the
	 * tour j
	 */
	private Double[][] s1;

	/*
	 * Savings matrix of joining the first node of tour i with the last node of the
	 * tour j
	 */
	private Double[][] s2;

	/*
	 * Savings matrix of joining the last node of tour i with the first node of tour
	 * j
	 */
	private Double[][] s3;

	/*
	 * Savings matrix of joining the last node of tour i with the last node of tour
	 * j
	 */
	private Double[][] s4;

	ArrayList<ArrayList<Node>> tours;

	/*
	 * Creates an instance of the ClarkeAndWright class
	 */
	public ClarkeAndWright() {

	}

	@Override
	public Solution solve(Model model) {
		this.distMatrix = model.getDistMatrix();
		this.nodes = model.getNodes();
		this.origin = model.getOrigin();
		this.tourNums = nodes.length - 1;

		Node[] route = algorithm();

		return null;
	}

	private Node[] algorithm() {

		if (origin == null) {
			Random r = new Random();
			origin = nodes[r.nextInt(nodes.length)];
		}

		tours = new ArrayList<ArrayList<Node>>();

		for (int k = 0; k < nodes.length; k++) {

			if (nodes[k].equals(origin) == false) {
				ArrayList<Node> tour = new ArrayList<Node>();
				tour.add(nodes[k]);

				tours.add(tour);

			}

		}

		while (tourNums > 1) {
			
			double[][] s1 = calculateSMatrix(false, true);
			double[][] s2 = calculateSMatrix(true, true);
			double[][] s3 = calculateSMatrix(true, false);
			double[][] s4 = calculateSMatrix(false, false);
			
			
			
			tourNums--;
		}

		return null; // TODO Hay que cambiarlo
	}
	
	private void getMaxValue(double[][] matrix) {
		
		int minI = 0;
		int minJ = 0;
		
		
		for(int i=1; i<matrix.length; i++) {
			
			for(int j=(i+1); j<matrix[0].length; j++) {
				
				if(matrix[i][j] < matrix[minI][minJ]) {
					minI = i;
					minJ = j;
				}
				
			}
			
		}
		
		
		
	}
	
	private double[][] calculateSMatrix(boolean firstNodeA, boolean firstNodeB) {

		double[][] s1 = new double[tourNums][tourNums];

		ArrayList<Node> tourA;
		Node nodeA;

		ArrayList<Node> tourB;
		Node nodeB;

		for (int i = 0; i < s1.length; i++) {

			tourA = tours.get(i);
			
			if(firstNodeA == true) {
				nodeA = tourA.get(0); 
			}else {
				nodeA = tourA.get(tourA.size() - 1); 
			}
			

			for (int j = i + 1; j < s1[0].length; j++) {

				tourB = tours.get(j);
				
				if(firstNodeB == true) {
					nodeB = tourB.get(0);
				}else {
					nodeB = tourB.get(tourB.size()-1);
				}

				s1[i][j] = distMatrix[origin.getId()][nodeA.getId()]
						+ distMatrix[origin.getId()][nodeB.getId()]
						+ distMatrix[nodeA.getId()][nodeB.getId()];

			}

		}

		return s1;

	}
	
	

}
