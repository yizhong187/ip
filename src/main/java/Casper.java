import java.util.Objects;
import java.util.Scanner;

public class Casper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean chat = true;
        String[] tasks = new String[100];
        int currTaskIndex = 0;

        printLine();
        print("Hello! I'm Casper\n What can I do for you?");
        printLine();
        print("");

        while (chat) {
            String input = scanner.nextLine();
            printLine();

            if (Objects.equals(input, "bye" )) {
                chat = false;
                print("Bye. Hope to see you again soon!");

            } else if (Objects.equals(input, "list" )){
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    print(i + ". " + tasks[i]);
                }

            } else {
                tasks[currTaskIndex] = input;
                currTaskIndex++;
                print("added: " + input);
            }

            printLine();
            print("");
        }
    }

    private static void print(String text) {
        System.out.println(text);
    }

    private static void printLine() {
        print("____________________________________________________________");
    }
}
