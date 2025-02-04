package uhg.uhgbot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import java.nio.file.*;

public class UhgBotTest {
    private static final String TEST_DATA_DIR = "./test-data";
    private static final String TEST_FILE = TEST_DATA_DIR + "/test.txt";
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    public void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(TEST_DATA_DIR));
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        originalIn = System.in;
        System.setOut(new PrintStream(outContent));
        Files.createDirectories(Paths.get(TEST_DATA_DIR));
    }

    @AfterEach
    public void tearDown() throws IOException {
        System.setOut(originalOut);
        System.setIn(originalIn);
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(TEST_DATA_DIR));
    }

    /**
     * Tests bot initialization
     */
    @Test
    public void testInitialization() {
        UhgBot bot = new UhgBot();
        String output = outContent.toString();
        assertTrue(output.isEmpty()); // Constructor shouldn't print anything
    }

    /**
     * Tests bot welcome message
     */
    @Test
    public void testWelcomeMessage() {
        simulateInput("bye\n");
        new UhgBot().run();
        String output = outContent.toString();
        assertTrue(output.contains("Hello! I'm UhgBot"));
        assertTrue(output.contains("What can I do for you?"));
    }

    /**
     * Tests basic command execution
     */
    @Test
    public void testBasicCommandExecution() {
        simulateInput("todo test task\nlist\nbye\n");
        new UhgBot().run();
        String output = outContent.toString();
        assertTrue(output.contains("Got it. I've added this task"));
        assertTrue(output.contains("test task"));
        assertTrue(output.contains("Here are the tasks in your list"));
    }

    /**
     * Tests error handling
     */
    @Test
    public void testErrorHandling() {
        simulateInput("invalid command\nbye\n");
        new UhgBot().run();
        String output = outContent.toString();
        assertTrue(output.contains("Command error! Invalid command"));
    }

    private void simulateInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}