package uhg.uhgbot.command;

import java.io.IOException;

import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.tasklist.TaskList;

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
    public String execute(Object... args) throws UhgBotException, IOException {
        if (args.length < 2 || !(args[0] instanceof TaskList) || !(args[1] instanceof Storage)) {
            throw new UhgBotException("Invalid arguments for DeleteCommand");
        }
        
        TaskList tasks = (TaskList) args[0];
        Storage storage = (Storage) args[1];
        
        Task removed = tasks.remove(index - 1);
        storage.save(tasks.getTaskList());
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
            removed.toString(), tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}