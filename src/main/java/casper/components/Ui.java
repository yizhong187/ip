package casper.components;

/**
 * A utility class for printing various messages to the console.
 */
public class Ui {

    /**
     * Prints the given text to the console.
     *
     * @param text The text to print.
     */
    public static void print(String text) {
        System.out.println(text);
    }

    /**
     * Prints a line of underscores to the console.
     */
    public static void printLine() {
        print("____________________________________________________________");
    }

    private static void printCasper() {
        print("""
                              ('-.      .-')     _ (`-.    ('-.  _  .-')                   ('-. .-.   ('-.     .-') _   \s
                             ( OO ).-. ( OO ).  ( (OO  ) _(  OO)( \\( -O )                 ( OO )  /  ( OO ).-.(  OO) )  \s
                    .-----.  / . --. /(_)---\\_)_.`     \\(,------.,------.         .-----. ,--. ,--.  / . --. //     '._ \s
                   '  .--./  | \\-.  \\ /    _ |(__...--'' |  .---'|   /`. '       '  .--./ |  | |  |  | \\-.  \\ |'--...__)
                   |  |('-..-'-'  |  |\\  :` `. |  /  | | |  |    |  /  | |       |  |('-. |   .|  |.-'-'  |  |'--.  .--'
                  /_) |OO  )\\| |_.'  | '..`''.)|  |_.' |(|  '--. |  |_.' |      /_) |OO  )|       | \\| |_.'  |   |  |   \s
                  ||  |`-'|  |  .-.  |.-._)   \\|  .___.' |  .--' |  .  '.'      ||  |`-'| |  .-.  |  |  .-.  |   |  |
                 (_'  '--'\\  |  | |  |\\       /|  |      |  `---.|  |\\  \\      (_'  '--'\\ |  | |  |  |  | |  |   |  |   \s
                    `-----'  `--' `--' `-----' `--'      `------'`--' '--'        `-----' `--' `--'  `--' `--'   `--'
                """);

    }

    /**
     * Prints a welcome message including the Casper ASCII art.
     */
    public static void printWelcomeMessage() {
        printCasper();
        print("Hello! I'm Casper!\nWhat can I do for you today?");
        printLine();
        print("");
    }

    /**
     * Prints a goodbye message.
     */
    public static void printByeMessage() {
        print("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints an error message.
     *
     * @param e The exception containing the error message to print.
     */
    public static void printError(Exception e) {
        print(e.getMessage());
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param taskString The description of the task that was added.
     * @param taskCount The current number of tasks in the task list.
     */
    public static void printAddedTaskMessage(String taskString, int taskCount) {
        print("Got it. I've added this task:\n" +
                taskString + "\n" +
                "Now you have " + taskCount + " task(s) in the list.");
    }

    /**
     * Prints the tasks that match the specified keyword.
     *
     * @param tasks The string containing the tasks that match the keyword, formatted with task indices.
     *              If no tasks match, this string should be empty.
     * @param keyword The keyword used to search the tasks.
     */
    public static void printFoundTasks(String tasks, String keyword){
        if (tasks.equals("")) {
            print(String.format("No task found that matches '%s'.", keyword));
        } else {
            print(String.format("Here are the task(s) matching '%s':", keyword));
            print(tasks);
        }
    }
}
