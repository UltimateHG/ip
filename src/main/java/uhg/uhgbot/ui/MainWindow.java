package uhg.uhgbot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import uhg.uhgbot.UhgBot;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    
    private UhgBot bot;

    /**
     * Initializes the main components of the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Sets the bot instance reference to itself.
     * @param bot
     */
    public void setBot(UhgBot bot) {
        this.bot = bot;
    }

    /**
     * Shows the welcome message.
     */
    private void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getBotDialog("Hello! I'm UhgBot\nWhat can I do for you?"));
    }

    /**
     * Handles the user input and displays chatbot response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        
        if (!input.isEmpty()) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
            try {
                response = bot.getResponse(input);
            } catch (Exception e) {
                response = "ERROR: " + e.getMessage();
            }
            dialogContainer.getChildren().add(DialogBox.getBotDialog(response));
            userInput.clear();
        }
    }
}