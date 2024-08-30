package casper.exceptions;

public class ExceptionWithSolution extends Exception{
    private String solution;

    public ExceptionWithSolution(String message, String solution) {
        super(message);
        this.solution = solution;
    }

    @Override
    public String getMessage() {
        return String.format("ERROR: %s \n%s", super.getMessage(), this.solution);
    }
}
