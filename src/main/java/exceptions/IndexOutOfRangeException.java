package exceptions;

public class IndexOutOfRangeException extends ExceptionWithSolution{

    private String command;
    public IndexOutOfRangeException() {
        super("Task index out of range.",
                "Provide a valid index corresponding to a task. You may use the `list` command to see all tasks.");

    }
}
