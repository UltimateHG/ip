package uhg.uhgbot.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import uhg.uhgbot.common.UhgBotException;
import uhg.uhgbot.storage.Storage;
import uhg.uhgbot.task.Task;
import uhg.uhgbot.task.Deadline;
import uhg.uhgbot.task.Event;
import uhg.uhgbot.tasklist.TaskList;

public class SnoozeCommand implements Command {
    private final int index;
    private final String duration;

    /**
     * Creates a new SnoozeCommand object. Accepts the index of the task to be snoozed and the duration to snooze by.
     * @param index
     * @param duration
     */
    public SnoozeCommand(int index, String duration) {
        this.index = index;
        this.duration = duration;
    }

    @Override
    public String execute(Object... args) throws UhgBotException, IOException {
        if (args.length < 2 || !(args[0] instanceof TaskList) || !(args[1] instanceof Storage)) {
            throw new UhgBotException("Invalid arguments for SnoozeCommand");
        }
        
        TaskList tasks = (TaskList) args[0];
        Storage storage = (Storage) args[1];
        
        Task task = tasks.get(index - 1);
        long amount = parseDuration(duration);
        ChronoUnit unit = parseUnit(duration);
        
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            LocalDateTime newTime = deadline.getBy().plus(amount, unit);
            tasks.replace(index - 1, new Deadline(deadline.getDescription(), 
                formatDateTime(newTime)));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            LocalDateTime newStart = event.getStart().plus(amount, unit);
            LocalDateTime newEnd = event.getEnd().plus(amount, unit);
            tasks.replace(index - 1, new Event(event.getDescription(),
                formatDateTime(newStart), formatDateTime(newEnd)));
        } else {
            throw new UhgBotException("Only Deadline and Event tasks can be snoozed");
        }
        
        storage.save(tasks.getTaskList());
        return String.format("Snoozed task by %s:\n  %s", duration, tasks.get(index - 1));
    }

    private long parseDuration(String duration) throws UhgBotException {
        try {
            return Long.parseLong(duration.substring(1, duration.length() - 1));
        } catch (NumberFormatException e) {
            throw new UhgBotException("Invalid duration format. Use +<number><unit>, e.g. +1d, +2h");
        }
    }

    private ChronoUnit parseUnit(String duration) throws UhgBotException {
        char unit = duration.charAt(duration.length() - 1);
        switch (unit) {
        case 'd': return ChronoUnit.DAYS;
        case 'h': return ChronoUnit.HOURS;
        case 'm': return ChronoUnit.MINUTES;
        default:
            throw new UhgBotException("Invalid time unit. Use d (days), h (hours), or m (minutes)");
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}