package uhg.UhgBot.command;

import java.io.IOException;

import uhg.UhgBot.common.UhgBotException;
import uhg.UhgBot.storage.Storage;
import uhg.UhgBot.task.Task;
import uhg.UhgBot.tasklist.TaskList;

public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Creates a new UnmarkCommand object. Accepts the index of the task to be marked as not completed.
     * @param index The index of the task to be marked as not completed.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws UhgBotException, IOException {
        Task task = tasks.get(index - 1);
        task.markAsUndone();
        storage.save(tasks.getTaskList());
        return String.format("OK, I've marked this task as not done yet:\n  %s", task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}