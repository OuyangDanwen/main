package main.java.data;

/**
 * This enum categorizes the status of task into 4 types below.
 * @author Ouyang Danwen
 *
 */
public enum TaskStatus {
	OVERDUE("overdue"), UPCOMING("upcoming"), FLOATING("floating"), COMPLETED("completed");

	private final String type;

	/**
	 * @param type
	 */
	TaskStatus(String type) {
		assert type != null;
		this.type = type;
	}

	/**
	 * @return the task status in string
	 */
	public String getType() {
		return type;
	}
}
