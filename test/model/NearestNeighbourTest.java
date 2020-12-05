package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NearestNeighbourTest {

	private NearestNeighbour nn;
	private Model model;
	
	public void setupStage1() {
		nn = new NearestNeighbour();
		
		Node[] route = {new Node(1, 10, -20), new Node(2, -8, 12), new Node(3, 0, -2),
				new Node(4, 8, 22)};
		
		model = new Model(route, route, route[0]);
	}
	
	@Test
}
