package UhgBot.task;

public class Todo extends Task {
    /**
     * Creates a new Todo object. Accepts a description as input.
     * 
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}