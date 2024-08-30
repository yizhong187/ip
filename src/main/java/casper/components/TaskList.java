package casper.components;

import casper.exceptions.IndexOutOfRangeException;
import casper.exceptions.InvalidIndexException;
import casper.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.tasks.size() == 0) {
            return "No tasks added.";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append((i + 1)).append(". " ).append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    public void markTask(int index) {
        this.tasks.get(index).mark();
    }

    public void unmarkTask(int index) {
        this.tasks.get(index).unmark();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String getTaskString(int index) {
        return this.tasks.get(index).toString();
    }

    public String getTaskSaveString(int index) {
        return this.tasks.get(index).toSaveString();
    }

    public int size() {
        return this.tasks.size();
    }

    public void checkIndexValidity(String argument, String command) throws IndexOutOfRangeException, InvalidIndexException{
        if (!argument.matches("-?\\d+" )) {
            throw new InvalidIndexException(command);
        }

        if (Integer.parseInt(argument) > this.tasks.size() || Integer.parseInt(argument) < 0) {
            throw new IndexOutOfRangeException();
        }
    }

    private int convertNegativeIndex(int index) {
        if (index < 0) {
            return this.tasks.size() + index;
        }
        return index;
    }
}
