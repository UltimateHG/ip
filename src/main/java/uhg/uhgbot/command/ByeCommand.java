package uhg.uhgbot.command;

import javafx.application.Platform;
import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;

public class ByeCommand implements Command {
    @Override
    public String execute(Object... args) throws UhgBotException {
        if (args.length < 2 || !(args[0] instanceof TaskList) || !(args[1] instanceof Storage)) {
            throw new UhgBotException("Invalid arguments for ByeCommand");
        }
        Platform.exit();
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}