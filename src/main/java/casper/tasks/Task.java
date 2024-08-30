package casper.tasks;

/**
 * Represents a task with a description and a completion status.
 * Provides methods for marking the task as done or undone, and formatting the task information for display and saving.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description and sets its completion status to false.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description A brief description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string in the format: "[status icon] description".
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @return A string suitable for saving to a file.
     */
    public abstract String toSaveString();
}

