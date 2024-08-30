package casper.exceptions;

/**
 * Thrown to indicate that an invalid command was provided.
 * This exception provides a suggested solution listing the valid commands.
 */
public class InvalidCommandException extends ExceptionWithSolution{
    public InvalidCommandException(String command) {
        super(String.format("`%s` is an invalid command.", command),
                "Valid commands: `list`, `bye`, `mark`, `unmark`, `delete, `todo`, `deadline`, `event`.");
    }
}
