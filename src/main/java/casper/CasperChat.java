package casper;


import java.util.Objects;
import java.util.Scanner;

import casper.components.Processor;
import casper.components.Storage;
import casper.components.TaskList;
import casper.components.Ui;
import casper.exceptions.CorruptedSavedTasksException;
import casper.exceptions.CustomIOException;
import casper.exceptions.ExceptionWithSolution;


/**
 * The main class for the CasperChat application, which allows users to manage tasks via command-line input.
 * It handles reading saved tasks from a file, processing user input, and displaying appropriate messages.
 */
public class CasperChat {

    /**
     * The file path to the saved tasks file.
     */
    public static final String FILE_PATH = "src/main/java/data/casper.txt";

    /**
     * The entry point for the CasperChat application.
     * Initializes the task list, loads saved tasks from the file, and enters the main input processing loop.
     * The application runs until the user inputs the "bye" command.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        try {
            Storage.addSavedTasksToTaskList(FILE_PATH, taskList);
        } catch (CustomIOException | CorruptedSavedTasksException e) {
            Ui.printError(e);
        }

        Ui.printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine();
            Ui.printLine();

            if (Objects.equals(input, "bye")) {
                Ui.printByeMessage();
                break;
            }

            try {
                Processor.processInput(input, taskList);
            } catch (ExceptionWithSolution | CustomIOException e) {
                Ui.printError(e);
            }

            Ui.printLine();
            Ui.print("");
        }

    }
}
