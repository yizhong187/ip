package casper.exceptions;

public class MissingArgumentException extends ExceptionWithSolution{
    public MissingArgumentException(String command, String missingArgument, String correctFormat) {

        super(String.format("Missing `%s` argument for `%s`", missingArgument, command),
                String.format("Please provide the argument to `%s` as such: %s", command, correctFormat));
    }
}
