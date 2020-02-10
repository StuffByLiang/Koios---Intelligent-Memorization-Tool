package ui.screen;

import model.TopicManager;
import ui.App;

import java.util.Scanner;

public abstract class Screen {
    public static final int MAIN_SCREEN = 0;
    public static final int TOPIC_SCREEN = 1;

    protected TopicManager topicManager;
    protected App app;
    protected Scanner input;

    public Screen(App app) {
        this.app = app;
        topicManager = app.getTopicManager();
        input = new Scanner(System.in);
    }

    public abstract void display();

    public abstract void processCommand(String command);
}
