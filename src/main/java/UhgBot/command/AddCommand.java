package UhgBot.command;

import java.io.IOException;

import UhgBot.storage.Storage;
import UhgBot.task.Task;
import UhgBot.tasklist.TaskList;

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
    public String execute(TaskList tasks, Storage storage) throws IOException {
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