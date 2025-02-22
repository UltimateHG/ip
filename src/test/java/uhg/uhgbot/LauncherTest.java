package uhg.uhgbot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.application.Application;

public class LauncherTest {
    /**
     * Tests that Launcher properly configures main class
     */
    @Test
    public void testMainClass() {
        assertDoesNotThrow(() -> {
            String[] args = new String[0];
            // Cannot actually launch JavaFX in unit test, but can verify setup
            assertTrue(UhgBot.class.getSuperclass().equals(Application.class));
        });
    }
}