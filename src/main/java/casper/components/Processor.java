package casper.components;

import casper.Casper;
import casper.exceptions.ExceptionWithSolution;
import casper.exceptions.CustomIOException;
import casper.exceptions.MissingArgumentException;
import casper.exceptions.InvalidCommandException;
import casper.tasks.Deadline;
import casper.tasks.Event;
import casper.tasks.ToDo;

import java.util.Objects;

import static casper.utils.DateTimeUtils.convertStringToDateTime;

/**
 * Processes user input commands and manages tasks in the task list.
 */
public class Processor {

    /**
     * Processes the user input command and updates the task list accordingly.
     *
     * @param input The user input command to process.
     * @param taskList The task list to which the command will be applied.
     * @throws ExceptionWithSolution If there is an issue with the command that needs a solution.
     * @throws CustomIOException If an I/O error occurs while interacting with the storage.
     * @throws MissingArgumentException If required arguments are missing for a specific command.
     * @throws InvalidCommandException If the command is invalid or not recognized.
     */
    public static String processInput(String input, TaskList taskList) throws
            ExceptionWithSolution, CustomIOException {

        String[] inputParts = Parser.parseInputToCommandAndArgument(input);
        String command = inputParts[0];
        String argument = inputParts.length > 1 ? inputParts[1] : null;

        switch (command) {
        case "bye" -> {
            return "Bye! You can close this window now or continue chatting anytime you want.";
        }
        case "list" -> {
            return taskList.toString();
        }

        case "mark", "unmark", "delete" -> {
            Parser.argumentCheck(argument, command);
            taskList.checkIndexValidity(argument, command);

            int taskIndex = Integer.parseInt(argument) - 1;

            if (Objects.equals(command, "mark")) {
                taskList.markTask(taskIndex);
                Storage.editTaskInSavedTasks(Casper.FILE_PATH,
                        taskList.getTaskSaveString(taskIndex),
                        taskIndex + 1);
                return "Nice! I've marked this task as done:\n" + taskList.getTaskString(taskIndex);

            } else if (Objects.equals(command, "unmark")) {
                taskList.unmarkTask(taskIndex);
                Storage.editTaskInSavedTasks(Casper.FILE_PATH,
                        taskList.getTaskSaveString(taskIndex),
                        taskIndex + 1);
                return "I have unmarked this task:\n" + taskList.getTaskString(taskIndex);

            } else {
                String toBeDeletedTaskString = taskList.getTaskString(taskIndex);
                taskList.removeTask(taskIndex);
                Storage.removeTaskFromSavedTasks(Casper.FILE_PATH, taskIndex + 1);
                return "I have deleted the task:\n" + toBeDeletedTaskString;
            }
        }

        case "find" -> {
            Parser.argumentCheck(argument, command);
            return Stringifier.getFoundTasks(taskList.find(argument), argument);
        }

        case "todo", "deadline", "event" -> {
            Parser.argumentCheck(argument, command);

            if (Objects.equals(command, "todo")) {
                taskList.addTask(new ToDo(argument));

            } else if (Objects.equals(command, "deadline")) {
                String[] parsedForBy = Parser.parseArgumentByKeyword(argument, "by");
                String description = parsedForBy[0];
                if (parsedForBy.length == 1) {
                    throw new MissingArgumentException("deadline", "/by",
                            "deadline [task description] /by [deadline]");
                }
                String by = parsedForBy[1];

                taskList.addTask(new Deadline(description, convertStringToDateTime(by)));

            } else {
                String[] parsedForFrom = Parser.parseArgumentByKeyword(argument, "from");
                String description = parsedForFrom[0];
                if (parsedForFrom.length == 1) {
                    throw new MissingArgumentException("event", "/from",
                            "event [task description] /from [start time] /to [end time]");
                }

                String[] parsedForTo = Parser.parseArgumentByKeyword(parsedForFrom[1], "to");
                String from = parsedForTo[0];
                if (parsedForTo.length == 1) {
                    throw new MissingArgumentException("event", "/to",
                            "event [task description] /from [start time] /to [end time]");
                }
                String to = parsedForTo[1];

                taskList.addTask(new Event(description,
                        convertStringToDateTime(from),
                        convertStringToDateTime(to)));
            }

            Storage.addTaskToSavedTasks(Casper.FILE_PATH, taskList.getTaskSaveString(taskList.size() - 1));
            return Stringifier.getAddedTaskMessage(taskList.getTaskString(taskList.size() - 1), taskList.size());
        }

        default ->
                throw new InvalidCommandException(command);
        }
    }
}
