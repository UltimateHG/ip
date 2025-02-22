package uhg.uhgbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uhg.uhgbot.command.Command;
import uhg.uhgbot.parser.Parser;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.tasklist.TaskList;
import uhg.uhgbot.ui.MainWindow;

public class UhgBot extends Application {
    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;
    private final String DATAPATH = "./data/uhgbot.txt";
    private final String FXMLPATH = "/view/MainWindow.fxml";

    /**
     * Constructor for UhgBot.
     */
    public UhgBot() {
        storage = new Storage(DATAPATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
            throw new RuntimeException("Error loading data: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(UhgBot.class.getResource(FXMLPATH));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("UhgBot");
        stage.setScene(scene);
        
        MainWindow controller = fxmlLoader.getController();
        controller.setBot(this);
        
        stage.show();
    }

    /**
     * Gets response for a single command.
     * 
     * @param fullCommand The command to process
     * @return The response string
     * @throws Exception if command processing fails
     */
    public String getResponse(String fullCommand) throws Exception {
        Command c = parser.parse(fullCommand);
        return c.execute(tasks, storage);
    }
}