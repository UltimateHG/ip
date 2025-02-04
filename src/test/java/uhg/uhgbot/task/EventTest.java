package uhg.uhgbot.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uhg.uhgbot.common.UhgBotException;
import java.time.LocalDateTime;

public class EventTest {
    /**
     * Tests valid event creation
     */
    @Test
    public void testValidEvent() throws UhgBotException {
        Event event = new Event("test event", "2024-03-15 1400", "2024-03-15 1600");
        assertEquals("test event", event.getDescription());
        assertEquals(LocalDateTime.parse("2024-03-15T14:00"), event.getStart());
        assertEquals(LocalDateTime.parse("2024-03-15T16:00"), event.getEnd());
    }

    /**
     * Tests invalid date format handling
     */
    @Test
    public void testInvalidDateFormat() {
        assertThrows(UhgBotException.class, () -> 
            new Event("test", "invalid", "invalid"));
    }

    /**
     * Tests end time before start time handling
     */
    @Test
    public void testInvalidTimeOrder() {
        assertThrows(UhgBotException.class, () -> 
            new Event("test", "2024-03-15 1600", "2024-03-15 1400"));
    }

    /**
     * Tests event string representation
     */
    @Test
    public void testEventToString() throws UhgBotException {
        Event event = new Event("meeting", "2024-03-15 1400", "2024-03-15 1600");
        String str = event.toString();
        assertTrue(str.contains("[E]"));
        assertTrue(str.contains("meeting"));
        assertTrue(str.contains("Mar 15 2024, 2:00PM"));
        assertTrue(str.contains("Mar 15 2024, 4:00PM"));
    }
}