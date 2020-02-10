package ui;

import model.TopicManager;

import java.util.Scanner;

public class App {
    private static final int MAIN_SCREEN = 0;
//    private static final int MAIN_SCREEN = 0;
//    private static final int MAIN_SCREEN = 0;

    private TopicManager topicManager;
    private Scanner input;

    private int screenState = 0;

    // EFFECTS: Runs the application
    public App() {
        init();
        runApp();
    }

    private void init() {
        topicManager = new TopicManager();
        input = new Scanner(System.in);
    }

    private void runApp() {
        displayMainMenu();
    }

    private void displayMainMenu() {

        System.out.println("\nSelect a Topic: ");

        System.out.println("\tc) Create a topic");
        System.out.println("\tq) Quit Program");
    }
}
