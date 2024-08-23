import exceptions.MissingArgumentException;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    public static String[] parseArgument(String argument) throws MissingArgumentException {
        String[] parts = argument.split(" /by ", 2);
        String description = parts[0];
        if (parts.length == 1) {
            throw new MissingArgumentException("deadline", "/by", "deadline [task description] /by [deadline]");
        }
        String by = parts[1];
        return new String[]{description, by};
    }
}

