package casper;

import casper.exceptions.CorruptedSavedTasksException;
import casper.exceptions.CustomIOException;
import casper.exceptions.ExceptionWithSolution;
import casper.components.Processor;
import casper.components.Storage;
import casper.components.TaskList;
import casper.components.Ui;

import java.util.Objects;
import java.util.Scanner;


public class CasperChat {

    public static final String FILE_PATH = "src/main/java/data/casper.txt";

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

            if (Objects.equals(input, "bye" )) {
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
