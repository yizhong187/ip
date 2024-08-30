package casper;

import casper.exceptions.NoArgumentException;

public class Parser {
    public static String[] parseInputToCommandAndArgument(String input) {
        return input.split(" ", 2);
    }

    public static String[] parseArgumentByKeyword(String argument, String keyword) {
        return argument.split(String.format(" /%s ", keyword), 2);
    }

    public static void argumentCheck(String argument, String command) throws NoArgumentException {
        if (argument == null) {
            throw new NoArgumentException(command);
        }
    }

}
