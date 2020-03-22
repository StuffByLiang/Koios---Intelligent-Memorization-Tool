package ui.console.screen;

import model.TopicManager;
import ui.console.App;

import java.util.Scanner;

// represents a general screen class
public abstract class Screen {
    // STATIC SCREEN CONSTANTS
    public static final int MAIN_SCREEN = 0;
    public static final int TOPIC_SCREEN = 1;
    public static final int TEST_SCREEN = 2;

    protected TopicManager topicManager;
    protected App app;
    protected Scanner input;

    // EFFECTS: constructs a screen given the app
    public Screen(App app) {
        this.app = app;
        topicManager = app.getTopicManager();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays the screen
    public abstract void display();

    // EFFECTS: processes the commands given a string
    public abstract void processCommand(String command);

    // EFFECTS: this method runs when the app screen is changed to this screen
    public void init() {
        topicManager = app.getTopicManager();
    }

    // EFFECTS: this method runs before the screen is displayed
    public void runBeforeDisplay() {}
}
