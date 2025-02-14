package mobile.cmd;

import java.util.Scanner;

public class UserInputHandler {
    public static final String YES = "yes";
    public static final String NO = "no";

    private final Scanner scanner;

    public UserInputHandler() {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void exit() {
        scanner.close();
    }

    public String getInputRequireNonEmpty(String property) {
        do {
            System.out.println("Please enter " + property + ":");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println(property + " cannot be empty");
            } else {
                return input;
            }
        } while (true);
    }

    public String getInputFromOptions(String property, String... options) {
        do {
            System.out.println("Please enter " + property + " (" + String.join(", ", options) + "):");
            String input = scanner.nextLine();
            for (String option : options) {
                if (option.equalsIgnoreCase(input)) {
                    return option;
                }
            }
            System.out.println("Invalid " + property);
        } while (true);
    }

    public String getInputWithFromOptionsSkipable(String message, String... options) {
        do {
            System.out.println(message);
            System.out.println("Possible options: " + String.join(", ", options));
            System.out.println("Enter 'no' if you wish to continue.");
            String input = scanner.nextLine();
            if (NO.equals(input)) {
                return null;
            }
            for (String option : options) {
                if (option.equalsIgnoreCase(input)) {
                    return option;
                }
            }
            System.out.println("Invalid input");
        } while (true);
    }
}
