package uhg.UhgBot.command;

import uhg.UhgBot.task.Deadline;

public class DeadlineCommand extends AddCommand {
    /**
     * Creates a new DeadlineCommand object. Accepts a Deadline object.
     * @param deadline Deadline object to be added.
     */
    public DeadlineCommand(Deadline deadline) {
        super(deadline);
    }
}