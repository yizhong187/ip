package tasks;

import exceptions.MissingArgumentException;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("event | %d | %s | %s | %s", super.isDone ? 1 : 0, this.description, this.from, this.to);
    }

    public static String[] parseArgument(String argument) throws MissingArgumentException{
        String[] partsOne = argument.split(" /from ", 2);
        String description = partsOne[0];
        if (partsOne.length == 1) {
            throw new MissingArgumentException("event", "/from", 
                    "event [task description] /from [start time] /to [end time]");
        }

        String[] partsTwo = partsOne[1].split(" /to ", 2);
        String from = partsTwo[0];
        if (partsTwo.length == 1) {
            throw new MissingArgumentException("event", "/to",
                    "event [task description] /from [start time] /to [end time]");
        }
        
        String to = partsTwo[1];
        return new String[]{description, from, to};
    }
}
