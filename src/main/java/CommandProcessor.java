import exceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;
import java.util.Objects;

import static utils.FileUtils.addTaskToSavedTasks;
import static utils.FileUtils.editTaskInSavedTasks;
import static utils.FileUtils.removeTaskFromSavedTasks;
import static utils.DateTimeUtils.convertStringToDateTime;

public class CommandProcessor {
    public static String processInput(String input, ArrayList<Task> tasks) throws
            ExceptionWithSolution, CustomIOException {

        // Break the input into command and argument with a method
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : null;

        switch (command) {

            case "list" -> {
                if (tasks.size() == 0) {
                    return "No tasks added.";
                }

                StringBuilder result = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    result.append((i + 1)).append(". " ).append(tasks.get(i));
                    if (i < tasks.size() - 1) {
                        result.append("\n");
                    }
                }

                return result.toString();
            }

            case "mark", "unmark", "delete" -> {
                argumentCheck(argument, command);

                if (!argument.matches("-?\\d+" )) {
                    throw new InvalidIndexException(command);
                }

                if (Integer.parseInt(argument) > tasks.size() || Integer.parseInt(argument) < 0) {
                    throw new IndexOutOfRangeException();
                }

                int taskIndex = Integer.parseInt(argument) - 1;

                if (Objects.equals(command, "mark")) {
                    tasks.get(taskIndex).mark();
                    editTaskInSavedTasks(Casper.FILE_PATH, tasks.get(taskIndex).toSaveString(), taskIndex + 1);
                    return ("Nice! I've marked this task as done:\n" + tasks.get(taskIndex));
                } else if (Objects.equals(command, "unmark")){
                    tasks.get(taskIndex).unmark();
                    editTaskInSavedTasks(Casper.FILE_PATH, tasks.get(taskIndex).toSaveString(), taskIndex + 1);
                    return ("I have unmarked this task:\n" + tasks.get(taskIndex));
                } else {
                    Task toBeDeleted = tasks.get(taskIndex);
                    tasks.remove(taskIndex);
                    removeTaskFromSavedTasks(Casper.FILE_PATH, taskIndex + 1);
                    return ("I have deleted the task:\n" + toBeDeleted);
                }
            }

            case "todo", "deadline", "event" -> {
                argumentCheck(argument, command);

                if (Objects.equals(command, "todo")) {
                    tasks.add(new ToDo(argument));
                } else if (Objects.equals(command, "deadline")){
                    String[] parsedArguments = Deadline.parseArgument(argument);
                    tasks.add(new Deadline(parsedArguments[0], convertStringToDateTime(parsedArguments[1])));
                } else {
                    String[] parsedArguments = Event.parseArgument(argument);
                    tasks.add(new Event(parsedArguments[0], convertStringToDateTime(parsedArguments[1]), convertStringToDateTime(parsedArguments[2])));
                }

                addTaskToSavedTasks(Casper.FILE_PATH, tasks.get(tasks.size() - 1).toSaveString());
                return addedTaskMessage(tasks.get(tasks.size() - 1).toString(), tasks.size());
            }

            default ->
                    throw new InvalidCommandException(command);
        }
    }

    private static void argumentCheck(String argument, String command) throws NoArgumentException {
        if (argument == null) {
            throw new NoArgumentException(command);
        }
    }

    private static String addedTaskMessage(String newTaskString, int taskCount) {
        return ("Got it. I've added this task:\n" +
                newTaskString + "\n" +
                "Now you have " + taskCount + " tasks in the list.");
    }

}
