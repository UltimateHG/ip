package uhg.UhgBot.command;

import java.io.IOException;

import uhg.UhgBot.common.UhgBotException;
import uhg.UhgBot.storage.Storage;
import uhg.UhgBot.tasklist.TaskList;

public interface Command {
    /**
     * Executes the command and returns the response as a string.
     * @param tasks TaskList object containing Tasks.
     * @param storage Storage object for file I/O and saving Tasks to disk.
     * @return Command response.
     * @throws UhgBotException
     * @throws IOException
     */
    String execute(TaskList tasks, Storage storage) throws UhgBotException, IOException;
    /**
     * Returns true if the command is an exit command.
     * @return True if the command is an exit command.
     */
    boolean isExit();
}