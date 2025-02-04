package uhg.uhgbot.ui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        ui = new Ui();
    }

    /**
     * Tests welcome message display
     */
    @Test
    public void testShowWelcome() {
        ui.showWelcome();
        String output = outContent.toString();
        assertTrue(output.contains("Hello! I'm UhgBot"));
        assertTrue(output.contains("What can I do for you?"));
    }

    /**
     * Tests response message formatting
     */
    @Test
    public void testShowResponse() {
        ui.showResponse("Test message");
        String output = outContent.toString();
        assertTrue(output.contains("Test message"));
        assertTrue(output.contains("____________________________________________________________"));
    }

    /**
     * Tests error message formatting
     */
    @Test
    public void testShowError() {
        ui.showError("Test error");
        String output = outContent.toString();
        assertTrue(output.contains("Command error! Test error"));
    }
}