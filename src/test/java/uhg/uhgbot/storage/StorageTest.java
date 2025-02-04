package uhg.uhgbot.storage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import uhg.uhgbot.task.*;

public class StorageTest {
    private static final String TEST_FILE = "./test-data/test-storage.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        storage = new Storage(TEST_FILE);
        // Create test directory if it doesn't exist
        Files.createDirectories(Paths.get("./test-data"));
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up test file after each test
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    /**
     * Tests loading from empty/non-existent file
     */
    @Test
    public void testLoadEmptyFile() throws IOException {
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    /**
     * Tests saving and loading tasks
     */
    @Test
    public void testSaveAndLoad() throws Exception {
        // Create test tasks
        Todo todo = new Todo("test todo");
        Deadline deadline = new Deadline("test deadline", "2024-03-15 1400");
        Event event = new Event("test event", "2024-03-15 1400", "2024-03-15 1600");
        
        // Save tasks
        List<Task> original = List.of(todo, deadline, event);
        storage.save(original);

        // Load tasks back
        List<Task> loaded = storage.load();
        
        assertEquals(original.size(), loaded.size());
        assertEquals(todo.getDescription(), loaded.get(0).getDescription());
        assertEquals(deadline.getDescription(), loaded.get(1).getDescription());
        assertEquals(event.getDescription(), loaded.get(2).getDescription());
    }

    /**
     * Tests saving and loading tasks with done status
     */
    @Test
    public void testSaveAndLoadWithStatus() throws Exception {
        Todo todo = new Todo("test todo");
        todo.markAsDone();
        
        storage.save(List.of(todo));
        List<Task> loaded = storage.load();
        
        assertTrue(loaded.get(0).isDone());
    }

    /**
     * Tests handling of invalid file content
     */
    @Test
    public void testInvalidFileContent() throws IOException {
        Files.write(Paths.get(TEST_FILE), "invalid content".getBytes());
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }
}