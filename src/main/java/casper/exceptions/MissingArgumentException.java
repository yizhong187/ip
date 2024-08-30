package casper.exceptions;

/**
 * Thrown to indicate that a required argument is missing for a command.
 * This exception provides a suggested solution for providing the missing argument in the correct format.
 */
public class MissingArgumentException extends ExceptionWithSolution{
    public MissingArgumentException(String command, String missingArgument, String correctFormat) {

        super(String.format("Missing `%s` argument for `%s`", missingArgument, command),
                String.format("Please provide the argument to `%s` as such: %s", command, correctFormat));
    }
}
