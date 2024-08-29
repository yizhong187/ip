package utils;

import exceptions.CorruptedSavedTasksException;
import exceptions.CustomIOException;
import exceptions.InvalidDateTimeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import static utils.PrintUtils.print;
import static utils.PrintUtils.printLine;

public class FileUtils {
    public static String getFileContentsAsString(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        StringBuilder builder = new StringBuilder();

        while (s.hasNextLine()) {
            builder.append(s.nextLine());
            builder.append("\n");
        }

        s.close();

        return builder.toString();
    }

    public static String[] getFileContentsAsArray(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        List<String> lines = new ArrayList<>();

        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }

        s.close();

        return lines.toArray(new String[0]);
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void addLineToFile(String filePath, String newLine) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator());
        fw.write(newLine);
        fw.close();
    }


    public static void editLineInFile(String filePath, String newText, int lineNumber)
            throws IOException {
        String[] lines = getFileContentsAsArray(filePath);

        lines[lineNumber - 1] = newText;

        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            content.append(line).append("\n");
        }

        writeToFile(filePath, content.toString().trim());
    }
    public static void deleteLineInFile(String filePath, int lineNumber)
            throws IOException {
        String[] lines = getFileContentsAsArray(filePath);

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i != lineNumber - 1) {
                content.append(lines[i]).append("\n");
            }
        }

        writeToFile(filePath, content.toString().trim());
    }

    public static void editTaskInSavedTasks(String filePath, String newText, int lineNumber) throws CustomIOException {
        try {
            editLineInFile(filePath, newText, lineNumber + 1);
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }
    public static void removeTaskFromSavedTasks(String filePath, int lineNumber) throws CustomIOException {
        try {
            deleteLineInFile(filePath, lineNumber + 1);
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    public static void addTaskToSavedTasks(String filePath, String newText) throws CustomIOException {
        try {
            addLineToFile(filePath, newText);
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    public static void addSavedTasksToTaskArray(String filePath, ArrayList<Task> tasks) throws CustomIOException, CorruptedSavedTasksException {
        int lineNumber = 1;

        try {
            String[] lines = getFileContentsAsArray(filePath);

            for (String line : Arrays.copyOfRange(lines, 1, lines.length)) {
                String[] parts = line.split(" \\| ");
                String eventType = parts[0];
                boolean isDone = parts[1].equals("1");

                switch (eventType) {
                    case "todo" -> tasks.add(new ToDo(parts[2], isDone));
                    case "deadline" -> tasks.add(
                            new Deadline(parts[2], DateTimeUtils.convertStringToDateTime(parts[3]), isDone));
                    case "event" -> tasks.add(
                            new Event(parts[2],
                                    DateTimeUtils.convertStringToDateTime(parts[3]),
                                    DateTimeUtils.convertStringToDateTime(parts[4]),
                                    isDone));
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            try {
                File file = new File(filePath);
                if (file.createNewFile()) {
                    print("Tasks list is now saved locally! ");
                    printLine();
                }
            } catch (IOException ioException) {
                throw new CustomIOException(ioException.getMessage());
            }
        } catch (InvalidDateTimeException e) {
            throw new CorruptedSavedTasksException(lineNumber);
        }
    }

}
