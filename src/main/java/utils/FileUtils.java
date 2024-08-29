package utils;

import exceptions.CustomIOException;
import exceptions.IndexOutOfRangeException;
import exceptions.InvalidIndexException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void editLineInFile(String filePath, String newText, int lineNumber)
            throws IOException, IndexOutOfRangeException {
        String[] lines = getFileContentsAsArray(filePath);

        if (lineNumber < 1 || lineNumber > lines.length) {
            throw new IndexOutOfRangeException();
        }

        lines[lineNumber - 1] = newText;

        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            content.append(line).append("\n");
        }

        writeToFile(filePath, content.toString());
    }

    public static void addSavedTasksToTaskArray(String filePath, ArrayList<Task> tasks) throws CustomIOException {
        try {
            String[] lines = getFileContentsAsArray(filePath);

            print("Loaded tasks list from local storage!");
            printLine();

            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                String eventType = parts[0];
                boolean isDone = parts[1].equals("1");

                switch (eventType) {
                    case "todo" -> tasks.add(new ToDo(parts[2], isDone));
                    case "deadline" -> tasks.add(new Deadline(parts[2], parts[3], isDone));
                    case "event" -> tasks.add(new Event(parts[2], parts[3], parts[4], isDone));
                }
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
        }
    }

}
