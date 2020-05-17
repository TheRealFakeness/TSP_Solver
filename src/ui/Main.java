package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	private ControllerGUI controller;
	
	public Main() {
		controller = new ControllerGUI();
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
		fxmlLoader.setController(controller);
		BorderPane bp = fxmlLoader.load();
		
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.setTitle("TSP Solver");
		
		stage.show();
		
	}

}
