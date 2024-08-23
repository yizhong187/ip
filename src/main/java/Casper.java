import exceptions.ExceptionWithSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Casper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean chat = true;
        ArrayList<Task> tasks = new ArrayList<>();

        printLine();
        print("Hello! I'm Casper!\nWhat can I do for you?");
        printLine();
        print("");

        while (true) {
            String input = scanner.nextLine();
            printLine();

            if (Objects.equals(input, "bye" )) {
                print("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            try {
                print(CommandProcessor.processInput(input, tasks));
            } catch (ExceptionWithSolution e) {
                print(e.getLocalizedMessage());
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
