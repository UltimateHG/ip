package uhg.UhgBot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import uhg.UhgBot.common.UhgBotException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    /**
     * Creates a new Event object. Accepts a description, start date string and end date string as input.
     * 
     * @param description The description of the event.
     * @param start The start date of the event in the format "yyyy-MM-dd HHmm".
     * @param end The end date of the event in the format "yyyy-MM-dd HHmm".
     * @throws UhgBotException If the date strings are not in the correct format or if the end date is before the start date.
     */
    public Event(String description, String start, String end) throws UhgBotException {
        super(description);
        try {
            this.start = LocalDateTime.parse(start, INPUT_FORMAT);
            this.end = LocalDateTime.parse(end, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UhgBotException("Date must be in format: yyyy-MM-dd HHmm");
        }
        if (this.end.isBefore(this.start)) {
            throw new UhgBotException("End time cannot be before start time");
        }
    }

    /** 
     * Returns Event start time as LocalDateTime .
     * 
     * @return LocalDateTime start of the Event object.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /** 
     * Returns Event end time as LocalDateTime.
     * 
     * @return LocalDateTime end of the Event object.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) 
            + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }
}