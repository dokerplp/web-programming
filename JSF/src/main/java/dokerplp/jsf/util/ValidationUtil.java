package dokerplp.jsf.util;

public class ValidationUtil {
    public static boolean checkY(String s) {
        if (s.matches("-0(\\.0+)?")) return false;
        return (s.matches("-[0-4](\\.\\d+)?") || s.matches("[0-2](\\.\\d+)?") || s.matches("-5") || s.matches("3"));
    }
}
