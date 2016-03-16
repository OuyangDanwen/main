package main.java.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.shade.edu.emory.mathcs.backport.java.util.Collections;

import main.java.data.Task;

public class TempStorage {

	private static final double STR_SIMILARITY_THRESHOLD = 0.55;
	private ArrayList<Task> taskList;
	private ArrayList<Task> tempList;
	private Storage storage;
	private Stack< ArrayList<Task> > undoStack; 
	Logger logger = Logger.getLogger("MyLog");  
	FileHandler fileHandler;  

	public TempStorage() throws SecurityException, IOException {
		
		storage = new Storage();
		undoStack =  new Stack< ArrayList<Task> >(); 
		taskList = retrieveListFromFile();
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		fileHandler = new FileHandler("MyLogFile.log");  
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();  
        fileHandler.setFormatter(formatter);  
		logger.info("Log created");
	}

	public void changeDirectory(String path) {
		assert path != null;
		
		storage.changeDirectory(path);
	}
	
	public Boolean renameFile(String name) {
		assert name != null;
		
		Boolean isSuccess = storage.renameFile(name);
		return isSuccess;
	}
	
	public void writeToTemp(Task task) {
		assert task != null;
		
		taskList.add(task);
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		storage.writeToFile(task);
	}
	
	public ArrayList<Task> displayTemp() {
	
		return taskList;
	}
	
	public void editToTemp(Task taskToEdit, Task editedTask) {
		assert taskToEdit != null;
		
		int indexOfTaskToEdit = searchTemp(taskToEdit);
		taskList.set(indexOfTaskToEdit, editedTask);
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		storage.editToFile(indexOfTaskToEdit, editedTask);
	}
	
	public void deleteFromTemp(Task task) {
		assert task != null;
		
		int indexOfTaskToDelete = searchTemp(task);
		taskList.remove(taskList.get(indexOfTaskToDelete));
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		storage.deleteFromFile(indexOfTaskToDelete);
		
	}
	
	public void clearTemp() {
		
		taskList.clear();
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		storage.clearFile();
	}
	
	public int searchTemp(Task task) {
		assert task != null;
		
		for(int i=0; i<taskList.size(); i++) {
			Task thisTask = taskList.get(i);
			if(thisTask.getTask().equals(task.getTask()) && 
					thisTask.getTime().equals(task.getTime()) &&
					thisTask.getPriority().equals(task.getPriority())) {
				return i;
			}
		}
		return -1;
	}
	
//	public ArrayList<Task> searchTemp(Task task) {
//		assert task != null;
//		
//		ArrayList<Task> searchResults = new ArrayList<Task>();
//		
//		for(int i=0; i<taskList.size(); i++) {
//			Task thisTask = taskList.get(i);
//			
//			if(stringCompare(thisTask.getTask(), task.getTask()) ||
//					(!thisTask.getTime().isEmpty() && thisTask.getTime().equals(task.getTime())) ||
//					(!thisTask.getPriority().isEmpty() && thisTask.getPriority().equals(task.getPriority()))) {
//				thisTask.setTaskID(i);
//				searchResults.add(thisTask);
//			}
//		}
//		return searchResults;
//	}
//	
	public void sortByTaskName() {
		
		Collections.sort(taskList, new TaskNameComparator());
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		storage.copyAllToFile((taskList));
	}
	
	public void sortByTime() {
		
		Collections.sort(taskList, new TimeComparator());
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		storage.copyAllToFile((taskList));
	}
	
	public void sortByPriority() {
		
		Collections.sort(taskList, new PriorityComparator());
		tempList = new ArrayList<Task>(taskList);
		undoStack.push(tempList);
		storage.copyAllToFile((taskList));
	}
	
	public void undoPrevious() {
		if(undoStack.size() >= 2) {
			undoStack.pop();
			taskList = new ArrayList<Task>(undoStack.peek());
			storage.copyAllToFile(taskList);
		}
	}
	
	private Boolean stringCompare(String taskInList, String taskToCheck) {
		
		Boolean isSimilar = (StringUtils.getJaroWinklerDistance(taskInList, taskToCheck) >= STR_SIMILARITY_THRESHOLD);
		Boolean isContainExactWord = false;
		
		if(taskInList.contains(taskToCheck) || taskToCheck.contains(taskInList)) {
			isContainExactWord = true;
		}
		return (isSimilar || isContainExactWord);
		
	}
	
	private ArrayList<Task> retrieveListFromFile() {
		ArrayList<Task> list = storage.readFromFile();
		
		return list;
	}
}
