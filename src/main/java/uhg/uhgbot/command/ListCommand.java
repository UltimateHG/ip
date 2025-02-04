package uhg.uhgbot.command;

import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;

public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.isEmpty() 
            ? "No tasks in the list!"
            : "Here are the tasks in your list:\n" + tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}