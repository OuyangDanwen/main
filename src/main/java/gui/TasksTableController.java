package main.java.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import main.java.flash.Main;

public class TasksTableController extends BorderPane {

	private Main mainApp;

	@FXML
	private ListView<TasksItemController> tasksDisplay;

//	@FXML
//	private Label title;

	private static final String FILE_STATS_FXML = "/main/resources/layouts/TasksTable.fxml";

	private ArrayList<TasksItemController> items;
	
	public TasksTableController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_STATS_FXML));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//title.setText(taskName);
		initialise();
	}
	
	private void initialise() {
		items = new ArrayList<TasksItemController>();
		tasksDisplay.setItems(FXCollections.observableList(items));
	}

	public void addTask(String taskName) {	
	
		addFileStatsItem(taskName);
		
		Collections.sort(items);

		tasksDisplay.setItems(FXCollections.observableList(items));
	}

	/**
	 * Each TaskItems displayed as a row
	 * in this custom view.
	 * 
	 * @param currentFile
	 * @param currentNumLines
	 */
	private void addFileStatsItem(String taskName) {
		items.add(new TasksItemController(taskName));
	}

}