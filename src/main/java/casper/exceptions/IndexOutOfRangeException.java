package casper.exceptions;

/**
 * Thrown to indicate that a task index is out of range.
 * This exception provides a suggested solution for providing a valid index.
 */
public class IndexOutOfRangeException extends ExceptionWithSolution{
    public IndexOutOfRangeException() {
        super("Task index out of range.",
                "Provide a valid index corresponding to a task. "
                        + "You may use the `list` command to see all casper.tasks.");

    }
}
