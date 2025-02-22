package uhg.uhgbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UhgBotTest {
    private static final String TEST_DATA_DIR = "./test-data";
    private static final String TEST_FILE = TEST_DATA_DIR + "/test.txt";
    private UhgBot bot;

    @BeforeEach
    public void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DATA_DIR));
        bot = new UhgBot();
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(TEST_DATA_DIR));
    }

    /**
     * Tests bot response to list command
     */
    @Test
    public void testListCommand() throws Exception {
        String response = bot.getResponse("list");
        assertEquals("No tasks in the list!", response);
    }

    /**
     * Tests bot response to todo command
     */
    @Test
    public void testTodoCommand() throws Exception {
        String response = bot.getResponse("todo test task");
        assertTrue(response.contains("Got it. I've added this task"));
        assertTrue(response.contains("test task"));
    }

    /**
     * Tests bot handling of invalid command
     */
    @Test
    public void testInvalidCommand() {
        assertThrows(Exception.class, () -> bot.getResponse("invalid"));
    }

    /**
     * Tests bot response to empty command
     */
    @Test
    public void testEmptyCommand() {
        assertThrows(Exception.class, () -> bot.getResponse(""));
    }

    /**
     * Tests persistence of tasks
     */
    @Test
    public void testTaskPersistence() throws Exception {
        bot.getResponse("todo test task");
        UhgBot newBot = new UhgBot(); // Should load saved task
        String response = newBot.getResponse("list");
        assertTrue(response.contains("test task"));
    }
}