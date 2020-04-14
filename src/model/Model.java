package model;

public class Model{
	
	// Attributes
	
	private Node[] nodes; 
	private double[][] distMatrix;
	private Node[] route; 
	private double routeCost;
	private Node origin; 

	
	// Constructor
	
	public Model(Node[] nodes) {
		this.nodes = nodes;
		this.distMatrix = calculateDistMatrix();
		this.route = null;
		this.routeCost = 0;
		this.origin = null;
	}
	
	public Model(Node[] nodes, Node[] route) {
		this.nodes = nodes;
		this.distMatrix = calculateDistMatrix();
		this.route = route;
		this.routeCost = calculateRouteCost();
		this.origin = null;
	}
	
	public Model(Node[] nodes, Node[] route, Node origin) {
		this.nodes = nodes;
		this.distMatrix = calculateDistMatrix();
		this.route = route;
		this.routeCost = calculateRouteCost();
		this.origin = origin;
	}
	
	// Methods
	
	public double[][] calculateDistMatrix(){
		
		int size = nodes.length;
		
		double[][] distMatrix = new double[size][size];
 		
		for(int i=0; i<size; i++) {
			for(int j=i; j<size; j++) {
			
				
				if(i == j) {
					distMatrix[i][j] = 0; 
					
				} else {
					double distance = calculateEuclideanDist(nodes[i].getxCoord(), nodes[i].getyCoord(), nodes[j].getxCoord(), nodes[j].getyCoord());
					distMatrix[i][j] = distance;
					distMatrix[j][i] = distance; 
				}
			}
		}
		
		return distMatrix;
		
	}
	
	public double calculateEuclideanDist(double x1, double y1, double x2, double y2) {
		
		return Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2),2)); 
	}


	public double calculateRouteCost() {
		
		double routeCost = 0;
		
		for(int i=0; i<nodes.length-1; i++) {
			
			routeCost += distMatrix[route[i].getId()][route[i+1].getId()];
			
		}
		
		routeCost += distMatrix[route[route.length-1].getId()] [route[0].getId()];
		
		return routeCost;
	}
	
	
	
	// Getters
	
	public Node[] getNodes() {
		return nodes;
	}


	public double[][] getDistMatrix() {
		return distMatrix;
	}

	public Node[] getRoute() {
		return route;
	}

	public double getRouteCost() {
		return routeCost;
	}
	
	public Node getOrigin() {
		return origin;
	}
	
	/*
	public void displayDistMatrix() {
		
		double[][] distMatrix = control.getDistMatrix();
		
		for(int i=0; i<distMatrix.length; i++) {
			for(int j=0; j<distMatrix[0].length; j++) {
				
				System.out.print(distMatrix[i][j] + " ");
			}
			
			System.out.println();
		}
		
	}
	*/
	
	
	/*
	
	public ArrayList<City> readCities(){
		
		ArrayList<City> cities = new ArrayList<City>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH)));
		
			String line = br.readLine();
			
			
			while(line != null) {
				
				String[] city = line.split(" ");
				
				cities.add(new City(Double.parseDouble(city[0]), Double.parseDouble(city[1])));
				
				
				line = br.readLine();
			}
			
			
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cities;
		
	}
	
	*/
	
}
