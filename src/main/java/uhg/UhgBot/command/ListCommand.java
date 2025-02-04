package uhg.UhgBot.command;

import uhg.UhgBot.storage.Storage;
import uhg.UhgBot.tasklist.TaskList;

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