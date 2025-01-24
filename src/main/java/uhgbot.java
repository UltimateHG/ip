import java.util.Scanner;

public class uhgbot {
    public static void main(String[] args) {
        String name = "uhgbot";
        String welcomeMsg = "____________________________________________________________\n" +
                          " Hello! I'm " + name + "\n" +
                          " What can I do for you?\n" +
                          "____________________________________________________________";
        
        System.out.println(welcomeMsg);
        
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        
        while (isRunning) {
            String input = scanner.nextLine();
            
            System.out.println("____________________________________________________________");
            
            if (input.toLowerCase().equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                isRunning = false;
            } else {
                System.out.println(" " + input);
            }
            
            System.out.println("____________________________________________________________");
        }
        
        scanner.close();
    }
}