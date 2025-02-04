package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Task;

public class ListCommandTest {
    /**
     * Tests empty list message
     */
    @Test
    public void testEmptyList() throws Exception {
        ListCommand cmd = new ListCommand();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("./test-data/test.txt");
        
        String result = cmd.execute(tasks, storage);
        assertEquals("No tasks in the list!", result);
    }

    /**
     * Tests list with tasks
     */
    @Test
    public void testNonEmptyList() throws Exception {
        ListCommand cmd = new ListCommand();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("./test-data/test.txt");
        
        tasks.add(new Task("test task"));
        String result = cmd.execute(tasks, storage);
        assertTrue(result.contains("test task"));
        assertTrue(result.contains("Here are the tasks in your list"));
    }

    /**
     * Tests isExit returns false
     */
    @Test
    public void testIsExit() {
        ListCommand cmd = new ListCommand();
        assertFalse(cmd.isExit());
    }
}