package uhg.uhgbot.tasklist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.common.UhgBotException;
import java.util.ArrayList;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests adding tasks to list
     */
    @Test
    public void testAddTask() {
        Task task = new Task("test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertFalse(taskList.isEmpty());
    }

    /**
     * Tests removing task from valid index
     */
    @Test
    public void testValidRemove() throws UhgBotException {
        Task task = new Task("test task");
        taskList.add(task);
        Task removed = taskList.remove(0);
        assertEquals(task, removed);
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests removing task from invalid index throws exception
     */
    @Test
    public void testInvalidRemove() {
        assertThrows(UhgBotException.class, () -> 
            taskList.remove(0));
        assertThrows(UhgBotException.class, () -> 
            taskList.remove(-1));
    }

    /**
     * Tests getting task list copy
     */
    @Test
    public void testGetTaskList() {
        Task task = new Task("test task");
        taskList.add(task);
        ArrayList<Task> copy = new ArrayList<>(taskList.getTaskList());
        assertEquals(1, copy.size());
        assertEquals(task, copy.get(0));
    }

    /**
     * Tests getting task by valid index
     */
    @Test
    public void testGetValidTask() throws UhgBotException {
        Task task = new Task("test task");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    /**
     * Tests getting task by invalid index throws exception
     */
    @Test
    public void testGetInvalidTask() {
        assertThrows(UhgBotException.class, () -> taskList.get(0));
        assertThrows(UhgBotException.class, () -> taskList.get(-1));
    }

    /**
     * Tests string representation of task list
     */
    @Test
    public void testToString() {
        Task task1 = new Task("first task");
        Task task2 = new Task("second task");
        taskList.add(task1);
        taskList.add(task2);
        
        String result = taskList.toString();
        assertTrue(result.contains("1." + task1.toString()));
        assertTrue(result.contains("2." + task2.toString()));
    }

    /**
     * Tests empty list behavior
     */
    @Test
    public void testEmptyList() {
        assertTrue(taskList.isEmpty());
        assertEquals(0, taskList.size());
        assertEquals("", taskList.toString().trim());
    }
}