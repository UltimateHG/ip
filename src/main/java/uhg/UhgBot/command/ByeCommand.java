package uhg.UhgBot.command;

import uhg.UhgBot.storage.Storage;
import uhg.UhgBot.tasklist.TaskList;

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