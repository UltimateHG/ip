package uhg.UhgBot.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object. Accepts a description as input.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon of task (X if done, empty if not done)
     *
     * @return "X" if task is done or " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** 
     * Sets task as done (isDone = true) 
     */
    public void markAsDone() {
        isDone = true;
    }
    
    /** 
     * Sets task as undone (isDone = false) 
     */
    public void markAsUndone() {
        isDone = false;
    }

    /** 
     * Returns Task isDone 
     * 
     * @return boolean isDone of the Task object
     */
    public boolean isDone() {
        return isDone;
    }

    /** 
     * Returns Task description 
     * 
     * @return String description of the Task object
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}