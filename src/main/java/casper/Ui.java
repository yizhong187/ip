package casper;

public class Ui {
    public static void print(String text) {
        System.out.println(text);
    }

    public static void printLine() {
        print("____________________________________________________________");
    }

    public static void printCasper() {
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

    public static void printWelcomeMessage() {
        printCasper();
        print("Hello! I'm Casper!\nWhat can I do for you today?");
        printLine();
        print("");
    }

    public static void printByeMessage() {
        print("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printError(Exception e) {
        print(e.getMessage());
    }

    public static void printAddedTaskMessage(String taskString, int taskCount) {
        print("Got it. I've added this task:\n" +
                taskString + "\n" +
                "Now you have " + taskCount + " casper.tasks in the list.");
    }
}
