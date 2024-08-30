package casper.exceptions;

public class InvalidCommandException extends ExceptionWithSolution{
    public InvalidCommandException(String command) {
        super(String.format("`%s` is an invalid command.", command),
                "Valid commands: `list`, `bye`, `mark`, `unmark`, `delete, `todo`, `deadline`, `event`.");
    }
}
