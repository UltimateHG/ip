package uhg.uhgbot.command;

import java.io.IOException;

import uhg.uhgbot.common.UhgBotException;

public interface Command {
    /**
     * Executes the command with variable arguments.
     * First arg must be TaskList, second must be Storage.
     * 
     * @param args TaskList and Storage must be first two arguments
     * @return Command response string
     * @throws UhgBotException
     * @throws IOException
     */
    String execute(Object... args) throws UhgBotException, IOException;
    /**
     * Returns true if the command is an exit command.
     * @return True if the command is an exit command.
     */
    boolean isExit();
}