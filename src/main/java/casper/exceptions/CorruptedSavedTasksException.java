package casper.exceptions;

public class CorruptedSavedTasksException extends ExceptionWithSolution{
    public CorruptedSavedTasksException(int lineNumber) {
        super(String.format("Line %d in locally saved casper.tasks list is corrupted", lineNumber),
                "You may want to edit `casper.txt` manually, or delete the file to reset.");
    }
}
