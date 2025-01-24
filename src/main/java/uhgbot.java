import java.util.Scanner;
import java.util.ArrayList;

public class uhgbot {
    public static Task createTodo(String input) throws uhgbotException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new uhgbotException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    public static Task createDeadline(String input) throws uhgbotException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length != 2 || parts[0].trim().isEmpty()) {
            throw new uhgbotException("Invalid deadline format. Use: deadline <description> /by <deadline>");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    public static Task createEvent(String input) throws uhgbotException {
        String[] parts = input.substring(6).split(" /from ");
        if (parts.length != 2 || parts[0].trim().isEmpty()) {
            throw new uhgbotException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new uhgbotException("Invalid event format. Missing /to timing");
        }
        return new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
    }
    
    public static void main(String[] args) {
        String name = "uhgbot";
        String welcomeMsg = "____________________________________________________________\n" +
                          " Hello! I'm " + name + "\n" +
                          " What can I do for you?\n" +
                          "____________________________________________________________";
        
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(welcomeMsg);
        
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        
        while (isRunning) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            
            try {
                if (input.trim().isEmpty()) {
                    throw new uhgbotException("Command cannot be empty!");
                }

                if (input.toLowerCase().equals("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    isRunning = false;
                } else if (input.toLowerCase().equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println(" No tasks in the list!");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                } else if (input.toLowerCase().startsWith("mark ")) {
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
                    Task task = createTodo(input);
                    tasks.add(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("deadline ")) {
                    Task task = createDeadline(input);
                    tasks.add(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("event ")) {
                    Task task = createEvent(input);
                    tasks.add(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("delete ")) {
                    try {
                        int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskNum >= 0 && taskNum < tasks.size()) {
                            Task removedTask = tasks.remove(taskNum);
                            System.out.println(" Noted. I've removed this task:");
                            System.out.println("   " + removedTask);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        } else {
                            throw new uhgbotException("Invalid task number: " + (taskNum + 1));
                        }
                    } catch (NumberFormatException e) {
                        throw new uhgbotException("Please provide a valid task number.");
                    }
                } else {
                    throw new uhgbotException("Invalid command: " + input);
                }
            } catch (uhgbotException e) {
                System.out.println(" Command error! " + e.getMessage());
            }
            
            System.out.println("____________________________________________________________");
        }
        
        scanner.close();
    }
}