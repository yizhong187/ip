package casper;

import casper.components.Processor;
import casper.components.Storage;
import casper.components.Stringifier;
import casper.components.TaskList;
import casper.exceptions.CorruptedSavedTasksException;
import casper.exceptions.CustomIOException;
import casper.exceptions.ExceptionWithSolution;

public class Casper {

    /**
     * The file path to the saved tasks file.
     */
    public static final String FILE_PATH = "src/main/java/data/casper.txt";

    private TaskList taskList;

    public Casper() {
        this.taskList = new TaskList();
    }

    public String loadSavedTasks() {
        try {
            Storage.addSavedTasksToTaskList(FILE_PATH, taskList);
            return "Loaded saved tasks successfully!";
        } catch (CustomIOException | CorruptedSavedTasksException e) {
            return e.getMessage();
        }
    }

    public String welcomeUser() {
        return Stringifier.getWelcomeMessage();
    }

    public String getResponse(String input) {
        try {
            return Processor.processInput(input, taskList);
        } catch (ExceptionWithSolution | CustomIOException e) {
            return e.getMessage();
        }
    }
}
