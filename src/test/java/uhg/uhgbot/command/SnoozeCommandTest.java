package uhg.uhgbot.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.task.Deadline;
import uhg.uhgbot.task.Event;
import uhg.uhgbot.task.Todo;
import uhg.uhgbot.common.UhgBotException;

public class SnoozeCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws UhgBotException {
        tasks = new TaskList();
        storage = new Storage("./test-data/test.txt");
        tasks.add(new Deadline("test deadline", "2024-03-15 1400"));
        tasks.add(new Event("test event", "2024-03-15 1400", "2024-03-15 1600"));
        tasks.add(new Todo("test todo"));
    }

    /**
     * Tests snoozing a deadline task
     */
    @Test
    public void testSnoozeDeadline() throws Exception {
        SnoozeCommand cmd = new SnoozeCommand(1, "+1d");
        String result = cmd.execute(tasks, storage);
        assertTrue(result.contains("Snoozed task by +1d"));
        assertTrue(result.contains("Mar 16 2024"));
    }

    /**
     * Tests snoozing an event task
     */
    @Test
    public void testSnoozeEvent() throws Exception {
        SnoozeCommand cmd = new SnoozeCommand(2, "+2h");
        String result = cmd.execute(tasks, storage);
        assertTrue(result.contains("Snoozed task by +2h"));
        assertTrue(result.contains("4:00PM"));
        assertTrue(result.contains("6:00PM"));
    }

    /**
     * Tests snoozing a todo task throws exception
     */
    @Test
    public void testSnoozeTodo() {
        SnoozeCommand cmd = new SnoozeCommand(3, "+1d");
        assertThrows(UhgBotException.class, () -> cmd.execute(tasks, storage));
    }

    /**
     * Tests snoozing with invalid duration
     */
    @Test
    public void testInvalidDuration() {
        SnoozeCommand cmd = new SnoozeCommand(1, "1d");  // Missing + prefix
        assertThrows(UhgBotException.class, () -> cmd.execute(tasks, storage));
        
        SnoozeCommand cmd2 = new SnoozeCommand(1, "+1x");  // Invalid unit
        assertThrows(UhgBotException.class, () -> cmd2.execute(tasks, storage));
    }

    /**
     * Tests snoozing with invalid index
     */
    @Test
    public void testInvalidIndex() {
        SnoozeCommand cmd = new SnoozeCommand(999, "+1d");
        assertThrows(UhgBotException.class, () -> cmd.execute(tasks, storage));
    }

    /**
     * Tests isExit returns false
     */
    @Test
    public void testIsExit() {
        SnoozeCommand cmd = new SnoozeCommand(1, "+1d");
        assertFalse(cmd.isExit());
    }
}