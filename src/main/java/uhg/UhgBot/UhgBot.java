package uhg.UhgBot;

import uhg.UhgBot.command.Command;
import uhg.UhgBot.parser.Parser;
import uhg.UhgBot.storage.Storage;
import uhg.UhgBot.tasklist.TaskList;
import uhg.UhgBot.ui.Ui;

public class UhgBot {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    /**
     * Creates a new UhgBot object. This object is the main class for the UhgBot application.
     */
    public UhgBot() {
        ui = new Ui();
        storage = new Storage("./data/uhgbot.txt");
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading data: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the UhgBot application. This method will display the welcome message and start the main loop of the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                String response = c.execute(tasks, storage);
                ui.showResponse(response);
                isRunning = !c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new UhgBot().run();
    }
}