package uhg.uhgbot.parser;

import uhg.uhgbot.command.ByeCommand;
import uhg.uhgbot.command.Command;
import uhg.uhgbot.command.DeadlineCommand;
import uhg.uhgbot.command.DeleteCommand;
import uhg.uhgbot.command.EventCommand;
import uhg.uhgbot.command.FindCommand;
import uhg.uhgbot.command.ListCommand;
import uhg.uhgbot.command.MarkCommand;
import uhg.uhgbot.command.SnoozeCommand;
import uhg.uhgbot.command.TodoCommand;
import uhg.uhgbot.command.UnmarkCommand;
import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.task.Deadline;
import uhg.uhgbot.task.Event;
import uhg.uhgbot.task.Todo;

public class Parser {
    /**
     * Parses the input string and returns the corresponding Command object.
     * 
     * @param input The input string to be parsed.
     * @return The Command object corresponding to the input string.
     * @throws UhgBotException If the input string is invalid.
     */
    public Command parse(String input) throws UhgBotException {
        if (input.trim().isEmpty()) {
            throw new UhgBotException("Command cannot be empty!");
        }

        String command = input.split(" ")[0].toLowerCase();
        
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return parseTodo(input);
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "delete":
            return parseDelete(input);
        case "mark":
            return parseMark(input);
        case "unmark":
            return parseUnmark(input);
        case "find":
            return parseFind(input);
        case "snooze":
            return parseSnooze(input);
        default:
            throw new UhgBotException("Invalid command: " + input);
        }
    }

    /**
     * Parses the input string and returns a TodoCommand object.
     * 
     * @param input The input string to be parsed.
     * @return The TodoCommand object corresponding to the input string.
     * @throws UhgBotException If the input string is invalid.
     */
    private Command parseTodo(String input) throws UhgBotException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new UhgBotException("The description of a todo cannot be empty.");
        }
        return new TodoCommand(new Todo(description));
    }

    /**
     * Parses the input string and returns a DeadlineCommand object.
     * 
     * @param input The input string to be parsed.
     * @return The DeadlineCommand object corresponding to the input string.
     * @throws UhgBotException If the input string is invalid.
     */
    private Command parseDeadline(String input) throws UhgBotException {
        String[] parts = input.substring(8).trim().split(" /by ");
        if (parts.length != 2 || parts[0].trim().isEmpty()) {
            throw new UhgBotException("Invalid deadline format. Use: deadline <description> /by <deadline>");
        }
        return new DeadlineCommand(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    /**
     * Parses the input string and returns an EventCommand object.
     * 
     * @param input The input string to be parsed.
     * @return The EventCommand object corresponding to the input string.
     * @throws UhgBotException If the input string is invalid.
     */
    private Command parseEvent(String input) throws UhgBotException {
        String[] parts = input.substring(5).trim().split(" /from ");
        if (parts.length != 2 || parts[0].trim().isEmpty()) {
            throw new UhgBotException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new UhgBotException("Invalid event format. Missing /to timing");
        }
        return new EventCommand(new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
    }

    /**
     * Parses the input string and returns a DeleteCommand object.
     * 
     * @param input The input string to be parsed.
     * @return The DeleteCommand object corresponding to the input string.
     * @throws UhgBotException If the input string is invalid.
     */
    private Command parseDelete(String input) throws UhgBotException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new UhgBotException("Please provide a valid task number.");
        }
    }

    /**
     * Parses the input string and returns a MarkCommand object.
     * 
     * @param input The input string to be parsed.
     * @return The MarkCommand object corresponding to the input string.
     * @throws UhgBotException If the input string is invalid.
     */
    private Command parseMark(String input) throws UhgBotException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new UhgBotException("Please provide a valid task number.");
        }
    }

    /**
     * Parses the input string and returns an UnmarkCommand object.
     * 
     * @param input The input string to be parsed.
     * @return The UnmarkCommand object corresponding to the input string.
     * @throws UhgBotException If the input string is invalid.
     */
    private Command parseUnmark(String input) throws UhgBotException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new UhgBotException("Please provide a valid task number.");
        }
    }

    /**
     * Parses an input string and finds tasks containing string.
     * 
     * @param input The input string to search for.
     * @return FindCommand object.
     * @throws UhgBotException if keyword is empty.
     */
    private Command parseFind(String input) throws UhgBotException {
        String keyword = input.substring(4).trim();
        if (keyword.isEmpty()) {
            throw new UhgBotException("Search keyword cannot be empty");
        }
        return new FindCommand(keyword);
    }

    /**
     * Snoozes a task.
     * Correct syntax should be "snooze <task number> <duration>".
     * 
     * @param input Snooze command following syntax.
     * @return SnoozeCommand object.
     * @throws UhgBotException If the input string is invalid or duration is invalid.
     */
    private Command parseSnooze(String input) throws UhgBotException {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new UhgBotException("Invalid snooze format. Use: snooze <task number> <duration>");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            if (!parts[2].matches("\\+\\d+[dhm]")) {
                throw new UhgBotException("Invalid duration format. Use +<number><unit>, e.g. +1d, +2h");
            }
            return new SnoozeCommand(index, parts[2]);
        } catch (NumberFormatException e) {
            throw new UhgBotException("Please provide a valid task number");
        }
    }
}