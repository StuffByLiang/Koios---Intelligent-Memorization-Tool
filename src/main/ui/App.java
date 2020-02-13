package ui;

import model.FlashCard;
import model.FlashCardSet;
import model.Topic;
import model.TopicManager;
import ui.screen.MainScreen;
import ui.screen.Screen;
import ui.screen.TestScreen;
import ui.screen.TopicScreen;

import java.util.Scanner;

// Koios Application
public class App {
    private int screen;
    private int topicId;
    private TopicManager topicManager;

    private Screen mainScreen;
    private Screen topicScreen;
    private Screen testScreen;

    private Scanner input;

    // EFFECTS: Runs the application
    public App() {
        init();
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields & some flash cards
    private void init() {
        screen = Screen.MAIN_SCREEN;
        topicManager = new TopicManager();

        Topic biology = new Topic("Biology");
        FlashCardSet fcl = biology.getFlashCardSet();
        fcl.addFlashCard(new FlashCard("1+1", "2"));
        fcl.addFlashCard(new FlashCard("2+2", "3"));
        fcl.addFlashCard(new FlashCard("4+4", "8"));
        fcl.addFlashCard(new FlashCard("What is the powerhouse of the cell?", "mitochondria"));
        fcl.addFlashCard(new FlashCard("What is the scientific name of humans", "homo sapiens"));
        topicManager.addTopic(biology);

        input = new Scanner(System.in);

        mainScreen = new MainScreen(this);
        topicScreen = new TopicScreen(this);
        testScreen = new TestScreen(this);
    }

    // EFFECTS: Runs the application
    private void runApp() {
        String command = "";
        do {
            //System.out.println(screen);
            runBeforeDisplay(getCurrentScreen());
            display(getCurrentScreen());
            Util.displayInputPrompt();
            command = input.nextLine().toLowerCase();

            processCommand(getCurrentScreen(), command);
        } while (!command.equals("q"));
    }

    // EFFECTS: runs the given screen's runBeforeDisplay method
    private void runBeforeDisplay(Screen screen) {
        screen.runBeforeDisplay();
    }

    // EFFECTS: display's the given screen
    private void display(Screen screen) {
        screen.display();
    }

    // EFFECTS: processes user input for a given screen
    private void processCommand(Screen screen, String command) {
        screen.processCommand(command);
    }

    // MODIFIES: this
    // EFFECTS: sets the current screen and initializes it
    public void setScreen(int screen) {
        this.screen = screen;
        getCurrentScreen().init();
    }

    // MODIFIES: this
    // EFFECTS: sets the current topic Id
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    // EFFECTS: returns the current Screen object
    private Screen getCurrentScreen() {
        switch (screen) {
            case Screen.MAIN_SCREEN:
                return mainScreen;
            case Screen.TOPIC_SCREEN:
                return topicScreen;
            case Screen.TEST_SCREEN:
                return testScreen;
            default:
                return mainScreen;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the current topic Id
    public TopicManager getTopicManager() {
        return topicManager;
    }

    public int getTopicId() {
        return topicId;
    }
}
