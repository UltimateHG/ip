package uhg.uhgbot.command;

import java.io.IOException;

import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.tasklist.TaskList;

public class MarkCommand implements Command {
    private final int index;

    /**
     * Creates a new MarkCommand object. Accepts the index of the task to be marked as completed.
     * @param index The index of the task to be marked as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws UhgBotException, IOException {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        storage.save(tasks.getTaskList());
        return String.format("Nice! I've marked this task as done:\n  %s", task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}