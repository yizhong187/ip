package casper.exceptions;

/**
 * A custom exception class for handling I/O errors with a specific error message format.
 * This exception provides a custom error message prefix to indicate I/O errors.
 */
public class CustomIOException extends Exception {

    public CustomIOException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("I/O ERROR: %s\n", super.getMessage());
    }
}
