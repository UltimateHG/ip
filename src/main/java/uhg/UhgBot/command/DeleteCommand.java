package uhg.UhgBot.command;

import java.io.IOException;

import uhg.UhgBot.common.UhgBotException;
import uhg.UhgBot.storage.Storage;
import uhg.UhgBot.task.Task;
import uhg.UhgBot.tasklist.TaskList;

public class DeleteCommand implements Command {
    private final int index;

    /**
     * Creates a new DeleteCommand object. Accepts the index of the task to be deleted.
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws UhgBotException, IOException {
        Task removed;
        removed = tasks.remove(index - 1);
        storage.save(tasks.getTaskList());
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
            removed.toString(), tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}