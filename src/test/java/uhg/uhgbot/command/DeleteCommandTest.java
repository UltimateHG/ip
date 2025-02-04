package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.common.UhgBotException;

public class DeleteCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new Storage("./test-data/test.txt");
        tasks.add(new Task("test task"));
    }

    /**
     * Tests deleting a task with valid index
     */
    @Test
    public void testValidDelete() throws Exception {
        DeleteCommand cmd = new DeleteCommand(1);
        String result = cmd.execute(tasks, storage);
        assertTrue(tasks.isEmpty());
        assertTrue(result.contains("Noted. I've removed this task"));
    }

    /**
     * Tests deleting a task with invalid index
     */
    @Test
    public void testInvalidDelete() {
        DeleteCommand cmd = new DeleteCommand(2);
        assertThrows(UhgBotException.class, () -> cmd.execute(tasks, storage));
    }

    /**
     * Tests isExit returns false
     */
    @Test
    public void testIsExit() {
        DeleteCommand cmd = new DeleteCommand(1);
        assertFalse(cmd.isExit());
    }
}