package mobile.validation;

public class UserInputValidator {
    public static <V> V castUserInput(String input, Class<?> type) {
        if (type == String.class) {
            return (V) input;
        } else if (type == Integer.class) {
            return (V) Integer.valueOf(input);
        } else if (type == Double.class) {
            return (V) Double.valueOf(input);
        } else if (type == Boolean.class) {
            if ("true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input)) {
                return (V) Boolean.valueOf(input);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
