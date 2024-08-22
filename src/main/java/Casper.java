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
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    print((i + 1) + ". " + tasks[i]);
                }

            } else {
                String[] splitString = input.split(" ", 2);

                if ((Objects.equals(splitString[0], "mark") || Objects.equals(splitString[0], "unmark")) &&
                        splitString.length == 2 &&
                        splitString[1].matches("-?\\d+") &&
                        Integer.parseInt(splitString[1]) <= currTaskIndex
                ) {
                    int taskIndex = Integer.parseInt(splitString[1]) - 1;

                    if (Objects.equals(splitString[0], "mark")) {
                        tasks[taskIndex].mark();
                        print("Nice! I've marked this task as done:\n" + tasks[taskIndex]);

                    } else {
                        tasks[taskIndex].unmark();
                        print("OK, I've marked this task as not done yet:\n" + tasks[taskIndex]);

                    }

                } else {
                    tasks[currTaskIndex] = new Task(input);
                    currTaskIndex++;
                    print("added: " + input);
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

}
