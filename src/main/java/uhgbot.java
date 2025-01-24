import java.util.Scanner;
import java.util.ArrayList;

public class uhgbot {
    public static void main(String[] args) {
        String name = "uhgbot";
        String welcomeMsg = "____________________________________________________________\n" +
                          " Hello! I'm " + name + "\n" +
                          " What can I do for you?\n" +
                          "____________________________________________________________";
        
        ArrayList<String> tasks = new ArrayList<>();
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
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                }
            } else {
                tasks.add(input);
                System.out.println(" added: " + input);
            }
            
            System.out.println("____________________________________________________________");
        }
        
        scanner.close();
    }
}