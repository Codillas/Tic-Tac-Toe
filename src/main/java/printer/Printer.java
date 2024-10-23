package printer;

public class Printer {
    public static void println() {
        System.out.println();
    }

    public static void println(String value) {
        System.out.println(value);
    }

    public static void print(char value) {
        print(String.valueOf(value));
    }

    public static void print(String value) {
        print(value, Color.DEFAULT);
    }

    public static void print(char value, Color color) {
        print(String.valueOf(value), color);
    }

    public static void print(String value, Color color) {
        String ansi_color = switch (color) {
            case DEFAULT -> "\u001B[0m";
            case BG_GRAY -> "\u001B[47m";
            case BG_CYAN -> "\u001B[46m";
            case BG_MAGENTA -> "\u001B[45m";
            case BG_BLUE -> "\u001B[44m";
            case BG_YELLOW -> "\u001B[43m";
            case BG_GREEN -> "\u001B[42m";
            case BG_RED -> "\u001B[41m";
            case GRAY -> "\u001B[37m";
            case BLACK -> "\u001B[30m";
            case RED -> "\u001B[31m";
            case GREEN -> "\u001B[32m";
            case YELLOW -> "\u001B[33m";
            case BLUE -> "\u001B[34m";
            case MAGENTA -> "\u001B[35m";
            case CYAN -> "\u001B[36m";
        };
        String ansi_reset = "\u001B[0m";

        System.out.print(ansi_color + value + ansi_reset);
    }
}
