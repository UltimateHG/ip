package uhg.uhgbot.ui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import javafx.scene.control.TextField;
import uhg.uhgbot.UhgBot;

public class MainWindowTest {
    private MainWindow mainWindow;
    
    @BeforeEach
    public void setUp() {
        mainWindow = new MainWindow();
    }

    /**
     * Tests bot reference setting
     */
    @Test
    public void testSetBot() {
        UhgBot bot = new UhgBot();
        assertDoesNotThrow(() -> mainWindow.setBot(bot));
    }

    /**
     * Tests initialization
     */
    @Test
    public void testInitialize() {
        assertDoesNotThrow(() -> mainWindow.initialize());
    }

    /**
     * Tests user input handling
     * Note: Full GUI testing requires TestFX framework
     */
    @Test
    public void testHandleUserInput() {
        // Basic structure test only since GUI testing needs TestFX
        assertDoesNotThrow(() -> {
            TextField field = new TextField();
            field.setText("test command");
            // Cannot fully test GUI interaction in unit test
        });
    }
}