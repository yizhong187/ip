package casper.tasks;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return String.format("todo | %d | %s", super.isDone ? 1 : 0, this.description);
    }
}
