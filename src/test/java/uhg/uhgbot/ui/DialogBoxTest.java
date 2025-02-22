package uhg.uhgbot.ui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.geometry.Pos;

public class DialogBoxTest {
    /**
     * Tests user dialog creation
     */
    @Test
    public void testUserDialog() {
        DialogBox box = DialogBox.getUserDialog("test message");
        assertEquals(Pos.CENTER_RIGHT, box.getAlignment());
        assertTrue(box.getChildren().size() > 0);
    }

    /**
     * Tests bot dialog creation
     */
    @Test
    public void testBotDialog() {
        DialogBox box = DialogBox.getBotDialog("test message");
        assertEquals(Pos.CENTER_LEFT, box.getAlignment());
        assertTrue(box.getChildren().size() > 0);
    }

    /**
     * Tests dialog styling
     */
    @Test
    public void testDialogStyling() {
        DialogBox box = DialogBox.getBotDialog("test");
        String style = box.getStyle();
        assertTrue(style.contains("padding"));
    }
}