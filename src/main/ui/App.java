package ui;

import model.FlashCard;
import model.FlashCardManager;
import model.Topic;
import model.TopicManager;
import ui.screen.MainScreen;
import ui.screen.Screen;
import ui.screen.TopicScreen;

import java.util.Scanner;

public class App {
    private int screen;
    private int topicId;
    private TopicManager topicManager;

    private Screen mainScreen;
    private Screen topicScreen;

    private Scanner input;

    // EFFECTS: Runs the application
    public App() {
        init();
        runApp();
    }

    private void init() {
        screen = Screen.MAIN_SCREEN;
        topicManager = new TopicManager();

        Topic biology = new Topic("Biology");
        FlashCardManager fcl = biology.getFlashCardManager();
        fcl.addFlashCard(new FlashCard("1+1", "2"));
        fcl.addFlashCard(new FlashCard("2+2", "3"));
        fcl.addFlashCard(new FlashCard("4+4", "8"));
        topicManager.addTopic(biology);

        input = new Scanner(System.in);

        mainScreen = new MainScreen(this);
        topicScreen = new TopicScreen(this);
    }

    private void runApp() {
        String command = "";
        do {
            //System.out.println(screen);
            display(getCurrentScreen());
            Util.displayInputPrompt();
            command = input.nextLine().toLowerCase();

            processCommand(getCurrentScreen(), command);
        } while (!command.equals("q"));
    }

    private void display(Screen screen) {
        screen.display();
    }

    private void processCommand(Screen screen, String command) {
        screen.processCommand(command);
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    private Screen getCurrentScreen() {
        switch (screen) {
            case Screen.MAIN_SCREEN:
                return mainScreen;
            case Screen.TOPIC_SCREEN:
                return topicScreen;
            default:
                return mainScreen;
        }
    }

    public TopicManager getTopicManager() {
        return topicManager;
    }

    public int getTopicId() {
        return topicId;
    }
}
