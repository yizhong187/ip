package casper.exceptions;

public class NoArgumentException extends ExceptionWithSolution{
    public NoArgumentException(String command) {
        super(String.format("No arguments for %s.", command),
                "Please provide the necessary arguments.");
    }
}
