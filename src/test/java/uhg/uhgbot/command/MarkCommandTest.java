package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.common.UhgBotException;

public class MarkCommandTest {
    private TaskList tasks;
    private Storage storage;
    private Task task;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new Storage("./test-data/test.txt");
        task = new Task("test task");
        tasks.add(task);
    }

    /**
     * Tests marking a task as done with valid index
     */
    @Test
    public void testValidMark() throws Exception {
        MarkCommand cmd = new MarkCommand(1);
        String result = cmd.execute(tasks, storage);
        
        assertTrue(task.isDone());
        assertTrue(result.contains("Nice! I've marked this task as done"));
    }

    /**
     * Tests marking a task with invalid index
     */
    @Test
    public void testInvalidMark() {
        MarkCommand cmd = new MarkCommand(2);
        assertThrows(UhgBotException.class, () -> cmd.execute(tasks, storage));
    }

    /**
     * Tests isExit returns false
     */
    @Test
    public void testIsExit() {
        MarkCommand cmd = new MarkCommand(1);
        assertFalse(cmd.isExit());
    }
}