package uhg.uhgbot.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TodoTest {
    /**
     * Tests todo creation with description
     */
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("test todo");
        assertEquals("test todo", todo.getDescription());
        assertFalse(todo.isDone());
    }

    /**
     * Tests todo string representation
     */
    @Test
    public void testTodoToString() {
        Todo todo = new Todo("test todo");
        String str = todo.toString();
        assertTrue(str.contains("[T]"));
        assertTrue(str.contains("[ ]"));
        assertTrue(str.contains("test todo"));
    }

    /**
     * Tests marking todo as done
     */
    @Test
    public void testMarkDone() {
        Todo todo = new Todo("test todo");
        todo.markAsDone();
        assertTrue(todo.isDone());
        assertTrue(todo.toString().contains("[X]"));
    }

    /**
     * Tests marking todo as undone
     */
    @Test
    public void testMarkUndone() {
        Todo todo = new Todo("test todo");
        todo.markAsDone();
        todo.markAsUndone();
        assertFalse(todo.isDone());
        assertTrue(todo.toString().contains("[ ]"));
    }
}