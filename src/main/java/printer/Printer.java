package printer;

import color.Color;

public class Printer {
    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void println() {
        System.out.println();
    }

    public static void println(String value) {
        println(value, Color.DEFAULT);
    }

    public static void println(String value, Color color) {
        print(value, color);
        println();
    }

    public static void print(String value) {
        print(value, Color.DEFAULT);
    }

    public static void print(String value, Color color) {
        String ansi_color = switch (color) {
            case DEFAULT -> "\u001B[0m";
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
