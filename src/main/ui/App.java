package ui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Topic;
import model.TopicManager;
import persistence.Saver;
import ui.controllers.MainController;
import ui.controllers.TestingController;
import ui.controllers.TopicController;
import ui.views.mainview.MainView;
import ui.views.testview.TestingView;
import ui.views.topicview.TopicView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

// Manages the app
public class App {
    public static final int DEFAULT_WIDTH = 650;
    public static final int DEFAULT_HEIGHT = 568;
    public static final String TITLE = "Koios - Intelligent FlashCard App";

    public static App instance;

    private final Stage stage;
    private StackPane root;

    private MainView mainView;
    private TopicView topicView;
    private TestingView testingView;

    private TopicManager topicManager;
    private Topic currentTopic;

    public enum Screen {
        MAIN_SCREEN,
        TOPIC_SCREEN,
        TESTING_SCREEN
    }

    public static App get() {
        return instance;
    }

    // EFFECTS: creates a new singleton instance
    public static void init(Stage stage) {
        instance = new App(stage);
        instance.show();
    }

    // EFFECTS: shows the app
    public void show() {
        initStage();
        loadViews();
        setScreen(Screen.MAIN_SCREEN);
        stage.setTitle(TITLE);
        stage.show();
    }

    // EFFECTS: loads the views
    private void loadViews() {
        mainView = new MainView(root);
        testingView = new TestingView(root);
        topicView = new TopicView(root);
    }

    // EFFECTS: initializes the stage
    private void initStage() {
        root = new StackPane();
        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        URL resource = getClass().getResource("stylesheet/stylesheet_main.css");
        scene.getStylesheets().add(String.valueOf(resource));
        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Montserrat");
        stage.setScene(scene);
    }

    public StackPane getRoot() {
        return root;
    }

    // EFFECTS: creates a new app
    private App(Stage stage) {
        this.stage = stage;
        topicManager = new TopicManager();
    }

    // MODIFIES: this
    // EFFECTS: loads topicManager from data.txt, and returns result message
    public String loadTopicManager() {
        try {
            topicManager = Saver.load("data.txt");
            if (currentTopic != null) {
                getTopicController().renderFlashCards();
            }
            getMainController().renderTopics();
            return "Successfully Loaded!";
        } catch (FileNotFoundException e) {
            return "Error: File not found";
        } catch (IOException e) {
            return "Error: Problem initializing stream";
        } catch (ClassNotFoundException e) {
            return "Error: Class was not found";
        }
    }

    // EFFECTS: saves topicManager to data.txt, and returns result message
    public String saveTopicManager() {
        try {
            Saver.save("data.txt", topicManager);
            return "Successfully Saved!";
        } catch (FileNotFoundException e) {
            return "Error: File not found";
        } catch (IOException e) {
            return "Error: Problem initializing stream";
        }
    }

    public TopicManager getTopicManager() {
        return topicManager;
    }

    public void setTopicManager(TopicManager topicManager) {
        this.topicManager = topicManager;
    }

    public Topic getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(Topic currentTopic) {
        this.currentTopic = currentTopic;
    }

    // EFFECTS: sets the screen
    public void setScreen(Screen screen) {
        root.requestFocus(); // to stop autofocus
        switch (screen) {
            case MAIN_SCREEN:
                root.getChildren().setAll(mainView);
                break;
            case TOPIC_SCREEN:
                root.getChildren().setAll(topicView);
                topicView.getController().onSwitch();
                topicView.requestFocus();
                break;
            case TESTING_SCREEN:
                root.getChildren().setAll(testingView);
                testingView.getController().onSwitch();
                break;
        }
    }

    public MainController getMainController() {
        return mainView.getController();
    }

    public TopicController getTopicController() {
        return topicView.getController();
    }

    public TestingController getTestingController() {
        return testingView.getController();
    }
}