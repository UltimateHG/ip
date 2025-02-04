package uhg.uhgbot.ui;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String message = String.join("\n",
            LINE,
            " Hello! I'm UhgBot",
            " What can I do for you?",
            LINE);
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine().replaceAll("[\n\r]$", "");
    }

    public void showResponse(String response) {
        System.out.println(LINE);
        System.out.println(" " + response);
        System.out.println(LINE);
    }

    public void showError(String message) {
        showResponse("Command error! " + message);
    }
}