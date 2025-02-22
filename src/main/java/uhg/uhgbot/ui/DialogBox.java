package uhg.uhgbot.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DialogBox extends HBox {
    private TextFlow dialog;

    /**
     * Constructor for DialogBox object.
     */
    private DialogBox(String text, Pos position) {
        dialog = new TextFlow(new Text(text));
        dialog.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 10;");
        
        this.setAlignment(position);
        this.getChildren().add(dialog);
        this.setStyle("-fx-padding: 10;");
    }

    /**
     * Returns a user DialogBox object with the given text (right side).
     * @param text
     * @return DialogBox object
     */
    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, Pos.CENTER_RIGHT);
    }

    /**
     * Returns a bot DialogBox object with the given text (left side).
     * @param text
     * @return DialogBox object
     */
    public static DialogBox getBotDialog(String text) {
        return new DialogBox(text, Pos.CENTER_LEFT);
    }
}