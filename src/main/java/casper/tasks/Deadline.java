package casper.tasks;

import casper.exceptions.MissingArgumentException;

import java.time.LocalDateTime;

import static casper.utils.DateTimeUtils.convertDateTimeToString;

public class Deadline extends Task{

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDateTimeToString(this.by) + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("deadline | %d | %s | %s",
                super.isDone ? 1 : 0,
                this.description,
                convertDateTimeToString(this.by));
    }
}

