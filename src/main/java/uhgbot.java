import java.util.Scanner;
import java.util.ArrayList;

public class UhgBot {
    /**
     * Creates a new Todo task from user input.
     *
     * @param input User's input string
     * @return Todo task created from input
     * @throws UhgBotException if description is empty
     */
    public static Task createTodo(String input) throws UhgBotException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new UhgBotException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Creates a new Deadline task from user input.
     *
     * @param input User's input string
     * @return Deadline task created from input
     * @throws UhgBotException if description is empty or invalid (parts.length != 2)
     */
    public static Task createDeadline(String input) throws UhgBotException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length != 2 || parts[0].trim().isEmpty()) {
            throw new UhgBotException("Invalid deadline format. Use: deadline <description> /by <deadline>");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Creates a new Event from user input.
     *
     * @param input User's input string
     * @return Event created from input
     * @throws UhgBotException if description is empty or if invalid format (parts.length != 2 or timeParts.length != 2)
     */
    public static Task createEvent(String input) throws UhgBotException {
        String[] parts = input.substring(6).split(" /from ");
        if (parts.length != 2 || parts[0].trim().isEmpty()) {
            throw new UhgBotException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new UhgBotException("Invalid event format. Missing /to timing");
        }
        return new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
    }
    
    public static void main(String[] args) {
        final String BOT_NAME = "UhgBot";
        final String WELCOME_MESSAGE = String.format(
            "____________________________________________________________\n"
            + " Hello! I'm %s\n"
            + " What can I do for you?\n"
            + "____________________________________________________________",
            BOT_NAME);
        
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(WELCOME_MESSAGE);
        
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        
        // Encompass main functionality in an infinite loop
        while (isRunning) {
            // Get user command input
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            
            // Handle user command
            try {
                // Empty command
                if (input.trim().isEmpty()) {
                    throw new UhgBotException("Command cannot be empty!");
                }

                if (input.toLowerCase().equals("bye")) {
                    // "bye" command, exit loop
                    System.out.println(" Bye. Hope to see you again soon!");
                    isRunning = false;
                } else if (input.toLowerCase().equals("list")) {
                    // "list" command, print task list if exists
                    if (tasks.isEmpty()) {
                        System.out.println(" No tasks in the list!");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                } else if (input.toLowerCase().startsWith("mark ")) {
                    // "mark" command, set task as done
                    try {
                        int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskNum >= 0 && taskNum < tasks.size()) {
                            tasks.get(taskNum).markAsDone();
                            System.out.println(" Nice! I've marked this task as done:");
                            System.out.println("   " + tasks.get(taskNum));
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(" Invalid task number!");
                    }
                } else if (input.toLowerCase().startsWith("unmark ")) {
                    // "unmark" command, set task as not done
                    try {
                        int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskNum >= 0 && taskNum < tasks.size()) {
                            tasks.get(taskNum).markAsUndone();
                            System.out.println(" OK, I've marked this task as not done yet:");
                            System.out.println("   " + tasks.get(taskNum));
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(" Invalid task number!");
                    }
                } else if (input.toLowerCase().startsWith("todo ")) {
                    // "todo" command, create new Todo task
                    Task task = createTodo(input);
                    tasks.add(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("deadline ")) {
                    // "deadline" command, create new Deadline task
                    Task task = createDeadline(input);
                    tasks.add(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("event ")) {
                    // "event" command, create new Event
                    Task task = createEvent(input);
                    tasks.add(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("delete ")) {
                    // "delete" command, remove task from list
                    try {
                        int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskNum >= 0 && taskNum < tasks.size()) {
                            Task removedTask = tasks.remove(taskNum);
                            System.out.println(" Noted. I've removed this task:");
                            System.out.println("   " + removedTask);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        } else {
                            throw new UhgBotException("Invalid task number: " + (taskNum + 1));
                        }
                    } catch (NumberFormatException e) {
                        throw new UhgBotException("Please provide a valid task number.");
                    }
                } else {
                    // Invalid command
                    throw new UhgBotException("Invalid command: " + input);
                }
            } catch (UhgBotException e) {
                // Handle any exceptions thrown
                System.out.println(" Command error! " + e.getMessage());
            }
            
            System.out.println("____________________________________________________________");
        }
        
        scanner.close();
    }
}