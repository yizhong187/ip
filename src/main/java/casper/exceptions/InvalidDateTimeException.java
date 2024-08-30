package casper.exceptions;

/**
 * Thrown to indicate that the provided date-time format is invalid.
 * This exception provides a suggested solution for the correct date-time format.
 */
public class InvalidDateTimeException extends ExceptionWithSolution{
    public InvalidDateTimeException() {
        super("Invalid date-time format.",
                "Please provide the date-time as 'dd-MM-yyyy HH:mm'. (E.g., 29-08-2024 15:30)");
    }
}
