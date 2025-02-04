package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Task;

public class FindCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new Storage("./test-data/test.txt");
        tasks.add(new Task("read book"));
        tasks.add(new Task("return pencil"));
    }

    /**
     * Tests finding tasks with matching keyword
     */
    @Test
    public void testFindMatch() throws Exception {
        FindCommand cmd = new FindCommand("book");
        String result = cmd.execute(tasks, storage);
        assertTrue(result.contains("read book"));
        assertFalse(result.contains("return pencil"));
    }

    /**
     * Tests finding tasks with no matches
     */
    @Test
    public void testFindNoMatch() throws Exception {
        FindCommand cmd = new FindCommand("xyz");
        String result = cmd.execute(tasks, storage);
        assertEquals("No matching tasks found.", result);
    }

    /**
     * Tests case insensitive search
     */
    @Test
    public void testFindCaseInsensitive() throws Exception {
        FindCommand cmd = new FindCommand("BOOK");
        String result = cmd.execute(tasks, storage);
        assertTrue(result.contains("read book"));
    }
}