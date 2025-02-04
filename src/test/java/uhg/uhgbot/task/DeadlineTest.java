package uhg.uhgbot.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uhg.uhgbot.common.UhgBotException;
import java.time.LocalDateTime;

public class DeadlineTest {
    /**
     * Tests valid deadline creation
     */
    @Test
    public void testValidDeadline() throws UhgBotException {
        Deadline deadline = new Deadline("test deadline", "2024-03-15 1400");
        assertEquals("test deadline", deadline.getDescription());
        assertEquals(LocalDateTime.parse("2024-03-15T14:00"), deadline.getBy());
    }

    /**
     * Tests invalid date format throws exception
     */
    @Test
    public void testInvalidDateFormat() {
        assertThrows(UhgBotException.class, () -> 
            new Deadline("test", "invalid date"));
    }

    /**
     * Tests deadline string representation
     */
    @Test
    public void testDeadlineToString() throws UhgBotException {
        Deadline deadline = new Deadline("test", "2024-03-15 1400");
        assertTrue(deadline.toString().contains("[D]"));
        assertTrue(deadline.toString().contains("Mar 15 2024, 2:00PM"));
    }
}