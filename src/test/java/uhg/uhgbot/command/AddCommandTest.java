package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Todo;
import java.io.IOException;

public class AddCommandTest {
    /**
     * Tests adding a task through TodoCommand
     */
    @Test
    public void testAddTodo() throws IOException {
        Todo todo = new Todo("test todo");
        TodoCommand cmd = new TodoCommand(todo);
        TaskList tasks = new TaskList();
        Storage storage = new Storage("./test-data/test.txt");
        
        String result = cmd.execute(tasks, storage);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        assertTrue(result.contains("Got it. I've added this task"));
        assertTrue(result.contains("test todo"));
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