package uhg.UhgBot.command;

import uhg.UhgBot.task.Event;

public class EventCommand extends AddCommand {
    /**
     * Creates a new EventCommand object. Accepts an Event object.
     * @param event Event object to be added.
     */
    public EventCommand(Event event) {
        super(event);
    }
}