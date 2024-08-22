import java.util.Objects;
import java.util.Scanner;

public class Casper {
    public static void main(String[] args) {
        print("""
                ____________________________________________________________\s
                Hello! I'm Casper\s
                What can I do for you?\s
                ____________________________________________________________\s
                """
        );

        Scanner scanner = new Scanner(System.in);

        boolean chat = true;

        while (chat) {
            String input = scanner.nextLine();

            if (!Objects.equals(input, "bye" )) {
                print("____________________________________________________________ \n" +
                        input + "\n" +
                        "____________________________________________________________ \n" );
            } else {
                chat = false;
                print("""
                        ____________________________________________________________\s
                        Bye. Hope to see you again soon!\s
                        ____________________________________________________________\s
                        """);
            }
        }
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
