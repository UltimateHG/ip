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
            assert storage != null : "Storage should be initialized";
            assert parser != null : "Parser should be initialized";
            assert tasks != null : "TaskList should be initialized";
        } catch (Exception e) {
            tasks = new TaskList();
            throw new RuntimeException("Error loading data: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        assert stage != null : "Stage cannot be null";
        
        FXMLLoader fxmlLoader = new FXMLLoader(UhgBot.class.getResource(FXMLPATH));
        assert fxmlLoader.getLocation() != null : "FXML file not found: " + FXMLPATH;
        
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("UhgBot");
        stage.setScene(scene);
        
        MainWindow controller = fxmlLoader.getController();
        assert controller != null : "FXML controller not initialized";
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
        assert fullCommand != null : "Command cannot be null";
        assert !fullCommand.trim().isEmpty() : "Command cannot be empty";
        
        Command c = parser.parse(fullCommand);
        assert c != null : "Parser returned null command";
        
        String response = c.execute(tasks, storage);
        assert response != null : "Command execution returned null response";
        
        return response;
    }
}