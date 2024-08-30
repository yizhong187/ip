package casper.exceptions;

/**
 * Thrown to indicate that no arguments were provided for a command.
 * This exception provides a suggested solution for supplying the necessary arguments.
 */
public class NoArgumentException extends ExceptionWithSolution{
    public NoArgumentException(String command) {
        super(String.format("No arguments for %s.", command),
                "Please provide the necessary arguments.");
    }
}
