package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;

public class ByeCommandTest {
    /**
     * Tests bye command execution
     */
    @Test
    public void testExecute() throws Exception {
        ByeCommand cmd = new ByeCommand();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("./test-data/test.txt");
        
        String result = cmd.execute(tasks, storage);
        assertEquals("Bye. Hope to see you again soon!", result);
    }

    /**
     * Tests isExit returns true
     */
    @Test
    public void testIsExit() {
        ByeCommand cmd = new ByeCommand();
        assertTrue(cmd.isExit());
    }
}