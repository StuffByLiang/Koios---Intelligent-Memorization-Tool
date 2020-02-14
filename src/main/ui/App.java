package ui;

import model.TopicManager;
import persistence.Saver;
import ui.screen.MainScreen;
import ui.screen.Screen;
import ui.screen.TestScreen;
import ui.screen.TopicScreen;

import java.io.FileNotFoundException;
import java.io.IOException;
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

        input = new Scanner(System.in);

        mainScreen = new MainScreen(this);
        topicScreen = new TopicScreen(this);
        testScreen = new TestScreen(this);

        load();
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

        save();
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

    // MODIFIES: this
    // EFFECTS: loads topicManager from data.txt
    public void load() {
        topicManager = new TopicManager();
        try {
            topicManager = Saver.load("data.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Class was not found");
        }
        setScreen(Screen.MAIN_SCREEN);
    }

    // EFFECTS: saves topicManager to data.txt
    public void save() {
        try {
            Saver.save("data.txt", topicManager);
            System.out.println("saved");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }
}
