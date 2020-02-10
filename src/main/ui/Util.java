package ui;

import java.io.IOException;

public class Util {
    // EFFECTS: prints out a prompt followed by a new line and  "> "
    public static void displayInputPrompt(String prompt) {
        System.out.println(prompt);
        System.out.print("> ");
    }

    // EFFECTS: prints out "> "
    public static void displayInputPrompt() {
        System.out.print("> ");
    }

    // EFFECTS: returns true if a given string is an integer
    public static Boolean isNum(String str) {
        return str.matches("-?\\d+");
    }

    // EFFECTS: waits for user to press key before program continues
    public static void pause() {
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
