package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Todo;
import uhg.uhgbot.common.UhgBotException;
import java.io.IOException;

public class AddCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new Storage("./test-data/test.txt");
    }

    /**
     * Tests adding a task through TodoCommand with valid arguments
     */
    @Test
    public void testValidAddTodo() throws Exception {
        Todo todo = new Todo("test todo");
        TodoCommand cmd = new TodoCommand(todo);
        
        String result = cmd.execute(tasks, storage);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        assertTrue(result.contains("Got it. I've added this task"));
        assertTrue(result.contains("test todo"));
    }

    /**
     * Tests adding a task with invalid arguments
     */
    @Test
    public void testInvalidArguments() {
        Todo todo = new Todo("test todo");
        TodoCommand cmd = new TodoCommand(todo);
        
        assertThrows(UhgBotException.class, () -> cmd.execute());
        assertThrows(UhgBotException.class, () -> cmd.execute("invalid"));
        assertThrows(UhgBotException.class, () -> cmd.execute(tasks));
    }

    /**
     * Tests isExit returns false
     */
    @Test
    public void testIsExit() {
        TodoCommand cmd = new TodoCommand(new Todo("test"));
        assertFalse(cmd.isExit());
    }
}