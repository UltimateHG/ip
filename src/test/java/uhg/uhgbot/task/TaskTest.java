package uhg.uhgbot.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {
    /**
     * Tests Task creation and initial state
     */
    @Test
    public void testTaskCreation() {
        Task task = new Task("test task");
        assertEquals("test task", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(" ", task.getStatusIcon());
    }

    /**
     * Tests marking task as done
     */
    @Test
    public void testMarkDone() {
        Task task = new Task("test task");
        task.markAsDone();
        assertTrue(task.isDone());
        assertEquals("X", task.getStatusIcon());
    }

    /**
     * Tests marking task as undone
     */
    @Test
    public void testMarkUndone() {
        Task task = new Task("test task");
        task.markAsDone();
        task.markAsUndone();
        assertFalse(task.isDone());
        assertEquals(" ", task.getStatusIcon());
    }
}