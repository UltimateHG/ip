import java.util.Scanner;
import java.util.ArrayList;

public class uhgbot {
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
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(" added: " + newTask);
            }
            
            System.out.println("____________________________________________________________");
        }
        
        scanner.close();
    }
}