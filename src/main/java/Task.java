public class Task {
    protected String description;
    protected boolean isDone;

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

    /** Sets task as done (isDone = true) */
    public void markAsDone() {
        isDone = true;
    }
    
    /** Sets task as undone (isDone = false) */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}