package main.java.gui;

import java.io.IOException;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main.java.data.Task;

public class TasksTableController extends BorderPane {

	@FXML
	private ListView<TasksItemController> tasksDisplay;

	@FXML
	private Label title;
	
	@FXML
    private ImageView helpImage;

	private static final String FILE_STATS_FXML = "/main/resources/layouts/TasksTable.fxml";
	private static final String HELP_ICON = "/main/resources/images/help.fw.png";
	private ArrayList<TasksItemController> items;
	protected String taskname;
	

	public TasksTableController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_STATS_FXML));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		initialise();
	}
	


	private void initialise() {
		this.items = new ArrayList<TasksItemController>();
		this.tasksDisplay.setItems(FXCollections.observableList(items));
		
	}

	public ListView<TasksItemController> getListView() {
		return tasksDisplay;
	}

	public void addTask(Task task) {
		setTasksItem(task);
		tasksDisplay.setItems(FXCollections.observableList(items));
	}

	/**
	 * Each TaskItems displayed as a row in this custom view.
	 * 
	 * @param count
	 * 
	 * @param currentFile
	 * @param currentNumLines
	 */
	private void setTasksItem(Task task) {	
		items.add(new TasksItemController(task));  

	}

	public void setItems(ObservableList<TasksItemController> subentries) {
		tasksDisplay.setItems(subentries);
		tasksDisplay.setCellFactory(new Callback<ListView<TasksItemController>, ListCell<TasksItemController>>(){
			 
            @Override
            public ListCell<TasksItemController> call(ListView<TasksItemController> p) {
                 
                ListCell<TasksItemController> cell = new ListCell<TasksItemController>(){
 
                    @Override
                    protected void updateItem(TasksItemController t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getTaskName() + ":" + t.getTaskTime());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}

	public void controlToList() {
		int count = 0;
		tasksDisplay.requestFocus();
		tasksDisplay.scrollTo(count);
		tasksDisplay.getFocusModel().focus(count);
		tasksDisplay.getSelectionModel().select(count);
	}

	public void clearTask() {
		items.clear();
	}
	
	public int getSize(){
		return items.size();
	}
	
	

}
