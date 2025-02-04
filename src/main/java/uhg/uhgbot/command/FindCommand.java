package uhg.uhgbot.command;

import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import java.util.List;
import uhg.uhgbot.task.Task;

public class FindCommand implements Command {
    private final String keyword;

    /**
     * Creates a new FindCommand object to search for tasks.
     * 
     * @param keyword The keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> matches = tasks.findByKeyword(keyword);
        if (matches.isEmpty()) {
            return "No matching tasks found.";
        }
        return "Here are the matching tasks in your list:\n" + TaskList.formatTasks(matches);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}