package uhg.uhgbot.parser;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uhg.uhgbot.command.*;
import uhg.uhgbot.common.UhgBotException;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    /**
     * Tests empty input handling
     */
    @Test
    public void testEmptyInput() {
        assertThrows(UhgBotException.class, () -> parser.parse(""));
        assertThrows(UhgBotException.class, () -> parser.parse("   "));
    }

    /**
     * Tests invalid command handling
     */
    @Test
    public void testInvalidCommand() {
        assertThrows(UhgBotException.class, () -> parser.parse("invalid"));
    }

    /**
     * Tests bye command parsing
     */
    @Test
    public void testByeCommand() throws UhgBotException {
        Command cmd = parser.parse("bye");
        assertTrue(cmd instanceof ByeCommand);
        assertTrue(cmd.isExit());
    }

    /**
     * Tests todo command parsing
     */
    @Test
    public void testTodoCommand() throws UhgBotException {
        Command cmd = parser.parse("todo read book");
        assertTrue(cmd instanceof TodoCommand);
        assertFalse(cmd.isExit());
    }

    /**
     * Tests invalid todo command
     */
    @Test
    public void testInvalidTodoCommand() {
        assertThrows(UhgBotException.class, () -> parser.parse("todo"));
        assertThrows(UhgBotException.class, () -> parser.parse("todo  "));
    }

    /**
     * Tests deadline command parsing
     */
    @Test
    public void testDeadlineCommand() throws UhgBotException {
        Command cmd = parser.parse("deadline return book /by 2024-03-15 1400");
        assertTrue(cmd instanceof DeadlineCommand);
    }

    /**
     * Tests invalid deadline command
     */
    @Test
    public void testInvalidDeadlineCommand() {
        assertThrows(UhgBotException.class, () -> parser.parse("deadline"));
        assertThrows(UhgBotException.class, () -> parser.parse("deadline /by"));
        assertThrows(UhgBotException.class, () -> 
            parser.parse("deadline return book /by invalid-date"));
    }
}