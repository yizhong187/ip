package exceptions;

import java.io.IOException;

public class CustomIOException extends Exception{

    String message;

    public CustomIOException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("I/O ERROR: %s\n", super.getMessage());
    }
}
