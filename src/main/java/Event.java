import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) 
            + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }
}