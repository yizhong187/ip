import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Casper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean chat = true;
        Task[] tasks = new Task[100];
        int currTaskIndex = 0;

        printLine();
        print("Hello! I'm Casper\nWhat can I do for you?");
        printLine();
        print("");

        while (chat) {
            String input = scanner.nextLine();
            printLine();

            if (Objects.equals(input, "bye" )) {
                chat = false;
                print("Bye. Hope to see you again soon!");

            } else if (Objects.equals(input, "list" )) {

                if (currTaskIndex == 0) {
                    print("No tasks added.");
                }

                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    print((i + 1) + ". " + tasks[i]);
                }

            } else {
                String[] parts = input.split(" ", 2);
                String keyword = parts[0];
                String message = parts.length > 1 ? parts[1] : null;

                if (message == null) {
                    print("Invalid format! Correct format: [keyword] [message]");
                    printLine();
                    print("");
                    continue;
                }

                switch (keyword) {
                    case "mark", "unmark" -> {
                        if (message.matches("-?\\d+" ) && Integer.parseInt(message) <= currTaskIndex) {
                            int taskIndex = Integer.parseInt(message) - 1;

                            if (Objects.equals(keyword, "mark" )) {
                                tasks[taskIndex].mark();
                                print("Nice! I've marked this task as done:\n" + tasks[taskIndex]);

                            } else {
                                tasks[taskIndex].unmark();
                                print("OK, I've marked this task as not done yet:\n" + tasks[taskIndex]);
                            }
                        }
                    }

                    case "todo" -> {
                        tasks[currTaskIndex] = new ToDo(message);
                        currTaskIndex++;
                        printAddedTask(tasks[currTaskIndex - 1].toString(), currTaskIndex);
                    }

                    case "deadline" -> {
                        String[] deadlineParts = message.split(" /by ", 2);
                        String description = deadlineParts[0];
                        String by = deadlineParts.length > 1 ? deadlineParts[1] : "";
                        tasks[currTaskIndex] = new Deadline(description, by);
                        currTaskIndex++;
                        printAddedTask(tasks[currTaskIndex - 1].toString(), currTaskIndex);
                    }

                    case "event" -> {
                        String[] eventParts = message.split(" /from ", 2);
                        String description = eventParts[0];

                        String[] eventPartsTwo = eventParts.length > 1 ? eventParts[1].split(" /to ", 2) : new String[]{"", ""};
                        String from = eventPartsTwo[0];
                        String to = eventPartsTwo.length > 1 ? eventPartsTwo[1] : "";

                        tasks[currTaskIndex] = new Event(description, from, to);
                        currTaskIndex++;
                        printAddedTask(tasks[currTaskIndex - 1].toString(), currTaskIndex);
                    }

                    default ->
                            print("Unknown command: " + keyword);
                }
            }

            printLine();
            print("");
        }

    }

    private static void print(String text) {
        System.out.println(text);
    }

    private static void printLine() {
        print("____________________________________________________________");
    }

    private static void printAddedTask(String newTask, int taskCount) {
        print("Got it. I've added this task:\n" +
                newTask + "\n" +
                "Now you have " + taskCount + " tasks in the list.");
    }
}
