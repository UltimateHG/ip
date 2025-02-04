package uhg.uhgbot.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.task.Task;

public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a new TaskList object. This object is used to store and manage a list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList object with the specified list of tasks.
     * 
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the TaskList.
     * 
     * @param task The task to add to the TaskList.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList at the specified index.
     * 
     * @param index The index of the task to remove.
     * @return The task that was removed.
     * @throws UhgBotException If the index is out of bounds.
     */
    public Task remove(int index) throws UhgBotException {
        if (index < 0 || index >= tasks.size()) {
            throw new UhgBotException("Invalid task index: " + (index + 1));
        }
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     * 
     * @param index The index of the task to return.
     * @return The task at the specified index.
     * @throws UhgBotException If the index is out of bounds.
     */
    public Task get(int index) throws UhgBotException {
        if (index < 0 || index >= tasks.size()) {
            throw new UhgBotException("Invalid task index: " + (index + 1));
        }
        return tasks.get(index);
    }

    /**
     * Returns true if the TaskList is empty.
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a copy of the list of tasks in the TaskList.
     * @return A copy of the list of tasks in the TaskList as an ArrayList.
     */
    public List<Task> getTaskList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     * 
     * @param keyword The keyword to search for.
     * @return List of tasks containing the keyword.
     */
    public List<Task> findByKeyword(String keyword) {
        return tasks.stream()
            .filter(task -> task.getDescription().toLowerCase()
                .contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    /**
     * Formats a list of tasks with numbering.
     * 
     * @param taskList List of tasks to format.
     * @return Formatted string representation of tasks.
     */
    public static String formatTasks(List<Task> taskList) {
        return IntStream.range(0, taskList.size())
            .mapToObj(i -> String.format(" %d.%s", i + 1, taskList.get(i)))
            .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return IntStream.range(0, tasks.size())
            .mapToObj(i -> String.format(" %d.%s", i + 1, tasks.get(i)))
            .collect(Collectors.joining("\n"));
    }
}