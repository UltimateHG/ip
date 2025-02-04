package UhgBot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import UhgBot.common.UhgBotException;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    /**
     * Creates a new Deadline object. Accepts a description and a date string as input.
     * 
     * @param description The description of the deadline.
     * @param by The date of the deadline in the format "yyyy-MM-dd HHmm".
     * @throws UhgBotException If the date string is not in the correct format.
     */
    public Deadline(String description, String by) throws UhgBotException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UhgBotException("Date must be in format: yyyy-MM-dd HHmm");
        }
    }

    /** 
     * Returns the deadline as LocalDateTime.
     * 
     * @return LocalDateTime by of the Deadline object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}