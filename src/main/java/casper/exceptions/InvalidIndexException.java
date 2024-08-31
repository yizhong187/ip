package casper.exceptions;

/**
 * Thrown to indicate that an invalid index was provided.
 * This exception provides a suggested solution for providing a valid numerical index.
 */
public class InvalidIndexException extends ExceptionWithSolution{
    public InvalidIndexException(String command) {
        super("Invalid index.",
                String.format("Please provide a numerical index to `%s`. "
                        + "You may use the `list` command to see all casper.tasks.", command));
    }
}
