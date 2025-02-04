package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.common.UhgBotException;

public class UnmarkCommandTest {
    private TaskList tasks;
    private Storage storage;
    private Task task;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new Storage("./test-data/test.txt");
        task = new Task("test task");
        task.markAsDone();
        tasks.add(task);
    }

    /**
     * Tests unmarking a task with valid index
     */
    @Test
    public void testValidUnmark() throws Exception {
        UnmarkCommand cmd = new UnmarkCommand(1);
        String result = cmd.execute(tasks, storage);
        
        assertFalse(task.isDone());
        assertTrue(result.contains("OK, I've marked this task as not done yet"));
    }

    /**
     * Tests unmarking a task with invalid index
     */
    @Test
    public void testInvalidUnmark() {
        UnmarkCommand cmd = new UnmarkCommand(2);
        assertThrows(UhgBotException.class, () -> cmd.execute(tasks, storage));
    }

    /**
     * Tests isExit returns false
     */
    @Test
    public void testIsExit() {
        UnmarkCommand cmd = new UnmarkCommand(1);
        assertFalse(cmd.isExit());
    }
}