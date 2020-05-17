package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.*;

public class ControllerGUI {

	private boolean initialize = false;

	@FXML
	private ToggleGroup algorithm;

	@FXML
	private Canvas canvas;

	@FXML
	private Label cost;

	@FXML
	void runAlgorithm(ActionEvent event) throws IOException {

		RadioButton rb = (RadioButton) algorithm.getSelectedToggle();
		String algorithm = rb.getText();

		if (algorithm.contentEquals("Nearest Neighbor")) {
			nearestNeighbor();
		} else if (algorithm.contentEquals("Sweep")) {
			sweep();
		} else if (algorithm.contentEquals("Clark And Wright")) {
			clarkAndWright();
		} else if (algorithm.contentEquals("Opt3Move")) {
			opt3Move();
		}

	}

	private void initialize() {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.translate(0, canvas.getHeight());
		gc.scale(1, -1);

	}

	public ControllerGUI() {

	}

	private void nearestNeighbor() throws IOException {

		FileReaderLIB fr = new FileReaderLIB();
		Model model = fr.readFile();

		NearestNeighbour nn = new NearestNeighbour();
		Solution sol = nn.solve(model);

		cost.setText(sol.getRouteCost() + "");
		plotRoute(sol);

	}

	private void sweep() throws IOException {
		FileReaderLIB fr = new FileReaderLIB();
		Model model = fr.readFile();

		Sweep sweep = new Sweep();
		Solution sol = sweep.solve(model);

		cost.setText(sol.getRouteCost() + "");
		plotRoute(sol);
	}

	private void clarkAndWright() throws IOException {
		FileReaderLIB fr = new FileReaderLIB();
		Model model = fr.readFile();

		ClarkeAndWright cw = new ClarkeAndWright();
		Solution sol = cw.solve(model);

		// cost.setText(sol.getRouteCost() + "");
		plotRoute(sol);
	}

	private void opt3Move() {

	}

	public void plotRoute(Solution sol) {

		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		if (initialize == false) {
			initialize();
			initialize = true;
		}

		Node[] route = sol.getRoute();
		double num = 10;
		
		
		for(int z=0; z <route.length; z++) {
			Node i = route[z];
			gc.setStroke(javafx.scene.paint.Color.BLACK);
			gc.fillOval(i.getxCoord() * num, i.getyCoord() * num, 5, 5);
		}
		
		for (int z = 0; z < route.length - 1; z++) {

			Node i = route[z];
			Node j = route[z + 1];

			/*
			gc.setStroke(javafx.scene.paint.Color.BLACK);
			gc.fillOval(i.getxCoord() * num, i.getyCoord() * num, 5, 5);
			 */
			
			gc.setStroke(javafx.scene.paint.Color.RED);
			gc.strokeLine(i.getxCoord() * num, i.getyCoord() * num, j.getxCoord() * num, j.getyCoord() * num);

			// System.out.println(i.getxCoord()*num + " " + i.getyCoord()*num);

		}

		Node last = route[route.length - 1];
		Node first = route[0];

		gc.strokeLine(last.getxCoord() * num, last.getyCoord() * num, first.getxCoord() * num, first.getyCoord() * num);

		/*
		 * for(int i=0; i<route.length; i++) { Node node = route[i];
		 * System.out.println(node.getxCoord() + " " + node.getyCoord()); }
		 */

	}

	private void printMatrix(Node[] nodes) {

		for (int i = 0; i < nodes.length; i++) {

			System.out.println(nodes[i].getxCoord() + " " + nodes[i].getyCoord());

		}

	}

}
