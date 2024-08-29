import exceptions.CustomIOException;
import tasks.Task;
import exceptions.ExceptionWithSolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static utils.FileUtils.addSavedTasksToTaskArray;
import static utils.PrintUtils.print;
import static utils.PrintUtils.printCasper;
import static utils.PrintUtils.printLine;

public class Casper {

    private static final String FILE_PATH = "src/main/java/data/casper.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean chat = true;
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            addSavedTasksToTaskArray(FILE_PATH, tasks);
            print("");
        } catch (CustomIOException e) {
            print(e.getMessage());
        }

        printCasper();
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
                print(e.getMessage());
            }

            printLine();
            print("");
        }

    }
}
