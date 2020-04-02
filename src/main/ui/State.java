package ui;

import model.Topic;
import model.TopicManager;
import persistence.Saver;

import java.io.FileNotFoundException;
import java.io.IOException;

public class State {
    private final App app;
    TopicManager topicManager;
    Topic currentTopic;

    public State(App app) {
        this.app = app;
    }

    // MODIFIES: this
    // EFFECTS: loads topicManager from data.txt, and returns result message
    public String load() {
        try {
            topicManager = Saver.load("data.txt");
            if (currentTopic != null) {
                app.getTopicController().renderFlashCards();
            }
            app.getMainController().renderTopics();
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
    public String save() {
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
}