package main.java.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;



public class HelpDisplayController extends BorderPane {

	
	private static final String FILE_STATS_FXML = "/main/resources/layouts/HelpDisplay.fxml";
	
	public HelpDisplayController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_STATS_FXML));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
}
