package uhg.uhgbot.command;

import java.io.IOException;

import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.tasklist.TaskList;

public abstract class AddCommand implements Command {
    private final Task task;

    /**
     * Creates a new AddCommand object. Accepts a Task object.
     * 
     * @param task Task object to be added.
     */
    protected AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Object... args) throws UhgBotException, IOException {
        if (args.length < 2 || !(args[0] instanceof TaskList) || !(args[1] instanceof Storage)) {
            throw new UhgBotException("Invalid arguments for AddCommand");
        }
        TaskList tasks = (TaskList) args[0];
        Storage storage = (Storage) args[1];
        
        tasks.add(task);
        storage.save(tasks.getTaskList());
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            task.toString(), tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}