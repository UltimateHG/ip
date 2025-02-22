package uhg.uhgbot.command;

import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;

public class ListCommand implements Command {
    @Override
    public String execute(Object... args) throws UhgBotException {
        if (args.length < 2 || !(args[0] instanceof TaskList) || !(args[1] instanceof Storage)) {
            throw new UhgBotException("Invalid arguments for ListCommand");
        }
        
        TaskList tasks = (TaskList) args[0];
        return tasks.isEmpty() 
            ? "No tasks in the list!"
            : "Here are the tasks in your list:\n" + tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}