package main.java.data;

public class Task {
	
	private String task;
	private String time;
	private String type;
	private String priority;
	private transient int taskID;
	private transient boolean display;
	
	public Task(String task, String time, String priority, String type) {
		this.task = task;
		this.time = time;
		this.priority = priority;
		this.type = type;
		this.taskID = -1;
		this.display = false;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public int getTaskID() {
		return taskID;
	}
	
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	public boolean getShowToUserDelete() {
		return display;
	}
	
	public void setShowToUserDelete(boolean display) {
		this.display = display;
	}
	
}

