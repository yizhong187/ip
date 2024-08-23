package exceptions;

public class InvalidIndexException extends ExceptionWithSolution{
    public InvalidIndexException(String command) {
        super("Invalid index.",
                String.format("Please provide a numerical index to `%s`. You may use the `list` command to see all tasks.", command));
    }
}
