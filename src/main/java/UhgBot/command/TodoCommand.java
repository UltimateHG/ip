package UhgBot.command;

import UhgBot.task.Todo;

public class TodoCommand extends AddCommand {
    /**
     * Constructor for TodoCommand. Accepts a Todo object.
     * @param todo Todo object to be added.
     */
    public TodoCommand(Todo todo) {
        super(todo);
    }
}