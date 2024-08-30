package casper.tasks;

import casper.exceptions.MissingArgumentException;

import java.time.LocalDateTime;

import static casper.utils.DateTimeUtils.convertDateTimeToString;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + convertDateTimeToString(this.from) + " to: " + convertDateTimeToString(this.to) + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("event | %d | %s | %s | %s", super.isDone ? 1 : 0, this.description, convertDateTimeToString(this.from), convertDateTimeToString(this.to));
    }

}
