package uhg.uhgbot.command;

import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;

public class ByeCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}